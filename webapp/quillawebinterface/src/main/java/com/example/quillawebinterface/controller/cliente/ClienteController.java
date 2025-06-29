package com.example.quillawebinterface.controller.cliente;

import com.example.quillawebinterface.entity.SalaVideo;
import com.example.quillawebinterface.entity.Usuario;
import com.example.quillawebinterface.repository.SalaVideoRepository;
import com.example.quillawebinterface.service.UsuarioService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private SalaVideoRepository salaVideoRepository;

    @Autowired
    private UsuarioService usuarioService;

    // Dashboard principal del cliente
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (!esUsuarioLogueado(session)) {
            return "redirect:/login";
        }

        Integer usuarioId = (Integer) session.getAttribute("usuarioId");

        // Obtener las salas del usuario
        List<SalaVideo> salasUsuario = salaVideoRepository.findByUsuarioIdAndUsuarioEliminadoFalse(usuarioId);
        model.addAttribute("salasUsuario", salasUsuario);

        // Obtener información del usuario para mostrar extensión SIP
        Optional<Usuario> usuario = usuarioService.buscarPorId(usuarioId);
        if (usuario.isPresent() && usuario.get().getExtensionSip() != null) {
            model.addAttribute("extension", usuario.get().getExtensionSip().getNumeroExt());
        }

        return "cliente/dashboard";
    }

    // Módulo de telefonía
    @GetMapping("/telefonia")
    public String telefonia(Model model, HttpSession session) {
        if (!esUsuarioLogueado(session)) {
            return "redirect:/login";
        }

        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        Optional<Usuario> usuario = usuarioService.buscarPorId(usuarioId);

        if (usuario.isPresent() && usuario.get().getExtensionSip() != null) {
            model.addAttribute("extension", usuario.get().getExtensionSip());
        }

        return "cliente/telefonia";
    }

    // Módulo de videoconferencias
    @GetMapping("/video")
    public String video(Model model, HttpSession session) {
        if (!esUsuarioLogueado(session)) {
            return "redirect:/login";
        }

        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        List<SalaVideo> salasUsuario = salaVideoRepository.findByUsuarioIdAndUsuarioEliminadoFalse(usuarioId);

        model.addAttribute("salasUsuario", salasUsuario);
        return "cliente/video";
    }

    // Crear nueva sala de video
    @PostMapping("/video/crear-sala")
    public String crearSala(
            @RequestParam String nombreSala,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (!esUsuarioLogueado(session)) {
            return "redirect:/login";
        }

        try {
            Integer usuarioId = (Integer) session.getAttribute("usuarioId");
            Optional<Usuario> usuario = usuarioService.buscarPorId(usuarioId);

            if (usuario.isPresent()) {
                SalaVideo sala = new SalaVideo(nombreSala, usuario.get());
                salaVideoRepository.save(sala);
                redirectAttributes.addFlashAttribute("success", "Sala creada exitosamente");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear la sala");
        }

        return "redirect:/cliente/video";
    }

    // Módulo de chat
    @GetMapping("/chat")
    public String chat(Model model, HttpSession session) {
        if (!esUsuarioLogueado(session)) {
            return "redirect:/login";
        }

        return "cliente/chat";
    }

    // Módulo de documentos colaborativos
    @GetMapping("/documentos")
    public String documentos(Model model, HttpSession session) {
        if (!esUsuarioLogueado(session)) {
            return "redirect:/login";
        }

        return "cliente/documentos";
    }

    // Perfil del usuario
    @GetMapping("/perfil")
    public String perfil(Model model, HttpSession session) {
        if (!esUsuarioLogueado(session)) {
            return "redirect:/login";
        }

        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        Optional<Usuario> usuario = usuarioService.buscarPorId(usuarioId);

        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
        }

        return "cliente/perfil";
    }

    private boolean esUsuarioLogueado(HttpSession session) {
        return session.getAttribute("usuarioId") != null;
    }
}