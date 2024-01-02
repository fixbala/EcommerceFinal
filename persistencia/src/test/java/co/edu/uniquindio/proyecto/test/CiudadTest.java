package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;


    @Test
    @Sql("classpath:Archivos.sql")
    public void registrarTest  (){


        List<Usuario> listaUsuarios = new ArrayList<>();

        listaUsuarios.add(usuarioRepo.findById("123").orElse(null));
        listaUsuarios.add(usuarioRepo.findById("124").orElse(null));
        listaUsuarios.add(usuarioRepo.findById("125").orElse(null));

        List<Producto> listaProductos = new ArrayList<>();

        listaProductos.add(productoRepo.findById(1).orElse(null));
        listaProductos.add(productoRepo.findById(2).orElse(null));
        listaProductos.add(productoRepo.findById(3).orElse(null));



        Ciudad ciudad = new Ciudad(1, "Armenia", listaUsuarios, listaProductos);
        Ciudad ciudadGuardada = ciudadRepo.save(ciudad);
        Assertions.assertNotNull(ciudadGuardada);
    }
    @Test
    @Sql("classpath:Archivos.sql")
    public  void eliminarTest (){
        ciudadRepo.deleteById(1);
        Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);

        Assertions.assertNull(ciudadBuscada);
    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void actualizarTest(){
        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        //modifico ciudad
        ciudad.setNombre("cali");
        //guarda la ciudad
        ciudadRepo.save(ciudad);

        Ciudad ciudadBuscado = ciudadRepo.findById(1).orElse(null);

        Assertions.assertEquals("cali", ciudadBuscado.getNombre());

    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void listarTest(){

        List<Ciudad> ciudades = ciudadRepo.findAll();
        ciudades.forEach(ciudad -> System.out.println(ciudad));

    }

}
