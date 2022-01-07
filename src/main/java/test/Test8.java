package test;

import domain.Presupuesto;
import domain.Tramite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.transaction.SystemException;
import java.sql.Timestamp;
import java.util.Date;

public class Test8 {
    //Crear un tramite y asociarle un presupuesto
    public static void main(String[] args) throws SystemException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            Tramite tramite = new Tramite("Credito",new Timestamp(new Date().getTime()));
            session.save(tramite);

            Presupuesto presupuesto = new Presupuesto("Altabix - Elche");
            presupuesto.setTramite(tramite);
            session.save(presupuesto);

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
