package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class SubastaUsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;

    @Test
    @Sql("classpath:Archivos.sql")
    public void registrarTest(){

        Usuario usuario = usuarioRepo.findById("123").orElse(null);

        Subasta subasta = subastaRepo.findById(1).orElse(null);

        LocalDate localDate = LocalDate.now();
        SubastaUsuario subastaUsuario = new SubastaUsuario(123, 50000.00, localDate,usuario,subasta );

        SubastaUsuario subastaUsuarioGuardado = subastaUsuarioRepo.save(subastaUsuario);

        Assertions.assertNotNull(subastaUsuarioGuardado);
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void eliminarTest(){

        subastaUsuarioRepo.deleteById(1);

        SubastaUsuario subastaUsuarioBuscado = subastaUsuarioRepo.findById(1).orElse(null);

        Assertions.assertNull(subastaUsuarioBuscado);
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void listarTest(){

        List<SubastaUsuario> subastaUsuarios = subastaUsuarioRepo.findAll();
        subastaUsuarios.forEach(subastaUsuario -> System.out.println(subastaUsuario));
    }


}
