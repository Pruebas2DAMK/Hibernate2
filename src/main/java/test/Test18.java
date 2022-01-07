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

public class Test18 {
    //Consulta inmuebles asociados a una imagen con id 1 (yo miro el 4 para que me salga algo) y que el tipo de terreno sea piso
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            //tipo de dato que devuelve la consulta
            CriteriaQuery<Inmueble> criteria = builder.createQuery(Inmueble.class);
            //tabla consulta
            Root<Inmueble> root = criteria.from(Inmueble.class);
            Join<Inmueble, Imagen> join = root.join(Inmueble_.imagenes);

            //Restriccion del id del inmueble dado
            criteria.where(
                    builder.and(
                        builder.equal(join.get(Imagen_.id),4) ,
                        builder.equal(root.get(Inmueble_.tipo),"Piso")
                    )
            );

            //visualizar
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
