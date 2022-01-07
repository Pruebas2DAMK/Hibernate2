package domain;

import java.sql.Timestamp;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DiarioCliente.class)
public abstract class DiarioCliente_ {

	public static volatile SingularAttribute<DiarioCliente, Timestamp> fecha;
	public static volatile SingularAttribute<DiarioCliente, Tramite> tramite;
	public static volatile SingularAttribute<DiarioCliente, String> entradaDiario;
	public static volatile SingularAttribute<DiarioCliente, Integer> id;

	public static final String FECHA = "fecha";
	public static final String TRAMITE = "tramite";
	public static final String ENTRADA_DIARIO = "entradaDiario";
	public static final String ID = "id";

}

