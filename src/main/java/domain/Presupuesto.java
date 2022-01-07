package domain;

import javax.persistence.*;

@Entity
@Table(name = "presupuesto")
public class Presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPresupuesto")
    private int id;
    @Column(name = "lugarPresupuesto")
    private String lugar;

    @OneToOne
    @JoinColumn(name = "Tramite_idTramite")
    private Tramite tramite;

    public Presupuesto(){
        //sin un constructor vacio cuidado con las querys ... ¯\_(-.-)_/¯ ... asi me he quedado despues de 15 minutos
    }

    public Presupuesto(String s){
        this.lugar = s;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString() {
        return id + " " + lugar;
    }

}
