package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.MensajeC;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producto")
public class ProductoRestController {

    @Autowired
    private ProductoServicio productoServicio;


    @GetMapping
    public List<Producto> listar(){
        return productoServicio.listarTodosProductos();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable(name = "id") Integer id){
        try {
            Producto p = productoServicio.obtenerProducto(id);
            return ResponseEntity.status(200).body(p);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeC(e.getMessage()));
        }

    }
    @PostMapping
    public ResponseEntity<MensajeC> crearProdcuto(@RequestBody Producto producto){
        try {
            productoServicio.publicarProducto(producto);
            return ResponseEntity.status(201).body(new MensajeC("producto creado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new MensajeC(e.getMessage()));
        }
    }
    @PutMapping
    public  ResponseEntity<MensajeC> actualizarProducto(@RequestBody Producto producto){
        try {
            productoServicio.actualizarProducto(producto);
            return ResponseEntity.status(201).body(new MensajeC("producto actualizado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new MensajeC(e.getMessage()));
        }
    }

    @DeleteMapping("/id")
    public  ResponseEntity<MensajeC> eliminarProducto(@PathVariable(name = "id") Integer id){
        try {
            productoServicio.eliminarProducto(id);
            return ResponseEntity.status(201).body(new MensajeC("producto eliminado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new MensajeC(e.getMessage()));
        }

    }
    @GetMapping("/categorias/{categoria}")
    public ResponseEntity<?> listarPorCategoria(@PathVariable(name = "categoria") String categoria){
        try {
          Categoria c = productoServicio.obtenerCategoria(categoria);
          List<Producto> p = productoServicio.listarProductos(c);
          return ResponseEntity.status(200).body(p);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new MensajeC(e.getMessage()));
        }
    }
    @GetMapping("/ciudades/{nombreciudad}")
    public ResponseEntity<?> listarPorCiudad(@PathVariable(name = "nombreciudad") String ciudad){
         List<Producto> p = productoServicio.listarPorCiudad(ciudad);
        return ResponseEntity.status(200).body(p);
    }

    @GetMapping("/rangoprecio/{precio}")
        public ResponseEntity<?> ListarPorRango(@PathVariable(name = "precio") double precio ){
        List<Producto> p = productoServicio.listarPorRango(precio);
        return ResponseEntity.status(200).body(p);
        }

    @GetMapping("/productocondescuento")
    public ResponseEntity<?> ListarSinComentarios(){
        List<Producto> p = productoServicio.listarPorDescuento();
        return ResponseEntity.status(200).body(p);
    }

    @GetMapping("/comentados")
    public ResponseEntity<?> ListarComentados(){
        List<Producto> p = productoServicio.productosMascomentados();
        return ResponseEntity.status(200).body(p);
    }
}



