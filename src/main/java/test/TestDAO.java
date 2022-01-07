package test;

import domain.Imagen;
import domain.Inmueble;
import service.InmuebleService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDAO {
    //probar que el codigo funciona
    public static void main(String[] args) {
        InmuebleService inmuebleService = new InmuebleService();

        //Crear inmueble sin imagenes
        inmuebleService.create(new Inmueble("Terreno","Matola"),null);

        //Otro con imagenes
        List<Imagen> imgs = new ArrayList<>(List.of(
           new Imagen("www.google.com/img",new Timestamp(new Date().getTime())),
                new Imagen("www.yahoo.com/img",new Timestamp(new Date().getTime()))
        ));
        inmuebleService.create(new Inmueble("Piso","Plaza Barcelona"),imgs);
    }
}
