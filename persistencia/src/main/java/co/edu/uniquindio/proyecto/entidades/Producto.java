package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//sirve únicamente para indicarle a JPA que esa clase es una Entity
@Entity

//Usamos lombook para acortar el codigo
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString

//Se crea la clase Producto y se le agrega implements Serializable
public class Producto implements Serializable {

    //Indica que este es la llave primaria
    @Id
    //Identificacion de Codigo (unica)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio y Length le da tamaño al codigo
    @Column(nullable = false, length = 80)
    private String nombre;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio
    @Column(nullable = false)
    @PositiveOrZero
    private Integer unidades;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio
    @Lob
    @NotBlank
    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String nombrePublicacion;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio
    @Column(nullable = false)
    @Positive
    private Double precio;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio
    @Column(nullable = false)
    @Future
    private LocalDate fechaLimite;

    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio
    @Column(nullable = false)
    @PositiveOrZero
    private Double descuento;



    @ElementCollection
    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio
    private List<String> imagenes ;

    @ElementCollection
    private List<Categoria> categorias;


    //Aplicamos la relacion muchos a uno entre Producto y Ciudad
    @ManyToOne
    private Ciudad ciudad;

    //Aplicamos la relacion muchos a uno entre Producto y Usuario
    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    //Aplicamos la relacion uno a muchos entre Producto y DetalleCompra
    @JsonIgnore
    @OneToMany(mappedBy = "producto" ,cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    //Aplicamos la relacion uno a muchos entre Comentario y Producto
    @JsonIgnore
    @OneToMany(mappedBy = "producto" ,cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Comentario> comentarios;

    //Aplicamos la relacion uno a muchos entre Producto y Subasta
    @JsonIgnore
    @OneToMany(mappedBy = "producto",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Subasta> subastas;

    //Aplicamos la relacion muchos a muchos entre Producto y Usuario
    @JsonIgnore
    @ManyToMany(mappedBy = "productosFavoritos",cascade = {CascadeType.PERSIST,CascadeType.DETACH, CascadeType.MERGE,CascadeType.REFRESH})
    @ToString.Exclude
    private List<Usuario> usuarios;






    public Producto( String nombre, Integer unidades, String descripcion, Double precio, LocalDate fechaLimite, Double descuento, Usuario usuario) {

        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.descuento = descuento;
        this.usuario = usuario;
    }

    public String getImagenPrincipal(){
        if(imagenes !=null && !imagenes.isEmpty()){
            return imagenes.get(0);
        }
        return "default.jfif";
    }
}



