package test;

import domain.Tramite;
import org.hibernate.Session;
import util.HibernateUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Date date = new Date();
        Tramite tramite = new Tramite();
        tramite.setFechaTramite(new Timestamp(date.getTime()));
        tramite.setTipoTramite("Credito");

        session.save(tramite);

        session.getTransaction().commit();
        session.close();

    }
}
