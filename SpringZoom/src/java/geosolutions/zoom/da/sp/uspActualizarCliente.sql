CREATE OR REPLACE FUNCTION uspActualizarCliente(
	_codigocliente character varying,
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
BEGIN	
--IF  EXISTS (SELECT 1 FROM cliente WHERE codigocliente <> _codigocliente) THEN

	UPDATE cliente
  SET 
	codigocliente=_codigocliente,
	nombrecliente=_nombrecliente,
	direccion=_direccion,
	distrito=_distrito,
	categoriacliente=_categoriacliente,
	gironegocio=_gironegocio,
	coordenada_y=_coordenada_y,
	coordenada_x=_coordenada_x,
	visual=_visual

 WHERE codigocliente=_codigocliente;


            RETURN (select max(codigocliente) from cliente)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





