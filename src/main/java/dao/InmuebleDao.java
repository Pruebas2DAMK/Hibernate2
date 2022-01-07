package dao;

import domain.Imagen;
import domain.Inmueble;

import java.util.List;

public interface InmuebleDao {

    void save(Inmueble inmueble);
    Inmueble findById(int id);
    List<Imagen> findAllImagesByInmueble(Inmueble inmueble);
}
