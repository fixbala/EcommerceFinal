package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;

public interface AdminServicio {

    Administrador logInAdmin(String codigo, String password) throws Exception;
}
