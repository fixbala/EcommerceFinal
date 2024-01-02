package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//Este permite llamar los datos del archivo .sql
@Repository

public interface ComentarioRepo extends JpaRepository<Comentario,Integer> {



    //FORMA NUMERO 1
    //@Query("select  c from Comentario c where c.calificacion > :calificacionMenor and c.calificacion < :calificacionMenor")
    //List<Comentario> listarComentariosRango1(int calificacionMenor, int calificacionMayor);

    //FORMA NUMERO 2
    @Query("select  c from Comentario c where c.calificacion between :calificacionMenor and :calificacionMayor")
    List<Comentario> listarComentariosRango2(int calificacionMenor, int calificacionMayor);
}
