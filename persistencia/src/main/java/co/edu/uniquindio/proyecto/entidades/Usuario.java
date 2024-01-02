package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//sirve únicamente para indicarle a JPA que esa clase es una Entity
@Entity
//Usamos lombook para acortar el codigo
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@AllArgsConstructor
//La clase Usuario es herencia de persona
public class Usuario extends Persona implements Serializable {

    //Sirve para el tipo de dato Map<>



    @ElementCollection(fetch = FetchType.EAGER)
    // Column ayuda a definir anotaciones en los atributos. No puede ir vacio
    @Column(nullable = false)
    private Map<String,String> numTelefono;




    @Column(nullable = false, unique = true)
    private String username;

    //Aplicamos la relacion muchos a uno entre Usuario y Ciudad
    @ManyToOne
    @JoinColumn(nullable = true)
    private Ciudad ciudad;


    //Aplicamos la relacion uno a muchos entre Usuario y Compra
    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Compra> compras;


    //Aplicamos la relacion uno a muchos entre Usuario y Comentario
    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Comentario> comentarios;


    //Aplicamos la relacion uno a muchos entre Usuario y SubastaUsuario
    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuarios;


    //Aplicamos la relacion muchos a muchos entre Producto y Usuario

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH, CascadeType.MERGE,CascadeType.REFRESH})
    @ToString.Exclude
    private List<Producto> productosFavoritos;


    //Aplicamos la relacion uno a muchos entre Usuario y Producto
    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Producto> productos;


    //Aplicamos la relacion uno a muchos entre Usuario y Chat
    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Chat> chats;


    //Creamos el constructor con argumentos de la super clase





    public Usuario(String codigo, String nombre, String email,String username, String password,Ciudad ciudad) {
        super(codigo, nombre, email, password);

        this.username = username;
        this.ciudad = ciudad;
    }



}
