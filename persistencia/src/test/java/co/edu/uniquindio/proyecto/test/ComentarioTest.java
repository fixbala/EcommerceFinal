package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
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
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;

    @Test
    @Sql("classpath:Archivos.sql")
    public void registrarTest(){


        Usuario usuario = usuarioRepo.findById("1").orElse(null);
        Producto producto = productoRepo.findById(1).orElse(null);

        LocalDate localDate = LocalDate.now();
        Comentario comentario= new Comentario(1, "El producto esta malo", "Lo lamento",localDate, usuario,producto,1);

        Comentario comentarioGuardado = comentarioRepo.save(comentario);

        Assertions.assertNotNull(comentarioGuardado);
    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void eliminarTest(){


        comentarioRepo.deleteById(1);

        Comentario comentarioBuscado = comentarioRepo.findById(1).orElse(null);

        Assertions.assertNull(comentarioBuscado);

    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void actualizarTest(){

        Comentario comentarioguardado = comentarioRepo.findById(1).orElse(null);

        comentarioguardado.setCalificacion(2);

        comentarioRepo.save(comentarioguardado);

        Comentario comentarioBuscado = comentarioRepo.findById(1).orElse(null);

        Assertions.assertEquals(2, comentarioBuscado.getCalificacion());

    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void listarTest(){

        List<Comentario> comentarios = comentarioRepo.findAll();
        comentarios.forEach(Comentario -> System.out.println(comentarios));
    }




}

