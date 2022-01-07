package test;

import domain.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class Test16 {
    //consulta imagenes de un inmueble con id 1 + gmail
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
            //Join de las dos entidades relacionadas
            Join<Imagen, Inmueble> join = root.join(Imagen_.inmuebles);
            //Restriccion del id del inmueble dado + la palabra gmail
            criteria.where(
                    builder.and(
                            builder.equal(join.get(Inmueble_.id),1),
                            builder.like(root.get(Imagen_.url),"%gmail%"))
                    );


            List<Imagen> imagenes = session.createQuery(criteria).getResultList();
            imagenes.forEach(System.out::println);


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
