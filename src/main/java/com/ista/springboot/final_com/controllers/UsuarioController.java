package com.ista.springboot.final_com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ista.springboot.final_com.models.entity.Estudiante;
import com.ista.springboot.final_com.models.entity.Materias;
import com.ista.springboot.final_com.models.entity.Rol;
import com.ista.springboot.final_com.models.entity.Usuario;
import com.ista.springboot.final_com.models.service.IEstudianteService;
import com.ista.springboot.final_com.models.service.IMateriasService;
import com.ista.springboot.final_com.models.service.IRolService;
import com.ista.springboot.final_com.models.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("Usuario")
public class UsuarioController {

	@Autowired
    private IUsuarioService usuarioService;

	@Autowired
	private IRolService  rolService;

	@Autowired
	private IEstudianteService  estudianteService;
	
	@Autowired
	private IMateriasService  materiasService;
	
	@GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario()); 
        return "login";
    }

	@PostMapping("/login")
	public String handleLogin(Usuario usuario, Model model, RedirectAttributes flash) {
	    Usuario usuarioAutenticado = usuarioService.findByUsernameAndPassword(usuario.getNombreUsuario(), usuario.getContrasena());

	    if (usuarioAutenticado != null) {
	        model.addAttribute("usuario", usuarioAutenticado);
	        flash.addFlashAttribute("success", "Inicio de sesión exitoso!");
	        return "redirect:/inicio";
	    } else {
	        model.addAttribute("error", "Usuario o contraseña incorrectos. Intente nuevamente.");
	        return "login";
	    }
	}
    
	@GetMapping("/registro")
	public String registro(Model model) {
	    model.addAttribute("usuario", new Usuario());
	    model.addAttribute("roles", rolService.findByAll()); // Cargar los roles desde la base de datos
	    return "registro";
	}

	@PostMapping("/registro")
	public String handleRegistration(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes flash, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("roles", rolService.findByAll()); // Volver a cargar los roles en caso de error
	        model.addAttribute("error", "Corrige los errores en el formulario.");
	        return "registro"; 
	    }
	    usuarioService.save(usuario);
	    flash.addFlashAttribute("success", "Usuario registrado exitosamente!");
	    return "redirect:/login"; 
	}
    
	@GetMapping("/inicio")
	public String inicio(Model model) {
	    try {
	        List<Estudiante> estudiantes = estudianteService.findByAll();
	        List<Materias> materias = materiasService.findByAll(); // Obtener todos los roles

	        model.addAttribute("estudiantes", estudiantes);
	        model.addAttribute("materias", materias); // Agregar los roles al modelo

	        return "inicio";
	    } catch (Exception e) {
	        model.addAttribute("error", "Ocurrió un error al obtener los estudiantes y las materias");
	        return "error";
	    }
	}
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalida la sesión actual
        session.invalidate();
        // Redirige al usuario a la página de login o inicio
        return "redirect:/login";
    }

    @RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id")Long id,  RedirectAttributes flash) {
		if(id>0) {
			estudianteService.delete(id);
			flash.addFlashAttribute("success", "Estudiante eliminado con éxito");
		}
		return "redirect:/inicio";
		
	}   
    
    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteService.findById(id);
        model.addAttribute("estudiante", estudiante);
        return "editar"; // Vista para editar
    }
    
    @PostMapping("/guardarEdicion")
    public String guardarEdicion(@Valid @ModelAttribute("estudiante") Estudiante estudiante, BindingResult result, RedirectAttributes flash, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Corrige los errores en el formulario.");
            return "editar"; 
        }
        estudianteService.save(estudiante);
        flash.addFlashAttribute("success", "Estudiante actualizado correctamente");
        return "redirect:/inicio";
    }
    
    @GetMapping("/buscar")
    public String buscarEstudiante(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Estudiante> estudiantesEncontrados = estudianteService.findByNombreOrApellidoOrCedulaOrMateria(keyword);
        model.addAttribute("estudiantes", estudiantesEncontrados);
        return "inicio";
    }
    
    @GetMapping("/ver/{id}")
    public String verEstudiante(@PathVariable Long id, Model model) {
    	Estudiante estudiante = estudianteService.findById(id);

        if (estudiante == null) {
            model.addAttribute("error", "Estudiante no encontrado.");
            return "error"; 
        }

        model.addAttribute("estudiante", estudiante);
        return "ver"; 
    }
    
	@GetMapping("/estudiante")
	public String registroEstudiante(Model model) {
	    model.addAttribute("estudiante", new Estudiante());
	    model.addAttribute("materias", materiasService.findByAll()); 
	    return "estudiante";
	}

	@PostMapping("/estudiante")
	public String handleRegistrationEstudiante(@Valid @ModelAttribute("estudiante") Estudiante estudiante, BindingResult result, RedirectAttributes flash, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("materias", materiasService.findByAll()); 
	        model.addAttribute("error", "Corrige los errores en el formulario.");
	        return "estudiante"; 
	    }
	    estudianteService.save(estudiante);
	    flash.addFlashAttribute("success", "Estudiante registrado exitosamente!");
	    return "redirect:/inicio"; 
	}
    
	@GetMapping("/materia")
	public String registroMateria(Model model) {
	    model.addAttribute("materias", new Materias()); 
	    return "materia";
	}

	@PostMapping("/materia")
	public String handleRegistrationMateria(@Valid @ModelAttribute("materia") Materias materia, BindingResult result, RedirectAttributes flash, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("materias", materiasService.findByAll()); 
	        model.addAttribute("error", "Corrige los errores en el formulario.");
	        return "materia"; 
	    }
	    materiasService.save(materia);
	    flash.addFlashAttribute("success", "Registrado exitosamente!");
	    return "redirect:/inicio"; 
	}
	

	
	
	@RequestMapping(value="/eliminarMateria/{id}")
	public String eliminarMateria(@PathVariable(value="id")Long id,  RedirectAttributes flash) {
		if(id>0) {
			materiasService.delete(id);
			flash.addFlashAttribute("success", "Materia eliminada con éxito");
		}
		return "redirect:/ListaMateria";
		
	}   
    
    @GetMapping("/editarMateria/{id}")
    public String editarMateria(@PathVariable Long id, Model model) {
        Materias materia = materiasService.findById(id);
        model.addAttribute("materia", materia);
        return "editarMateria"; 
    }
    

    @PostMapping("/guardarEdicionMateria")
    public String guardarEdicionMateria(@Valid @ModelAttribute("materia") Materias materia, BindingResult result, RedirectAttributes flash, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Corrige los errores en el formulario.");
            return "editarMateria"; 
        }
        materiasService.save(materia);
        flash.addFlashAttribute("success", "Actualizado correctamente");
        return "redirect:/ListaMateria";
    }
    
	
	
	@RequestMapping(value="/ListaMateria", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("materia", materiasService.findByAll());
		return "ListaMateria";
	}
	

    
}
