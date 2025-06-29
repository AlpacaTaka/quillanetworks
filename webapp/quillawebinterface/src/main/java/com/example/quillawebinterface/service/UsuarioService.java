package com.example.quillawebinterface.service;

import com.example.quillawebinterface.entity.Credencial;
import com.example.quillawebinterface.entity.ExtensionSip;
import com.example.quillawebinterface.entity.Rol;
import com.example.quillawebinterface.entity.Usuario;
import com.example.quillawebinterface.repository.RolRepository;
import com.example.quillawebinterface.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuariosActivos() {
        return usuarioRepository.findByEstadoTrueAndEliminadoFalse();
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findByEliminadoFalse();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findByIdAndEliminadoFalse(id);
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public Usuario crearUsuario(String nombre, String correo, String password, String rolNombre, String numeroExt) {
        Rol rol = rolRepository.findByNombre(rolNombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario(nombre, rol);
        usuario = usuarioRepository.save(usuario);

        // Crear credencial
        Credencial credencial = new Credencial(correo, passwordEncoder.encode(password), usuario);
        usuario.setCredencial(credencial);

        // Crear extensión SIP si se proporciona
        if (numeroExt != null && !numeroExt.isEmpty()) {
            ExtensionSip extension = new ExtensionSip(numeroExt, generarClaveSip(), usuario);
            usuario.setExtensionSip(extension);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Integer id, String nombre, Boolean estado) {
        Usuario usuario = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(nombre);
        usuario.setEstado(estado);

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        Usuario usuario = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEliminado(true);
        usuario.setEstado(false);
        usuarioRepository.save(usuario);
    }

    private String generarClaveSip() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}