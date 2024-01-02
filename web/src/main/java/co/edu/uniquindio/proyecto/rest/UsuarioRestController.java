package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.MensajeC;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {

   @Autowired
    private UsuarioServicio usuarioServicio;


   @GetMapping
   public List<Usuario> listar(){
         return usuarioServicio.listarUsuario();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?>  obtenerUsuario(@PathVariable(name = "id") String id){
        try {
             Usuario u = usuarioServicio.obtenerUsuario(id);
            return ResponseEntity.status(200).body(u);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeC(e.getMessage()));
        }

    }

    @PostMapping
    public ResponseEntity<MensajeC> crearUsuario(@RequestBody Usuario usuario){
        try {
            usuarioServicio.registrarUsuario(usuario);
            return ResponseEntity.status(201).body(new MensajeC("Usuario creado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new MensajeC(e.getMessage()));
        }
    }
    @PutMapping
    public  ResponseEntity<MensajeC> actualizarUsuario(@RequestBody Usuario usuario){
        try {
            usuarioServicio.actualizarUsuario(usuario);
            return ResponseEntity.status(201).body(new MensajeC("Usuario actualizado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new MensajeC(e.getMessage()));
        }
    }
    @DeleteMapping("/id")
    public  ResponseEntity<MensajeC> eliminarUsuario(@PathVariable(name = "id") String id){
        try {
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.status(201).body(new MensajeC("Usuario eliminado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new MensajeC(e.getMessage()));
        }




    }



}
