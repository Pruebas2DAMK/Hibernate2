package test;

import domain.Tramite;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Test3 {
    //Consulta de los registros con Criteria
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //vamos a empezar a construir la consulta
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);

        //Define el tipo de entidad que devuelve la consulta
        Root<Tramite> root = criteria.from(Tramite.class);

        //Construyendo la consulta
        criteria.select(root); //Hago un select

        //Solo los tramites de tipo credito
        criteria.where(builder.equal(root.get("tipoTramite"),"Credito")); //tambien se puede anidar al select de manera que se vea más como un SQL

        /*
        Ejemplo Anidado:
            criteria.select(root)
                .where(builder.equal(root.get("tipoTramite"), "Credito"));
         */

        List<Tramite> tramites = session.createQuery(criteria).getResultList();
        tramites.forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
    }

}
