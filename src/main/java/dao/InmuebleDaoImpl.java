package dao;

import domain.Imagen;
import domain.Imagen_;
import domain.Inmueble;
import domain.Inmueble_;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class InmuebleDaoImpl implements InmuebleDao{

    @Override
    public void save(Inmueble inmueble) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(inmueble);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Inmueble findById(int id) {
        Inmueble inmueble = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Inmueble> criteria = builder.createQuery(Inmueble.class);
            Root<Inmueble>root = criteria.from(Inmueble.class);

            criteria.where(
                    builder.equal(root.get(Inmueble_.id),id)
            );
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return inmueble;
    }

    @Override
    public List<Imagen> findAllImagesByInmueble(Inmueble inmueble) {
        List<Imagen>imagenes = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Imagen> criteria = builder.createQuery(Imagen.class);
            Root<Imagen>root = criteria.from(Imagen.class);

            Join<Imagen, Inmueble> join = root.join(Imagen_.inmuebles);

            criteria.where(
                    builder.equal(join.get(Inmueble_.id),inmueble.getId())
            );

            imagenes = session.createQuery(criteria).getResultList();
        }catch (HibernateException e){
            imagenes = null;
            e.printStackTrace();
        }
        return imagenes;
    }
}
