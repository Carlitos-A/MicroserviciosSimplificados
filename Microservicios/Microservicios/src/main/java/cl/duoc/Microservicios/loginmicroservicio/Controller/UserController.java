package cl.duoc.Microservicios.loginmicroservicio.Controller;

import cl.duoc.Microservicios.loginmicroservicio.Controller.UserController;
import cl.duoc.Microservicios.loginmicroservicio.model.Usuario;
import cl.duoc.Microservicios.loginmicroservicio.repository.UsuarioRepository;
import cl.duoc.Microservicios.loginmicroservicio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/gestionusuario")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> TodosLosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.TodosLosUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> BuscarUsuarioId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.BuscarUsuarioId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombreusuario}")
    public ResponseEntity<Usuario> BuscarPorNombre(@PathVariable String nombreusuario) {
        Usuario usuario = usuarioService.BuscarPorNombre(nombreusuario);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/crearUsuario")
    public ResponseEntity<?> CrearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioCreado = usuarioService.CrearUsuario(usuario);
            return ResponseEntity.ok(usuarioCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario.");
        }
    }
    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticarUsuario(@RequestBody Map<String, String> datos) {
        try {
            String nombreUsuario = datos.get("nombreusuario");
            String contrasena = datos.get("contrasena");

            Usuario usuario = usuarioService.buscarPorNombreUsuario(nombreUsuario);
            boolean autenticado = false;
            String tipoUsuario = "no existe";

            if (usuario != null && contrasena.equals(usuario.getContrasena())) {
                autenticado = true;
                tipoUsuario = usuario.getTipousuario();
            }

            Map<String, Object> respuesta = Map.of(
                    "autenticado", autenticado,
                    "tipoUsuario", tipoUsuario
            );

            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al autenticar el usuario."));
        }
    }
    @PutMapping("/actualizarusuario/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        try {
            Optional<Usuario> usuarioExistente = usuarioService.BuscarUsuarioId(id);
            if (usuarioExistente.isPresent()) {
                Usuario usuario = usuarioExistente.get();
                usuario.setNombreusuario(usuarioActualizado.getNombreusuario());
                usuario.setContrasena(usuarioActualizado.getContrasena());
                usuario.setTipousuario(usuarioActualizado.getTipousuario());
                Usuario usuarioGuardado = usuarioService.CrearUsuario(usuario); 
                return ResponseEntity.ok(usuarioGuardado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario.");
        }
    }
    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.EliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario.");
        }
    }


}
