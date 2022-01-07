package test;

import domain.Tramite;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class Test2 {
    //QUERY
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //SELECT * FROM TRAMITE WHERE ...
        Query<Tramite> query = session.createQuery("from domain.Tramite where idTramite = :tipoTram"); //FIJARSE MUY BIEN PORQUE BUSCA POR LA RUTA DE LA CLASE NO EL NOMBRE DE LA TABLA
        query.setParameter("tipoTram",1); //tipoTram es un nombre comodin

        List<Tramite> tramites = query.getResultList();
        tramites.forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
    }
}
