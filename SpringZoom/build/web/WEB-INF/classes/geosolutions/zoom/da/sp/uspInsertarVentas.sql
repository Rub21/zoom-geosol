CREATE OR REPLACE FUNCTION uspInsertarVentas(
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
DECLARE _idventas integer;
DECLARE _retorno integer;
BEGIN	
	_codigovendedor =(select max(codigovendedor) from ventas)::integer;
	_codigovendedor=_codigovendedor+1;
	if(_codigovendedor is null) then
	_codigovendedor=1;
	end if;	
	INSERT INTO ventas(

	codigovendedor,
	fuerzaventas,
	grupoventas,
	codigocliente,
	codigozona,
	codigomesa,
	codigodia,
	fechafacturacion,
	tipodocumento,
	numerodocumento,
	codigoproducto,
	codigobonificacion,
	bonificacion,
	codigoalmacen,
	formaventa,
	anulado,
	motivoanulacion,
	tipotransaccion,
	cantidadunitaria,
	cantidadformato,
	valorunitario,
	itemcostoventa,
	itemvalorventa,
	totalventa,
	codigovehiculo,
	fecha_time )

 VALUES (	_codigovendedor,
	_fuerzaventas,
	_grupoventas,
	_codigocliente,
	_codigozona,
	_codigomesa,
	_codigodia,
	_fechafacturacion,
	_tipodocumento,
	_numerodocumento,
	_codigoproducto,
	_codigobonificacion,
	_bonificacion,
	_codigoalmacen,
	_formaventa,
	_anulado,
	_motivoanulacion,
	_tipotransaccion,
	_cantidadunitaria,
	_cantidadformato,
	_valorunitario,
	_itemcostoventa,
	_itemvalorventa,
	_totalventa,
	_codigovehiculo,
	_fecha_time );


            _retorno =(select max(codigovendedor) from ventas)::integer;
		RETURN _retorno;
	--ELSE
	EXCEPTION
     WHEN unique_violation THEN
          RETURN 0;
     WHEN others THEN
	   RETURN 0;
			--END IF;
		--SELECT 1 existe FROM alumno WHERE ced_alu = 2034565;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





