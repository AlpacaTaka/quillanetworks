package com.example.quillawebinterface.controller.admin;

import com.example.quillawebinterface.entity.Usuario;
import com.example.quillawebinterface.repository.SalaVideoRepository;
import com.example.quillawebinterface.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SalaVideoRepository salaVideoRepository;

    // Dashboard principal del administrador
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }

        model.addAttribute("totalUsuarios", usuarioService.listarTodosUsuarios().size());
        model.addAttribute("usuariosActivos", usuarioService.listarUsuariosActivos().size());
        model.addAttribute("totalSalas", salaVideoRepository.count());

        return "admin/dashboard";
    }

    // Gestión de usuarios
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }

        model.addAttribute("usuarios", usuarioService.listarTodosUsuarios());
        return "admin/usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }

        return "admin/usuario-form";
    }

    @PostMapping("/usuarios/crear")
    public String crearUsuario(
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String password,
            @RequestParam String rol,
            @RequestParam(required = false) String numeroExt,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (!esAdmin(session)) {
            return "redirect:/login";
        }

        try {
            usuarioService.crearUsuario(nombre, correo, password, rol, numeroExt);
            redirectAttributes.addFlashAttribute("success", "Usuario creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear usuario: " + e.getMessage());
        }

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }

        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "admin/usuario-edit";
        }

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/actualizar/{id}")
    public String actualizarUsuario(
            @PathVariable Integer id,
            @RequestParam String nombre,
            @RequestParam Boolean estado,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (!esAdmin(session)) {
            return "redirect:/login";
        }

        try {
            usuarioService.actualizarUsuario(id, nombre, estado);
            redirectAttributes.addFlashAttribute("success", "Usuario actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar usuario");
        }

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }

        try {
            usuarioService.eliminarUsuario(id);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar usuario");
        }

        return "redirect:/admin/usuarios";
    }

    // Gestión de configuración del sistema
    @GetMapping("/configuracion")
    public String configuracion(Model model, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }

        return "admin/configuracion";
    }

    // Reportes y estadísticas
    @GetMapping("/reportes")
    public String reportes(Model model, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }

        return "admin/reportes";
    }

    private boolean esAdmin(HttpSession session) {
        String rol = (String) session.getAttribute("usuarioRol");
        return "ADMIN".equals(rol);
    }
}