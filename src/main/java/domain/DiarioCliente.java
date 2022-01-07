package domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "diariocliente")
public class DiarioCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddiarioCliente")
    private int id;
    @Column(name = "entradaDiario")
    private String entradaDiario;
    @Column(name = "fecha")
    private Timestamp fecha;
    @ManyToOne
    @JoinColumn(name = "Tramite_idTramite")
    private Tramite tramite;

    public DiarioCliente(){}

    public DiarioCliente(String s, Timestamp ts) {
        this.entradaDiario = s;
        this.fecha = ts;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEntradaDiario() {
        return entradaDiario;
    }
    public void setEntradaDiario(String entradaDiario) {
        this.entradaDiario = entradaDiario;
    }
    public Timestamp getFecha() {
        return fecha;
    }
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    public Tramite getTramite() {
        return tramite;
    }
    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    @Override
    public String toString() {
        return id + " " + entradaDiario + " " + fecha + " " + tramite;
    }
}
