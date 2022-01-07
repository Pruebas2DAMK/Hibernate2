package service;

import dao.ImagenDao;
import dao.ImagenDaoImpl;
import domain.Imagen;

import java.util.List;

public class ImagenService {
    private ImagenDao imagenDao;

    public ImagenService(){
        imagenDao = new ImagenDaoImpl();
    }

    public Imagen getImagenById(int id){
        return imagenDao.findById(id);
    }

    public List<Imagen> getAllImagen(){
        return imagenDao.findAll();
    }

    public void save(Imagen img){
        imagenDao.save(img);
    }
}
