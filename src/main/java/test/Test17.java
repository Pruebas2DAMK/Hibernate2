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

public class Test17 {
    //Inmuebles asociados con una imagen de id 1
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            //tipo de dato que devuelve la consulta
            CriteriaQuery<Inmueble> criteria = builder.createQuery(Inmueble.class);
            //tabla consulta y join
            Root<Imagen> root = criteria.from(Imagen.class);
            Join<Inmueble, Imagen> join = root.join(String.valueOf(Inmueble_.imagenes));
            criteria.where(
                    builder.and(
                            builder.equal(join.get(String.valueOf(Imagen_.id)),1)
            ));


            List<Inmueble> inmuebles = session.createQuery(criteria).getResultList();
            inmuebles.forEach(System.out::println);


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
