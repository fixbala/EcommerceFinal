package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicioImpl implements AdminServicio{

    @Autowired
    private AdministradorRepo administradorRepo;

    @Override
    public Administrador logInAdmin(String codigo, String password) throws Exception {
        return administradorRepo.findByEmailAndPassword(codigo,password).orElseThrow( ()-> new Exception("Los datos de autenticacion son incorrectos"));
    }
}
