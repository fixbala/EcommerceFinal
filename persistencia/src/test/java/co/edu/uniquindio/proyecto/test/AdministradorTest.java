package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AdministradorTest {

    @Autowired
    private AdministradorRepo administradorRepo;

    @Test
    @Sql("classpath:Archivos.sql")
    public void registrarTest(){

        Administrador administrador = new Administrador("1","Juan","Juan@email.com","1234", "1");

        Administrador administradorGuardado = administradorRepo.save(administrador);

        Assertions.assertNotNull(administradorGuardado);
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void eliminarTest(){

        administradorRepo.deleteById(1);

        Administrador administradorBuscado = administradorRepo.findById(1).orElse(null);

        Assertions.assertNull(administradorBuscado);

    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void actualizarTest(){

        Administrador guardado = administradorRepo.findById(1).orElse(null);
        //Modifica el administrador
        guardado.setEmail("lorena_nuevo@email.com");
        //guarda el administrador actualizado
        administradorRepo.save(guardado);

        Administrador administradorBuscado = administradorRepo.findById(1).orElse(null);

        Assertions.assertEquals("lorena_nuevo@email.com", administradorBuscado.getEmail());
    }


    @Test
    @Sql("classpath:Archivos.sql")
    public void listarTest(){

        List<Administrador> administradores = administradorRepo.findAll();
        administradores.forEach(administrador -> System.out.println(administrador));
    }


    @Test
    @Sql("classpath:Archivos.sql")
    public void ordenarListaTest() {

       List<Administrador> lista = administradorRepo.findAll(Sort.by("nombre"));
       System.out.println(lista);
    }


    //SE DEBE CORREGIR
    //@Test
    //@Sql("classpath:Archivos.sql")
    //public void loguarseAdministradorTest() {

        //Optional<Administrador> administrador =  AdministradorRepo.findByEmail("santiago@email.com");

        //if(administrador.isPresent()){
            //System.out.println(administrador.get());
        //}else {
            //System.out.println("No existe ese correo");
        //}
    //}

}
