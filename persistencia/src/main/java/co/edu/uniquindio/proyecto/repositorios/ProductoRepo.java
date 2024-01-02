package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


//Este permite llamar los datos del archivo .sql
@Repository
public interface ProductoRepo extends JpaRepository <Producto, Integer> {



    Page<Producto> findAll(Pageable paginador);

    @Query("select p.usuario.nombre from Producto p where p.codigo = :id")
    String obtenerNombreVendedor(String id);


    @Query("select c from Producto p join p.comentarios c where p.codigo = :id")
    List<Comentario> obtenerComentarios(Integer id);

    @Query("select u.email, p from Usuario u join u.productos p ")
    List<Object[]> listarUsuariosYProductos();

    @Query("select p.nombre, c from Producto p left join p.comentarios c ")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.usuario  from Producto p join p.comentarios c where p.codigo = :id ")
    List<Object[]> listarUsuariosYComentarios(Integer id);



    @Query("select p.nombre, p.descripcion, p.precio, p.ciudad.nombre from Producto p where :fechaActual < p.fechaLimite")
    List<Object[]> listarProductosValidos(LocalDate fechaActual);

    @Query("select c, count (p) from Producto p join p.categorias c group by c")
    List<Object[]> obtenerTotalProductosPorCategoria();



    @Query("select p from Producto p where p.comentarios is empty ")
    List<Producto> obtenerProductosSinComentarios();

    @Query("select p from Producto p where p.usuario.codigo = :codigo")
    List<Producto> obtenerProductoCodigoU(String codigo);

    List<Producto> findByNombreContains(String nombre);

    @Query("select p from Producto p where p.nombre like concat('%', :nombre, '%') ")
    List<Producto> buscarProductoNombre(String nombre);

    @Query("select  c, count(p) as total from Producto p join p.categorias c group by c order by total desc")
    List<Object[]> obtenerCategoriaMasUsada();

    @Query("select  avg(c.calificacion) from Producto p join p.comentarios c where p.codigo = :codigo")
    Integer obtenerPromedioCalificaciones(Integer codigo);



    @Query("select p from Producto p where :categoria  member of p.categorias")
    List<Producto> listarPorCategoria(Categoria categoria);

    @Query("select p from Producto p where p.ciudad.nombre like concat('%', :nombre, '%') ")
    List<Producto> listarPorCiudad(String nombre);

    @Query("select p from Producto p where :precio < p.precio")
    List<Producto> listarRangoPrrecio(Double precio);

    @Query("select p from Producto p where p.descuento > 0")
    List<Producto> productosConDescuento();

    @Query("select p from Producto p where p.comentarios.size < 5 ")
    List<Producto> productosMascomentados();


//    Falta hacerle el test
//    Traer todos los comentarios de un producto dado su ID
//    @Query("select c from Producto p join p.comentarios c where p.codigo = :id")
//    List<Comentario> obtenerComentarios1(String id);
//
//    @Query("select c from Comentario c where c.producto.codigo = :id")
//    List<Comentario> obtenerComentarios2(String id);


    //REVISAR
    //@Query("select p.nombre, c from Producto p left join p.comentarios c")
    //List<Object[]> listarProductosYComentarios();


    //Con el distinct se evita que se repitan los registros
    //@Query("select distinct c.usuario from Producto p join p.comentarios c where p.codigo = :id")
    //List<Usuario> listarUsuariosComentarios(String id);


    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido(p.nombre,p.descripcion,p.precio,p.ciudad) from Producto p where :fechaActual < p.fechaLimite")
    List<ProductoValido> listarProductosValidos(LocalDateTime fechaActual);

}
