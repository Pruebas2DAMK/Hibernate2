package domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Presupuesto.class)
public abstract class Presupuesto_ {

	public static volatile SingularAttribute<Presupuesto, Tramite> tramite;
	public static volatile SingularAttribute<Presupuesto, String> lugar;
	public static volatile SingularAttribute<Presupuesto, Integer> id;

	public static final String TRAMITE = "tramite";
	public static final String LUGAR = "lugar";
	public static final String ID = "id";

}

