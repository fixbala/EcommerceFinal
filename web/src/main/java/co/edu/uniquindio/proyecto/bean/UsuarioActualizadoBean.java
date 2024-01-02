package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class UsuarioActualizadoBean implements Serializable {

    @Getter @Setter
    private Usuario usuario;


    private String usuarioId;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private CiudadServicio ciudadServicio;

    @PostConstruct
    public void inicializar(){
        try {
            usuarioId="7777";
            usuario= usuarioServicio.obtenerUsuario(usuarioId);
            ciudades= ciudadServicio.listarCiudades();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(){
        try {
            usuarioServicio.actualizarUsuario(usuario);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Registro exitoso");
            FacesContext.getCurrentInstance().addMessage("msj-bean",msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean",msg);
        }
    }
}
