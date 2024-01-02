package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.dto.SendEmail;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {


    @Autowired
    private ProductoRepo productoRepo;


    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;


    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try {
            return productoRepo.save(p);
        }catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {

    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {

        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public List<Producto> listarPorCiudad(String nombre) {
        return productoRepo.listarPorCiudad(nombre);
    }

    @Override
    public List<Producto> listarPorDescuento() {
        return productoRepo.productosConDescuento();
    }

    @Override
    public List<Producto> productosMascomentados() {
        return productoRepo.productosMascomentados();
    }

    @Override
    public List<Producto> listarPorRango(Double precio) {
        return productoRepo.listarRangoPrrecio(precio);
    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {


        Optional<Producto> producto = productoRepo.findById(codigo);

        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }

        productoRepo.deleteById(codigo);

    }


    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {


        return productoRepo.findById(codigo).orElseThrow( ()-> new Exception("El producto no existe"));
    }



    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepo.findAll();
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFecha_comentario(LocalDate.now());
        comentarioRepo.save(comentario);

    }

    @Override
    public void comentarProducto(String mensaje, Integer calificacion, Usuario usuario, Producto producto) throws Exception {


    }

    @Override
    public void guadarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProductos(Compra compra) throws Exception {

    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros) {

        return productoRepo.buscarProductoNombre(nombreProducto);


    }

    @Override
    public List<Producto> listarProductos(String codigoUsuario) throws Exception {
        return productoRepo.obtenerProductoCodigoU(codigoUsuario);
    }


    @Override
    public List<Categoria> listarCategoria() {
        return Arrays.asList(Categoria.values());
    }


    @Override
    public Categoria obtenerCategoria(String categoria) throws Exception {
        return Categoria.valueOf(categoria);
    }

    @Override
    public Integer obtenerPromedioCalificaciones(Integer codigo) {
        return productoRepo.obtenerPromedioCalificaciones(codigo);
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception {
        try {

            Compra c = new Compra();
            Producto producto = new Producto();
            c.setFechaCompra(LocalDate.now(ZoneId.of("America/Bogota")));
            c.setUsuario(usuario);
            c.setMedioPago(medioPago);
            String string ="";


            Compra compraGuardada = compraRepo.save(c);

            DetalleCompra dc;
            for (ProductoCarrito p : productos) {
                producto = productoRepo.findById(p.getId()).get();
                dc = new DetalleCompra();
                dc.setCompra(compraGuardada);
                dc.setPrecioProducto(p.getPrecio());
                dc.setUnidades(p.getUnidades());
                dc.setProducto(producto);
                string+="Nombre del producto: "+producto.getNombre()+"\n";
                string+="Precio del producto: "+producto.getPrecio()+"\n";
                string+="Unidades: "+p.getUnidades()+"\n";

                producto.setUnidades(producto.getUnidades()-p.getUnidades());

                detalleCompraRepo.save(dc);
            }
            SendEmail sendEmail = new SendEmail();
            sendEmail.setToEmail(usuario.getEmail());
            sendEmail.setSubject("Detalle compra");
            sendEmail.setBody(string);
            sendEmail.setFrom("migue.2556242@gmail.com");

            System.out.println(emailService.sendEmail(sendEmail));
            return compraGuardada;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

}
