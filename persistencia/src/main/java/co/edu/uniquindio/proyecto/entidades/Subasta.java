package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.List;

//sirve únicamente para indicarle a JPA que esa clase es una Entity
@Entity

//Usamos lombook para acortar el codigo
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString

//Se crea la clase Subasta y se le agrega implements Serializable
public class Subasta implements Serializable {

    //Indica que este es la llave primaria
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Identificacion de Codigo (unica)
    private Integer codigo;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio
    @Column(nullable = false)
    @Future
    private LocalDate fechaLimite;

    //Aplicamos la relacion muchos a uno entre Producto y Subasta
    @ManyToOne
    private Producto producto;

    //Aplicamos la relacion uno a muchos entre SubastaUsuario y Subasta
    @OneToMany(mappedBy = "subasta",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuarios;

}


