package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;


import java.util.List;

public interface ProductoServicio {

    List<Producto> listarPorCiudad(String nombre);

    List<Producto> listarPorDescuento();
    List<Producto> productosMascomentados();
    List<Producto> listarPorRango(Double precio);

    Producto publicarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto producto)throws Exception;


    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;



    List<Producto> listarTodosProductos();

    void comentarProducto(Comentario comentario) throws Exception;



    List<Producto> listarProductos(Categoria categoria) throws Exception;

    void comentarProducto(String mensaje, Integer calificacion, Usuario usuario, Producto producto) throws Exception;


    void guadarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception;

    void comprarProductos(Compra compra) throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros);

    List<Producto> listarProductos(String codigoUsuario) throws Exception;


    List<Categoria> listarCategoria();


    Categoria obtenerCategoria(String categoria) throws Exception;

    Integer obtenerPromedioCalificaciones(Integer codigo);

    Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception;



}
