package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Este permite llamar los datos del archivo .sql
@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    Optional<Ciudad> findByNombre(String nombreCiudad);


    //Solicitamos la informacion de una lista de una entidad, es necesario hacer el Join
    @Query("select u from Ciudad c join  c.usuarios u where c.nombre = :nombre")
    List<Usuario> listarUsuarios(String nombre);

}
