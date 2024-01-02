package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class misProductosBean implements Serializable {

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private List<Producto> productos;

    @Autowired
    private ProductoServicio productoServicio;

    @PostConstruct
    public void inicializar(){

        try {
            productos= productoServicio.listarProductos(usuarioSesion.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void eliminarProducto(Integer id){
        try {
            productoServicio.eliminarProducto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String irADetalle(String id){
        return "/detalleProducto?faces-redirect=true&amp;producto="+id;
    }
}
