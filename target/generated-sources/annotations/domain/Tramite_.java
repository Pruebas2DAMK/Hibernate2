package domain;

import java.sql.Timestamp;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tramite.class)
public abstract class Tramite_ {

	public static volatile SingularAttribute<Tramite, Integer> idTramite;
	public static volatile SetAttribute<Tramite, DiarioCliente> diarioCliente;
	public static volatile SingularAttribute<Tramite, Presupuesto> presupuesto;
	public static volatile SingularAttribute<Tramite, Timestamp> fechaTramite;
	public static volatile SingularAttribute<Tramite, String> tipoTramite;

	public static final String ID_TRAMITE = "idTramite";
	public static final String DIARIO_CLIENTE = "diarioCliente";
	public static final String PRESUPUESTO = "presupuesto";
	public static final String FECHA_TRAMITE = "fechaTramite";
	public static final String TIPO_TRAMITE = "tipoTramite";

}

