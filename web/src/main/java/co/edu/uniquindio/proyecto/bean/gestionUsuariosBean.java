package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class gestionUsuariosBean implements Serializable {

    @Getter @Setter
    private List<Usuario> listaUsuarios;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostConstruct
    public void inicializar(){
        listaUsuarios = usuarioServicio.listarUsuario();
    }

    public void eliminarUsuario(String codigo){
        try {
            usuarioServicio.eliminarUsuario(codigo);
            FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Se elimino correctamente");
            FacesContext.getCurrentInstance().addMessage("msj-mang",fm);
        } catch (Exception e) {
            FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-mang",fm);
        }

    }

    public String irActualizarUsuario(String id){
        return "/admin/actualizarUsuario?faces-redirect=true&amp;usuario="+id;
    }
}
