package test;

import domain.Presupuesto;
import domain.Tramite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Timestamp;
import java.util.Date;

public class Test9 {
    //crear un presupuesto y luego asignarlo a un tramite ya creado
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            //cargo el tramite existente en la bd con id 3
            Tramite tramite = session.load(Tramite.class,3);

            Presupuesto presupuesto = new Presupuesto("Calle Federico Garcia Lorca");
            //asignamos este presupuesto al tramite cargado
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
