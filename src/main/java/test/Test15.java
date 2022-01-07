package test;

import domain.DiarioCliente;
import domain.Imagen;
import domain.Inmueble;
import domain.Tramite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Timestamp;
import java.util.Date;

public class Test15 {
    //Creacion de objetos relacion ManyToMany
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            Timestamp ts = new Timestamp(new Date().getTime());
            //Nuevos inmuebles
            Inmueble i1 = new Inmueble("Piso","Plaza Barcelona");
            Inmueble i2 = new Inmueble("Apartamento","Avenida de las naciones");
            //Nuevas Imagenes
            Imagen img1 = new Imagen("www.imagen.com/img1",ts);
            Imagen img2 = new Imagen("www.imagen.com/img2",ts);
            Imagen img3 = new Imagen("www.imagen.com/img3",ts);

            //Añadir las imagenes a los inmuebles
            i1.getImagenes().add(img1);
            i1.getImagenes().add(img2);

            i2.getImagenes().add(img1);
            i2.getImagenes().add(img3);

            //guardamos en bd
            session.save(i1);
            session.save(i2);

            transaction.commit();
        }catch(Exception e){
            if (session != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
