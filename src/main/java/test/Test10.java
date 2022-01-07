package test;

import domain.Presupuesto;
import domain.Tramite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Test10 {
    //Consultar tramite que pertenece a un presupuesto y consultar todos los tramites que aparecen en presupuesto
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Presupuesto> criteria = builder.createQuery(Presupuesto.class);
            Root<Presupuesto> root = criteria.from(Presupuesto.class);

            criteria.select(root); //select ALL

            Presupuesto presupuesto = session.load(Presupuesto.class,2);


            Tramite tramite = presupuesto.getTramite();
            List<Presupuesto> presupuestos = session.createQuery(criteria).getResultList();

            //seleccionamos para actualizarlo
            Presupuesto presupuesto1 = session.load(Presupuesto.class,1); //id 1
            presupuesto1.setLugar("Carrús - Elche");
            session.update(presupuesto1);

            System.out.println(tramite.toString());
            System.out.println("-------\nTODOS");
            presupuestos.forEach(System.out::println);
            System.out.println("-------\nActualizar 1");
            System.out.println(presupuesto1.toString());


        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
}
