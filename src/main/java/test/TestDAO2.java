package test;

import domain.Imagen;
import service.ImagenService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDAO2 {
    //implementa al menos dos metodos y pruebalos
    public static void main(String[] args) {
        ImagenService imagenService = new ImagenService();

        imagenService.save(new Imagen("www.carricoche.com/a4patas.png",new Timestamp(new Date().getTime())));

        List<Imagen>imagenList = new ArrayList<>();
        imagenList = imagenService.getAllImagen();
        imagenList.forEach(System.out::println); //casi me pillas con el fetch

        Imagen img = imagenService.getImagenById(4);
        System.out.println(img.toString());

    }
}
