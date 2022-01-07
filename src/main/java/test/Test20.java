package test;

import domain.Imagen;
import domain.Inmueble;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class Test20 {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            Inmueble inmueble = session.load(Inmueble.class,4);
            Imagen imagen = session.load(Imagen.class,4);
            //Quitamos la imagen de la lista de imagenes
            inmueble.getImagenes().remove(imagen);
            //Esto elimina la imagen 4 del inmueble 4 en la tabla inmueble_has_imagen
            //no elimina la imagen ni el inmueble solo la union de ambas
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
