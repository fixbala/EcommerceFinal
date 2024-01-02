package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
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
public class ReportesBean implements Serializable {



    @Getter @Setter
    private BarChartModel barChartModel;

    @Getter @Setter
    private BarChartModel barChartModel2;

    @Getter @Setter
    private List<Producto> listaProducto;


    @Autowired
    private ProductoServicio productoServicio;

    @PostConstruct
    public void inicializar(){
        listaProducto= productoServicio.listarTodosProductos();
        graficar(listaProducto);

    }



    private void graficar(List<Producto> CPC){


        barChartModel= new BarChartModel();
        ChartSeries cantProductos = new ChartSeries();
        for (Producto producto: CPC) {
            cantProductos.set(producto.getNombre(), producto.getUnidades());


        }
        barChartModel.addSeries(cantProductos);
        barChartModel.setTitle("Cantidad de productos respecto a sus existencias");



    }

}
