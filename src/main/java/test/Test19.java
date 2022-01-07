package test;

import domain.Imagen;
import domain.Imagen_;
import domain.Inmueble;
import domain.Inmueble_;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class Test19 {
    //actualizar imagen id 1 con el inmueble de id 1 (usare otros ids por lo que tengo ahora mismo)
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            Inmueble inmueble = session.load(Inmueble.class,4);
            Imagen imagen = session.load(Imagen.class,4);
            //Quitamos la imagen de la lista de imagenes
            inmueble.getImagenes().remove(imagen);
            //actualizamos la url
            imagen.setUrl("www.google.com/drive/img1");
            //añadimos la imagen de nuevo
            inmueble.getImagenes().add(imagen);

            session.save(inmueble);

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
