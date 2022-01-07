package domain;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inmueble")
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInmueble")
    private int id;
    @Column(name = "tipo")
    String tipo;
    @Column(name = "domicilio")
    String domicilio;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinTable(
            name = "imagen_has_inmueble",
            joinColumns = @JoinColumn(name = "idImagen"),
            inverseJoinColumns =@JoinColumn(name = "idInmueble")
    )
    private List<Imagen> imagenes = new ArrayList<Imagen>();

    public Inmueble(){}

    public Inmueble(String tipo, String domicilio) {
        this.domicilio = domicilio;
        this.tipo = tipo;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }
    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return id + " " + tipo + " " + domicilio;
    }
}
