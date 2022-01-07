package domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "imagen")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idImagen")
    private int id;
    @Column(name = "url")
    private String url;
    @Column(name = "fechaCreacion")
    private Timestamp fechaCreacion;

    //ManyToMany Bidireccional
    @ManyToMany(mappedBy = "imagenes")
    private List<Inmueble> inmuebles = new ArrayList<Inmueble>();

    public Imagen(){
    }

    public Imagen(String url, Timestamp ts) {
        this.url = url;
        this.fechaCreacion = ts;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }
    public void setInmuebles(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

    @Override
    public String toString() {
        return id + " " + url + " " + fechaCreacion + " " + inmuebles;
    }
}
