package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@RequestScoped
public class ReportesBean2 implements Serializable {



    @Getter @Setter
    private BarChartModel barChartModel;

    @Getter @Setter
    private BarChartModel barChartModel3;

    @Getter @Setter
    private BarChartModel barChartModel4;

    @Getter @Setter
    private BarChartModel barChartModel2;

    @Getter @Setter
    private List<Producto> listaProducto;

    @Getter @Setter
    private List<Usuario> listaUsuarios;


    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostConstruct
    public void inicializar(){
        listaProducto= productoServicio.listarTodosProductos();
        listaUsuarios= usuarioServicio.listarUsuario();
        graficar(listaProducto);
        graficar3(listaProducto);
        graficar4(listaUsuarios);
        graficar5(listaProducto);
        //graficar2(listarUsuariosMasProductos);
    }



    private void graficar(List<Producto> CPC){


        barChartModel= new BarChartModel();
        ChartSeries cantProductos = new ChartSeries();
        for (Producto producto: CPC) {
            int descuento = (int) Math.round(producto.getDescuento());
            cantProductos.set(producto.getNombre(), descuento);
            //pieChartModel.set(categoriaProducto.getCategoria(), categoriaProducto.getCantidadProductos());

        }
        barChartModel.addSeries(cantProductos);
        barChartModel.setTitle("Cantidad de productos respecto a su descuento");



    }
    private void graficar3(List<Producto> CPC){


        barChartModel2= new BarChartModel();
        ChartSeries cantProductos = new ChartSeries();
        for (Producto producto: CPC) {
            int precio = (int) Math.round(producto.getPrecio());
            cantProductos.set(producto.getNombre(), precio);
            //pieChartModel.set(categoriaProducto.getCategoria(), categoriaProducto.getCantidadProductos());

        }
        barChartModel2.addSeries(cantProductos);
        barChartModel2.setTitle("Cantidad de productos respecto a su precio");



    }
    private void graficar4(List<Usuario> CPC){


        barChartModel3= new BarChartModel();
        ChartSeries cantProductos = new ChartSeries();
        for (Usuario usuario: CPC) {

            cantProductos.set(usuario.getNombre(), usuario.getProductos().size());
            //pieChartModel.set(categoriaProducto.getCategoria(), categoriaProducto.getCantidadProductos());

        }
        barChartModel3.addSeries(cantProductos);
        barChartModel3.setTitle("Cantidad de Usuarios respecto a sus productos publicados");



    }
    private void graficar5(List<Producto> CPC){


        barChartModel4= new BarChartModel();
        ChartSeries cantProductos = new ChartSeries();
        for (Producto producto: CPC) {

            cantProductos.set(producto.getNombre(), productoServicio.obtenerPromedioCalificaciones(producto.getCodigo()));
            //pieChartModel.set(categoriaProducto.getCategoria(), categoriaProducto.getCantidadProductos());

        }
        barChartModel4.addSeries(cantProductos);
        barChartModel4.setTitle("Cantidad de productos respecto a su calificacion promedio");



    }

}
