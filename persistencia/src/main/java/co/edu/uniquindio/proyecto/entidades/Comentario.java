package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//sirve únicamente para indicarle a JPA que esa clase es una Entity
@Entity

//Usamos lombook para acortar el codigo
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString

//Se crea la clase Comentario y se le agrega implements Serializable
public class Comentario implements Serializable {

    //Indica que este es la llave primaria
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Identificacion de Codigo (unica)
    private Integer codigo;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacia
    @Column(nullable = false)
    @Lob
    @NotBlank
    private String mensaje;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacia

    @Lob
    private String respuesta;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacia
    @Column(nullable = false)
    private LocalDate fecha_comentario;

    //Aplicamos la relacion muchos a uno entre Comentario y Usuario
    @ManyToOne
    private Usuario usuario;

    //Aplicamos la relacion muchos a uno entre Comentario y Producto
    @ManyToOne
    private Producto producto;
    //El numero debe ser positivo
    @PositiveOrZero
    //La calificacion maxima debe ser 5
    @Max(5)
    // Column ayuda a definir anotaciones en los atributos. No puede ir vacia
    @Column(nullable = false)
    private Integer calificacion;

    public String getFechaEstilo(){
        return fecha_comentario.format(DateTimeFormatter.ISO_DATE);
    }

}

