package test;

import domain.DiarioCliente;
import domain.DiarioCliente_;
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
import java.util.List;

public class Test14 {
    //todos los diarios del tramite con id 1
    public static void main(String[] args) throws SystemException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            //tipo de dato que devuelve la consulta
            CriteriaQuery<DiarioCliente> criteria = builder.createQuery(DiarioCliente.class);
            //tabla consulta
            Root<DiarioCliente> root = criteria.from(DiarioCliente.class);
            //Join de las dos entidades relacionadas
            Join<DiarioCliente, Tramite> join = root.join(DiarioCliente_.tramite);
            criteria.where(
                    builder.equal(root.get(DiarioCliente_.tramite),session.load(Tramite.class,1))
            ); //saca por pantalla todos los diarios del tramite con id 1


            List<DiarioCliente> diarios = session.createQuery(criteria).getResultList();
            diarios.forEach(System.out::println);


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
