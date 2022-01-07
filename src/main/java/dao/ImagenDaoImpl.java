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

public class ImagenDaoImpl implements ImagenDao{
    @Override
    public void save(Imagen imagen) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(imagen);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Imagen findById(int id) {
        Imagen imagen = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Imagen> criteria = builder.createQuery(Imagen.class);
            Root<Imagen> root = criteria.from(Imagen.class);

            criteria.where(
                    builder.equal(root.get(Imagen_.id),id)
            );
            imagen = session.createQuery(criteria).getSingleResult();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return imagen;
    }

    @Override
    public List<Inmueble> findAllInmueblesByImagen(Imagen imagen) {
        List<Inmueble>inmuebles = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Inmueble> criteria = builder.createQuery(Inmueble.class);
            Root<Inmueble>root = criteria.from(Inmueble.class);

            Join<Inmueble, Imagen> join = root.join(Inmueble_.imagenes);

            criteria.where(
                    builder.equal(join.get(Imagen_.id),imagen.getId())
            );

            inmuebles = session.createQuery(criteria).getResultList();
        }catch (HibernateException e){
            inmuebles = null;
            e.printStackTrace();
        }
        return inmuebles;
    }

    @Override
    public List<Imagen> findAll() {
        List<Imagen>imagenes = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Imagen> criteria = builder.createQuery(Imagen.class);
            Root<Imagen>root = criteria.from(Imagen.class);


            criteria.select(root);

            imagenes = session.createQuery(criteria).getResultList();
        }catch (HibernateException e){
            imagenes = null;
            e.printStackTrace();
        }
        return imagenes;

    }
}
