package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Test
    @Sql("classpath:Archivos.sql")
    public void registrarTest(){


        List<DetalleCompra> detalleCompras = new ArrayList<>();

        detalleCompras.add(detalleCompraRepo.findById(1).orElse(null));
        detalleCompras.add(detalleCompraRepo.findById(2).orElse(null));
        detalleCompras.add(detalleCompraRepo.findById(3).orElse(null));

        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        LocalDate localDate = LocalDate.now();
        Compra compra= new Compra(1,localDate, "efectivo",usuario, detalleCompras);

        Compra compraguardada = compraRepo.save(compra);

        Assertions.assertNotNull(compraguardada);

    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void eliminarTest(){


        compraRepo.deleteById(1);

        Compra compraBuscado = compraRepo.findById(1).orElse(null);

        Assertions.assertNull(compraBuscado);

    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void actualizarTest(){

        Compra compraguardado = compraRepo.findById(1).orElse(null);

        compraguardado.setMedioPago("efectivo");

        compraRepo.save(compraguardado);

        Compra compraBuscado = compraRepo.findById(1).orElse(null);

        Assertions.assertEquals("targeta", compraBuscado.getMedioPago());

    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void listarTest(){


        List<Compra> compras = compraRepo.findAll();
        compras.forEach(Compra -> System.out.println(Compra));

    }


}