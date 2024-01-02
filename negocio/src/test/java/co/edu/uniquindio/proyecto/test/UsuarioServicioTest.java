package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarTest(){
        Usuario u = new Usuario("123","pepe","pepe@email.com","1234","pepito12",null);
        try {
            usuarioServicio.registrarUsuario(u);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

//    @Autowired
//    private UsuarioServicio usuarioServicio;
//
//    @Test
//    public void registrarTest(){
//        Usuario u = new Usuario("123","pepe","pepe@email.com","1234","pepito12",null);
//        try {
//            usuarioServicio.registrarUsuario(u);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }



