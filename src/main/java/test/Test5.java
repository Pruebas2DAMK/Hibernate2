package test;


import domain.Tramite;
import domain.Tramite_;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Test5 {
    //update
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
                    .where(builder.equal(root.get(Tramite_.tipoTramite),"Aval"));

            Tramite tramite = session.createQuery(criteria).getSingleResult();
            tramite.setTipoTramite("Proyecto Software");

            session.update(tramite);

            transaction.commit();
        }catch (Exception e){
            if (session != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }

    }
}
