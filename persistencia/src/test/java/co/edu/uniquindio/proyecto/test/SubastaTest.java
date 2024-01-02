package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)


public class SubastaTest {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;


    @Test
    @Sql("classpath:Archivos.sql")
    public void registrarTest(){

        Producto producto = productoRepo.findById(1).orElse(null);

        List<SubastaUsuario> subastaUsuarios = new ArrayList<>();

        subastaUsuarios.add(subastaUsuarioRepo.findById(1).orElse(null));
        subastaUsuarios.add(subastaUsuarioRepo.findById(2).orElse(null));
        subastaUsuarios.add(subastaUsuarioRepo.findById(3).orElse(null));

        LocalDate localDate = LocalDate.now();
        Subasta subasta = new Subasta(1,localDate,producto, subastaUsuarios);

        Subasta subastaGuardado = subastaRepo.save(subasta);

        Assertions.assertNotNull(subastaGuardado);
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void eliminarTest(){

        subastaRepo.deleteById(1);

        Subasta subastaBuscado = subastaRepo.findById(1).orElse(null);

        Assertions.assertNull(subastaBuscado);
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void actualizarTest(){

        Subasta guardado = subastaRepo.findById(3).orElse(null);

        LocalDate localDate = LocalDate.now();
        guardado.setFechaLimite(localDate);

        subastaRepo.save(guardado);

        Subasta subastaBuscado = subastaRepo.findById(3).orElse(null);

        Assertions.assertEquals(localDate, subastaBuscado.getFechaLimite());
    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void listarTest(){

        List<Subasta> subastas = subastaRepo.findAll();
        subastas.forEach(subasta -> System.out.println(subasta));
    }
}
