CREATE OR REPLACE FUNCTION uspInsertarCliente(
	_nombrecliente character varying,
	_direccion character varying,
	_distrito character varying,
	_categoriacliente character varying,
	_gironegocio character varying,
	_coordenada_y double precision,
	_coordenada_x double precision,
	_visual integer)

  RETURNS integer AS
$BODY$
DECLARE _idcliente integer;
DECLARE _retorno integer;
BEGIN	
	_codigocliente =(select max(codigocliente) from cliente)::integer;
	_codigocliente=_codigocliente+1;
	if(_codigocliente is null) then
	_codigocliente=1;
	end if;	
	INSERT INTO cliente(

	codigocliente,
	nombrecliente,
	direccion,
	distrito,
	categoriacliente,
	gironegocio,
	coordenada_y,
	coordenada_x,
	visual )

 VALUES (	_codigocliente,
	_nombrecliente,
	_direccion,
	_distrito,
	_categoriacliente,
	_gironegocio,
	_coordenada_y,
	_coordenada_x,
	_visual );


            _retorno =(select max(codigocliente) from cliente)::integer;
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





