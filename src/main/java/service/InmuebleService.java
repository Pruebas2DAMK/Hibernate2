package service;

import dao.InmuebleDao;
import dao.InmuebleDaoImpl;
import domain.Imagen;
import domain.Inmueble;

import java.util.List;

//La capa de servicio sirve para añadir logica al codigo si es necesario
public class InmuebleService {

    private InmuebleDao inmuebleDao;

    public InmuebleService(){
        inmuebleDao = new InmuebleDaoImpl();
    }

    public Inmueble getInmuebleById(int id){
        return inmuebleDao.findById(id);
    }

    public void create(Inmueble inmueble, List<Imagen> imgs){
        if (imgs!=null){
            for (Imagen imagen:imgs
                 ) {
                inmueble.getImagenes().add(imagen);
            }
        }
        inmuebleDao.save(inmueble);
    }
}
