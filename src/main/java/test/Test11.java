package test;

import domain.Presupuesto;
import domain.Tramite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class Test11 {
    //Borrar one to one
    public static void main(String[] args) {
        //eliminar el presupuesto con id 1
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            Presupuesto presupuesto = session.load(Presupuesto.class, 1);
           // session.delete(presupuesto); // Borrado sin afectar al tramite , hasta que se le asigne al pojo borrado en cascada
            //Borrar el tramite 2 y los presupuestos asociados a este, para ello ya estara configurado el borrado en cascada en @onetoone
            //Presupuesto presupuesto1 = new Presupuesto("Calle San Andres"); //creamos
            Tramite tramite = session.load(Tramite.class,2); //cargamos
            //presupuesto1.setTramite(tramite); //asignamos

            //subido a la base de datos
            //session.saveOrUpdate(tramite);
            //session.saveOrUpdate(presupuesto1);

            //borrado en cascada
            session.delete(tramite); //Proxima ejecucion tendra que fallar, se comenta lo anterior


            transaction.commit();
        }catch(Exception e){
            if (session != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }


    }
}
