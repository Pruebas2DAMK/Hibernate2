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
import java.awt.*;
import java.util.List;

public class Test21 {
    //Eliminar todas las imagenes de un inmueble en concreto
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            //tipo de dato que devuelve la consulta
            CriteriaQuery<Imagen> criteria = builder.createQuery(Imagen.class);
            //tabla consulta
            Root<Imagen> root = criteria.from(Imagen.class);
            Join<Imagen, Inmueble> join = root.join(Imagen_.inmuebles);

            //query
            criteria.where(
                    builder.equal(join.get(Inmueble_.id),4)

            );

            //visualizar
            List<Imagen> imagenes = session.createQuery(criteria).getResultList();
            imagenes.forEach(System.out::println);

            //cargamos el inmueble con id 4
            Inmueble inmueble = session.load(Inmueble.class, 4);

            //Eliminamos las imagenes que le pertenecen
            imagenes.forEach( e->{
                inmueble.getImagenes().remove(e);
                session.save(inmueble);
            });
            /*
            OTRA FORMA DE ELIMINAR TODAS LAS IMAGENES
            ----------
            Inmueble inmueble = session.load(Inmueble.class, 4);
            inmueble.setImagenes(null);
            session.save(inmueble);
            ----------
            ELIMINAR TODAS LAS IMAGENES DENTRO DEL INMUEBLE X Y ELIMINAR EL INMUEBLE
            ----------
            Cargar los dos.
            inmueble.getImagenes().remove(imagen) -> de cualquiera de las dos maneras vista
            session.delete(inmueble);
             */

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
