package test;

import domain.DiarioCliente;
import domain.Presupuesto;
import domain.Tramite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.transaction.SystemException;
import java.sql.Timestamp;
import java.util.Date;

public class Test12 {
    //probar la relacion onetomany manytoone
    public static void main(String[] args) throws SystemException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            Timestamp ts = new Timestamp(new Date().getTime());
            //crear dos nuevos tramites
            Tramite tramite = new Tramite("Proyecto 1", ts);
            Tramite tramite2 = new Tramite("Proyecto 2", ts);
            session.save(tramite);
            session.save(tramite2);
            //Crear nuevos diarios cliente
            DiarioCliente d1 = new DiarioCliente("Entrada 1", ts);
            DiarioCliente d2 = new DiarioCliente("Entrada 2", ts);
            DiarioCliente d3 = new DiarioCliente("Entrada 3", ts);
            //Unir diarios con tramites
            d1.setTramite(tramite);
            d2.setTramite(tramite);
            d3.setTramite(tramite2);
            //subir a la bd
            session.save(d1);
            session.save(d2);
            session.save(d3);

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
