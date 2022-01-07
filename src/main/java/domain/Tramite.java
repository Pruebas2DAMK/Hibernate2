package domain;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@Entity
@Table(name = "tramite")
public class Tramite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTramite;
    private String tipoTramite;
    private Timestamp fechaTramite;

    //relacion bidireccional
    @OneToOne(mappedBy = "tramite")
    private Presupuesto presupuesto;

    public Tramite(){ }

    public Tramite(String tipoTramite, Timestamp timestamp) {
        this.tipoTramite = tipoTramite;
        this.fechaTramite = timestamp;
    }

    public int getIdTramite(){
        return idTramite;
    }

    public void setIdTramite(int idTramite) {
        this.idTramite = idTramite;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public Timestamp getFechaTramite() {
        return fechaTramite;
    }

    public void setFechaTramite(Timestamp fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    @Override
    public String toString() {
        return idTramite +" "+tipoTramite+" "+fechaTramite;
    }
}

