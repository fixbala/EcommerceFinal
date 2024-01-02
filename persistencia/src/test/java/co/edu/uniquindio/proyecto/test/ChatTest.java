package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {
    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private MensajeRepo mensajeRepo;

    @Test
    @Sql("classpath:Archivos.sql")
    public void registrarTest(){

        Usuario usuario = usuarioRepo.getById("123");

        List<Mensaje> Listmensajes = new ArrayList<>();

        Listmensajes.add(mensajeRepo.findById(1).orElse(null));
        Listmensajes.add(mensajeRepo.findById(2).orElse(null));
        Listmensajes.add(mensajeRepo.findById(3).orElse(null));

        Chat chat = new Chat(1,Listmensajes, usuario);
        Chat chatGuardado = chatRepo.save(chat);

        Assertions.assertNotNull(chatGuardado);


    }
    @Test
    @Sql("classpath:Archivos.sql")
    public void eliminarTest(){


        chatRepo.deleteById(1);

        Chat chatBuscado = chatRepo.findById(1).orElse(null);

        Assertions.assertNull(chatBuscado);

    }

    @Test
    @Sql("classpath:Archivos.sql")
    public void actualizarTest(){
        Chat chat = chatRepo.findById(1).orElse(null);

        chat.setCodigo(3);

        chatRepo.save(chat);

        Chat chatBuscada = chatRepo.findById(1).orElse(null);

        Assertions.assertEquals(3, chatBuscada.getCodigo());


    }

    @Test
    @Sql("classpath:Archivos.sql")

    //Esta generando error al entrar al ToString del Usuario
    public void listarTest(){

        List<Chat> chats = chatRepo.findAll();
        chats.forEach(chat-> System.out.println(chat));

    }
}
