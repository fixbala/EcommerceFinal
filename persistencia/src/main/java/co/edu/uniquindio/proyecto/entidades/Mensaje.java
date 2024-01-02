package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

//sirve únicamente para indicarle a JPA que esa clase es una Entity
@Entity

//Usamos lombook para acortar el codigo
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString

//Se crea la clase Mensaje y se le agrega implements Serializable
public class Mensaje implements Serializable {

    //Indica que este es la llave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Identificacion de Codigo (unica)
    private Integer codigo;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacia
    @Column(nullable = false)
    private String mensaje;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacia
    @Column(nullable = false)
    private String emisor;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacia
    @Column(nullable = false)
    private LocalDateTime fecha;

    //Aplicamos la relacion muchos a uno entre Mensaje y Chat
    @ManyToOne
    private Chat chat;

}
