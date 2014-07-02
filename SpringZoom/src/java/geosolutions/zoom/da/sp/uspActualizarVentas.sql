CREATE OR REPLACE FUNCTION uspActualizarVentas(
	_codigovendedor character varying,
	_fuerzaventas character varying,
	_grupoventas character varying,
	_codigocliente character varying,
	_codigozona character varying,
	_codigomesa character varying,
	_codigodia character varying,
	_fechafacturacion character varying,
	_tipodocumento character varying,
	_numerodocumento character varying,
	_codigoproducto character varying,
	_codigobonificacion character varying,
	_bonificacion character varying,
	_codigoalmacen character varying,
	_formaventa character varying,
	_anulado character varying,
	_motivoanulacion character varying,
	_tipotransaccion character varying,
	_cantidadunitaria integer,
	_cantidadformato numeric,
	_valorunitario numeric,
	_itemcostoventa numeric,
	_itemvalorventa numeric,
	_totalventa numeric,
	_codigovehiculo character varying,
	_fecha_time integer)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM ventas WHERE codigovendedor <> _codigovendedor) THEN

	UPDATE ventas
  SET 
	codigovendedor=_codigovendedor,
	fuerzaventas=_fuerzaventas,
	grupoventas=_grupoventas,
	codigocliente=_codigocliente,
	codigozona=_codigozona,
	codigomesa=_codigomesa,
	codigodia=_codigodia,
	fechafacturacion=_fechafacturacion,
	tipodocumento=_tipodocumento,
	numerodocumento=_numerodocumento,
	codigoproducto=_codigoproducto,
	codigobonificacion=_codigobonificacion,
	bonificacion=_bonificacion,
	codigoalmacen=_codigoalmacen,
	formaventa=_formaventa,
	anulado=_anulado,
	motivoanulacion=_motivoanulacion,
	tipotransaccion=_tipotransaccion,
	cantidadunitaria=_cantidadunitaria,
	cantidadformato=_cantidadformato,
	valorunitario=_valorunitario,
	itemcostoventa=_itemcostoventa,
	itemvalorventa=_itemvalorventa,
	totalventa=_totalventa,
	codigovehiculo=_codigovehiculo,
	fecha_time=_fecha_time

 WHERE codigovendedor=_codigovendedor;


            RETURN (select max(codigovendedor) from ventas)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





