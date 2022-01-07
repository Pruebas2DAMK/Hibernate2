package test;

import domain.DiarioCliente;
import domain.Tramite;
import domain.Tramite_;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.SystemException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Test13 {
    //consultar todos los tramites diarioCliente
    public static void main(String[] args) throws SystemException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            //tipo de dato que devuelve la consulta
            CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
            //tabla consulta
            Root<Tramite> root = criteria.from(Tramite.class);
            //Join de las dos entidades relacionadas
            Join<Tramite, DiarioCliente>join = root.join(Tramite_.diarioCliente);
            criteria.select(root).distinct(true); //distinc evita la redundancia


            List<Tramite> tramites = session.createQuery(criteria).getResultList();
            //todos los datos de diariocliente en sql por pantalla
            tramites.forEach(System.out::println);


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
