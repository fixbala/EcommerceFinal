package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private ProductoRepo productoRepo;
    @Test
    @Sql("classpath:Archivos.sql")
    public void registrarTest(){

        Compra compra = compraRepo.findById(1).orElse(null);
        Producto producto = productoRepo.findById(1).orElse(null);

        DetalleCompra detalleCompra = new DetalleCompra(1, 5, 50000.00, compra,producto);
        DetalleCompra detalleCompraGuardado = detalleCompraRepo.save(detalleCompra);

        Assertions.assertNotNull(detalleCompraGuardado);
    }

    @Test
    @Sql("classpath:Archivos.sql")
   public void eliminarTest(){

        detalleCompraRepo.deleteById(1);

        DetalleCompra detalleCompraBuscado = detalleCompraRepo.findById(1).orElse(null);

        Assertions.assertNull(detalleCompraBuscado);
    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void actualizarTest(){

        DetalleCompra guardado = detalleCompraRepo.findById(1).orElse(null);
        //modifico el usuario
        guardado.setPrecioProducto(233456.00);
        //guardar el usuario
        detalleCompraRepo.save(guardado);

        DetalleCompra detalleCompraBuscado = detalleCompraRepo.findById(1).orElse(null);

        Assertions.assertEquals(233456.00, detalleCompraBuscado.getPrecioProducto());



    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void listarTest(){


        List<DetalleCompra> detalleCompras = detalleCompraRepo.findAll();
        detalleCompras.forEach(detalleCompra -> System.out.println(detalleCompra));



    }
}
