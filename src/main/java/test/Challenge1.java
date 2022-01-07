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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Challenge1 {
    //Consultar los tramites que tengan la palabra 'proyecto' y que se hayan creado antes de 'x' fecha
    public static void main(String[] args) throws ParseException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //fecha
        String patronFecha = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(patronFecha);
        Date date = sdf.parse("2022-01-07 19:40:00"); //la fecha predefinida
        Timestamp ts = new Timestamp(date.getTime());
        try{
            //construccion consulta
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
            //entidad consulta
            Root<Tramite> root = criteria.from(Tramite.class);

            //Consulta
            criteria.select(root)
                    .where(builder.like(root.get(Tramite_.tipoTramite), "%Proyecto%")
                            ,builder.lessThan(root.get(Tramite_.fechaTramite),ts) ); //funciona

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


            //Consulta por palabra y fecha

            /*

            criteria.select(root)
                    .where(builder.like(root.get(Tramite_.tipoTramite), "%Credito%")
                    );

             */

}
