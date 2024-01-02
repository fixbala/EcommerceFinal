package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;


//    @Test
//    @Sql("classpath:Archivos.sql")
//    public void registrarTest(){
//
//        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
//
//        Map<String, String> telefonos = new HashMap<>();
//        telefonos.put("casa", "12345678");
//        telefonos.put("celular", "98765432");
//
//        Usuario usuario = new Usuario("123","Pepito","pepe@email.com","123456",telefonos, ciudad);
//
//        Usuario usuarioGuardado = usuarioRepo.save(usuario);
//
//        Assertions.assertNotNull(usuarioGuardado);
//
//    }


    @Test
    @Sql("classpath:Archivos.sql")
    public void eliminarTest(){

        usuarioRepo.deleteById("123");

        Usuario usuarioBuscado = usuarioRepo.findById("123").orElse(null);

        Assertions.assertNull(usuarioBuscado);

    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void actualizarTest(){

        Usuario guardado = usuarioRepo.findById("124").orElse(null);
        //modifico el usuario
        guardado.setEmail("lorena_nuevo@email.com");
        //guardar el usuario
        usuarioRepo.save(guardado);

        Usuario usuarioBuscado = usuarioRepo.findById("124").orElse(null);

        Assertions.assertEquals("lorena_nuevo@email.com", usuarioBuscado.getEmail());
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void listarTest(){

        List<Usuario> usuarios = usuarioRepo.findAll();
        usuarios.forEach(usuario -> System.out.println(usuario));
    }





    @Test
    @Sql("classpath:Archivos.sql")
    //Buscamos usurarios por nombres
    public void filtarNombreTest() {
        List<Usuario> lista = usuarioRepo.findAllByNombreContains("ANDRES");

        //Una forma de hacerlo
        //lista.forEach( u -> System.out.println(u));

        //Segunda forma de hacerlo
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:Archivos.sql")

    //Buscamos usurarios por el Email
    public void filtarEmailTest() {

        Optional<Usuario> usuario =  usuarioRepo.findByEmail("santiago@email.com");

        if(usuario.isPresent()){
            System.out.println(usuario.get());
        }else {
        System.out.println("No existe ese correo");
        }
    }


    @Test
    @Sql("classpath:Archivos.sql")
    public void paginarListaTest() {

        Pageable paginador = PageRequest.of( 0, 2 );

        Page<Usuario> lista = usuarioRepo.findAll(paginador);
        System.out.println(lista.stream().collect(Collectors.toList()));
    }


    @Test
    @Sql("classpath:Archivos.sql")
    public void ordenarListaTest() {

        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);
    }


    @Test
    @Sql("classpath:Archivos.sql")
    public void listarUsuariosCiudadTest() {

        List<Usuario> usuarios = ciudadRepo.listarUsuarios( "Pereira");
        usuarios.forEach(System.out::println);
        Assertions.assertEquals( 2, usuarios.size());
    }


    //REVISAR --> CORREGIR EL ASSERTIONS
//    @Test
//    @Sql("classpath:Archivos.sql")
//    public void listarUsuariosProductosTest() {
//
//        List<Object[]> respuesta = usuarioRepo.listarUsuariosYProductos( );
//        respuesta.forEach(objeto -> System.out.println(objeto[0]+"----"+ objeto[1]));
//        Assertions.assertEquals(4, respuesta.size());
//    }


    //REVISAR --> CORREGIR EL ASSERTIONS
//    @Test
//    @Sql("classpath:Archivos.sql")
//    public void listarProductosYComentariosTest() {
//
//        List<Object[]> respuesta = ProductoRepo.listarProductosYComentarios();
//        respuesta.forEach(objeto -> System.out.println(objeto[0]+"----"+ objeto[1]));
//        Assertions.assertEquals(4, respuesta.size());
//    }


    //REVISAR
    //@Test
    //@Sql("classpath:Archivos.sql")
    //public void listarUsuariosComentariosTest() {
        //List<Usuario> usuarios = ProductoRepo.listarUsuariosComentarios(1);
        //usuarios.forEach(System.out::println);
    //}


//    @Test
//    @Sql("classpath:Archivos.sql")
//    public void listarProductosValidosTest() {
//
//        List<ProductoValido> productos = ProductoRepo.listarProductosValidos(LocalDate.now());
//        productos.forEach(System.out::println);
//    }




}

