package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class MensajeTest {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private MensajeRepo mensajeRepo;

    @Test
    @Sql("classpath:Archivos.sql")
    public void registrarTest(){

        Chat chat = chatRepo.findById(1).orElse(null);

        LocalDateTime localDateTime = LocalDateTime.now();
        Mensaje mensaje = new Mensaje(21,"Hola", "Arias", localDateTime, chat );

        Mensaje mensajeGuardado = mensajeRepo.save(mensaje);

        Assertions.assertNotNull(mensajeGuardado);
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void eliminarTest(){

        mensajeRepo.deleteById(1);

        Mensaje mensajeBuscado = mensajeRepo.findById(1).orElse(null);

        Assertions.assertNull(mensajeBuscado);
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void actualizarTest(){

        Mensaje guardado = mensajeRepo.findById(1).orElse(null);
        //Modificamos el nuevo mensaje
        guardado.setMensaje("Hola crack");
        //Guardamos el mensaje
        mensajeRepo.save(guardado);

        Mensaje mensajeBuscado= mensajeRepo.findById(1).orElse(null);

        Assertions.assertEquals("Hola crack", mensajeBuscado.getMensaje());
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void listarTest(){

        List<Mensaje> mensajes = mensajeRepo.findAll();
        mensajes.forEach(mensaje -> System.out.println(mensaje));
    }

}
