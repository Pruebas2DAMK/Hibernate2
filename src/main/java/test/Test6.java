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

public class Test6 {
    //save or update
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            //construccion consulta
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
            //entidad consulta
            Root<Tramite> root = criteria.from(Tramite.class);
            //Consulta
            criteria.select(root)
                    .where(builder.equal(root.get(Tramite_.tipoTramite),"Credito"));

            Tramite tramite = session.createQuery(criteria).getSingleResult();
            tramite.setTipoTramite("Aval");

            Tramite tramite2 = new Tramite();
            Date date = new Date();
            tramite2.setTipoTramite("Otro Crédito");
            tramite2.setFechaTramite(new Timestamp(date.getTime()));

            session.saveOrUpdate(tramite);
            session.saveOrUpdate(tramite2);

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
