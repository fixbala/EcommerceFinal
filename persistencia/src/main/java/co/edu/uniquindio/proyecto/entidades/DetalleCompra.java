package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

//sirve únicamente para indicarle a JPA que esa clase es una Entity
@Entity

//Usamos lombook para acortar el codigo
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString

//Se crea la clase DetalleCompra y se le agrega implements Serializable
public class DetalleCompra implements Serializable {

    //Indica que este es la llave primaria
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Identificacion de Codigo (unica)
    private Integer codigo;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacia
    @Column(nullable = false)
    @Positive
    private Integer unidades;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacia
    @Column(nullable = false)
    @Positive
    private Double precioProducto;

    //Aplicamos la relacion muchos a uno entre DetalleCompra y Compra
    @ManyToOne
    private Compra compra;

    //Aplicamos la relacion muchos a uno entre DetalleCompra y Producto
    @ManyToOne
    private Producto producto;

}
