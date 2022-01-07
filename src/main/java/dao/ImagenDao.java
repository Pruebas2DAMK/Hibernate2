package dao;

import domain.Imagen;
import domain.Inmueble;

import java.util.List;

public interface ImagenDao {

    void save(Imagen imagen);
    Imagen findById(int id);
    List<Inmueble> findAllInmueblesByImagen(Imagen imagen);
    List<Imagen>findAll();
}
