package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

//sirve únicamente para indicarle a JPA que esa clase es una Entity
@Entity

//Usamos lombook para acortar el codigo
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString

//Se crea la clase Ciudad y se le agrega implements Serializable
public class Ciudad implements Serializable {

    //Indica que este es la llave primaria
    @Id
    //Genera automaticamente el valor de la llave primaria y va aumentando
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Sirve para comparar las llaves primarias
    @EqualsAndHashCode.Include
    //Identificacion de Ciudad (unica)
    private Integer codigo;


    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio y Length le da tamaño al codigo
    @Column(nullable = false, length = 80)
    private String nombre;


    //Aplicamos la relacion uno a muchos entre Ciudad y usuarios
    @JsonIgnore
    @OneToMany(mappedBy = "ciudad",cascade = CascadeType.REMOVE)
    //Lo exluye del metodo toString
    @ToString.Exclude
    private List<Usuario> usuarios;


    //Aplicamos la relacion uno a muchos entre Ciudad y Productos
    @JsonIgnore
    @OneToMany(mappedBy = "ciudad" ,cascade = CascadeType.REMOVE)
    //Lo exluye del metodo toString
    @ToString.Exclude
    private List<Producto> productos;
}





