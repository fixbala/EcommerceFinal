insert into ciudad values (1,"Armenia");
insert into ciudad values (2,"Pereira");
insert into ciudad values (3,"Medellin");

<<<<<<< HEAD
insert into usuario values ("123", "miguel@email.com", "MIGUEL", "1234","nose", 1);
insert into usuario values ("124", "andres@email.com", "ANDRES", "1234","sldkjflksd", 2);
insert into usuario values ("125", "santiago@email.com", "SANTIAGO", "1234","dsfsd", 3);
=======
insert into usuario values ("123", "miguel@email.com", "MIGUEL", "1234", 1);
insert into usuario values ("124", "andres@email.com", "ANDRES", "1234", 2);
insert into usuario values ("125", "santiago@email.com", "SANTIAGO", "1234", 3);
insert into usuario values ("126", "andresNuevo@email.com", "JUAN ANDRES", "1234", 2);
>>>>>>> cb03e369f40e61577c0e9fe26048863e1d11e99b

insert into usuario_num_telefono values("123","7123258","casa");
insert into usuario_num_telefono values("124","3135581225","trabajo");
insert into usuario_num_telefono values("125","3105924077","trabajo");

insert into categoria values ("1","electrodomesticos");
insert into categoria values ("2","vehiculos");
insert into categoria values ("3","libros");

insert  into producto values ("1","moto sin gasolina",50000,"2000/09/03","Moto",200000,2,1,"123");
insert  into producto values ("2","computador sin bateria",25000,"2000/09/03","Computador",250000,5,2,"125");
insert  into producto values ("3","celular dañado",80000,"2000/09/03","Celular",300000,3,3, "124");

insert  into producto_categorias values ("1","1");
insert  into producto_categorias values ("2","2");
insert  into producto_categorias values ("3","3");

insert into comentario values ("1",5,"2000/09/07","mal servicio","ofrecemos disculpas","1", "123");
insert into comentario values ("3",2,"2015/09/07","no llego articulo","ofrecemos disculpas","2", "124");
insert into comentario values ("2",10,"2001/09/07","buen servicio","muchas gracias","3", "125");

insert  into chat values ("1","123");
insert  into chat values ("2","125");
insert  into chat values ("3","124");

insert  into compra values ("1","2000/09/07","efectivo","123");
insert  into compra values ("2","2000/09/07","trasnferencia","124");
insert  into compra values ("3","2001/09/07","bono","125");

insert into detalle_compra values ("1",24000.00,2,"1","2");
insert into detalle_compra values ("2",24000.00,2,"2","1");
insert into detalle_compra values ("3",24000.00,2,"3","3");

insert into mensaje values ("1","usuario","2000/09/07","llega producto","1");
insert into mensaje values ("2","usuario","2000/09/04","no llega producto","2");
insert into mensaje values ("3","usuario","2000/09/05","no llega producto","3");

insert  into subasta values ("1","2000/09/04","1");
insert  into subasta values ("2","2000/09/05","3");
insert  into subasta values ("3","2000/09/08","2");

insert  into subasta_usuario values ("1","2021/08/15",680400.00,"1","124");
insert  into subasta_usuario values ("2","2020/12/13",320500.00,"3","125");
insert  into subasta_usuario values ("3","2021/09/27",546780.00,"2","123");

insert into administrador values ("1", "pepito@email.com", "PEPITO", "1234");
insert into administrador values ("2", "alejito@email.com", "ALEJO", "12345");
insert into administrador values ("3", "juanca@email.com", "JUAN", "123456");

insert into producto_usuarios values ("1","123" );
insert into producto_usuarios values ("2","124" );
insert into producto_usuarios values ("3","125" );









