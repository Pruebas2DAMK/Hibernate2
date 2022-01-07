package test;

import domain.Tramite;
import domain.Tramite_;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Test7 {

    //Consultes imbricades amb Criteria
    public static void main(String[] args) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
        try{
            //construccion consulta
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
            //entidad consulta
            Root<Tramite> root = criteria.from(Tramite.class);

            //Consulta
            criteria.select(root)
                    .where(builder.like(root.get(Tramite_.tipoTramite), "%Otro Crédito%"));

            //Mostrar resultados
            List<Tramite> tramites = session.createQuery(criteria).getResultList();
            tramites.forEach(System.out::println);

            //Realiza los cambios en la base de datos
            transaction.commit();
        } catch (Exception e) {
            //si no se ha iniciado sesion y se detecta error, vuelve al estado de antes de la ejecucion de este codigo
            if (session != null) {
                transaction.rollback();
            }
        } finally {
            //cierra conexion con la base de datos si o si
            session.close();
        }
    }
}

