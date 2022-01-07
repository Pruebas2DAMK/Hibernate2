package domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Inmueble.class)
public abstract class Inmueble_ {

	public static volatile SingularAttribute<Inmueble, String> tipo;
	public static volatile ListAttribute<Inmueble, Imagen> imagenes;
	public static volatile SingularAttribute<Inmueble, String> domicilio;
	public static volatile SingularAttribute<Inmueble, Integer> id;

	public static final String TIPO = "tipo";
	public static final String IMAGENES = "imagenes";
	public static final String DOMICILIO = "domicilio";
	public static final String ID = "id";

}

