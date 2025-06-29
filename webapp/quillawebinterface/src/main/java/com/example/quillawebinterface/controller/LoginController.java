package com.example.quillawebinterface.controller;

import com.example.quillawebinterface.entity.Usuario;
import com.example.quillawebinterface.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController{

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String error = request.getParameter("error");
        if (error != null) {
            model.addAttribute("error", "Credenciales inválidas");
        }
        return "access/login";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String correo,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Optional<Usuario> usuarioOpt = usuarioService.buscarPorCorreo(correo);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();

                // Aquí validarías la contraseña
                session.setAttribute("usuario", usuario);
                session.setAttribute("usuarioId", usuario.getId());
                session.setAttribute("usuarioNombre", usuario.getNombre());
                session.setAttribute("usuarioRol", usuario.getRol().getNombre());

                // Redirigir según el rol
                if ("ADMIN".equals(usuario.getRol().getNombre())) {
                    return "redirect:/admin/dashboard";
                } else {
                    return "redirect:/cliente/dashboard";
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
                return "redirect:/login";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error en el sistema");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


}
