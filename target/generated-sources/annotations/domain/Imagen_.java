package domain;

import java.sql.Timestamp;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Imagen.class)
public abstract class Imagen_ {

	public static volatile SingularAttribute<Imagen, Timestamp> fechaCreacion;
	public static volatile SingularAttribute<Imagen, Integer> id;
	public static volatile SingularAttribute<Imagen, String> url;
	public static volatile ListAttribute<Imagen, Inmueble> inmuebles;

	public static final String FECHA_CREACION = "fechaCreacion";
	public static final String ID = "id";
	public static final String URL = "url";
	public static final String INMUEBLES = "inmuebles";

}

