package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Este permite llamar los datos del archivo .sql
@Repository
public interface AdministradorRepo extends JpaRepository <Administrador, Integer> {


    //Busca una lista de Usuarios por nombre, y nos devuelve un listado de los que contengan ese nombre
    List<Usuario> findAllByNombreContains(String nombre);


    // METODO PARA HACER EN EL NUEVO MODULO
    //@Override
    //public Usuario login(String email, String password) throws Exception {
    //    return usuarioRepo.finByEmailAndPassword(email,password).orElseThrow( ()-> new Exception("Los datos de autenticacion son incorrectos"));
    //}

    //Loguearse
      Optional<Administrador> findByEmailAndPassword(String email, String clave);
//    Page<Administrador> findAll(Pageable paginador);


}
