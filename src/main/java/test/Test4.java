package test;

import domain.Tramite;
import domain.Tramite_;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Test4 {
    //Consulta con Metamodel generator
    //metamodel generado en target -> generated-sources -> annotations -> domain -> Tramite_
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //vamos a empezar a construir la consulta
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);

        //Define el tipo de entidad que devuelve la consulta
        Root<Tramite> root = criteria.from(Tramite.class);

        //consulta con metamodel
        criteria.select(root)
                .where(builder.equal(root.get(Tramite_.tipoTramite), "Credito"));

        List<Tramite> tramites = session.createQuery(criteria).getResultList();
        tramites.forEach(System.out::println);

        session.getTransaction().commit();
        session.close();

    }
}
