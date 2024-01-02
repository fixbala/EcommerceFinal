package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

//sirve únicamente para indicarle a JPA que esa clase es una Entity
@Entity
//Usamos lombook para acortar el codigo
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor

//La clase Administrador es herencia de persona
public class Administrador extends Persona implements Serializable {

    //Indica que este es la llave primaria
    @Id

    // Column ayuda a definir anotaciones en los atributos. Length le da tamaño al codigo
    @Column(length = 10)

    //Identificacion del administrador (unica)
    private String codigo;

    //Creamos el constructor con argumentos de la super clase
    public Administrador(String codigo, String nombre, String email, String password, String codigo1) {
        super(codigo, nombre, email, password);
        this.codigo = codigo1;
    }

}
