CREATE OR REPLACE FUNCTION uspActualizarProducto(
	_codigoproducto character varying,
	_grupo character varying,
	_familia character varying,
	_linea character varying,
	_nombremarca character varying,
	_productobase character varying,
	_codigonestle character varying,
	_nombreproducto character varying,
	_pesoneto numeric)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM producto WHERE codigoproducto <> _codigoproducto) THEN

	UPDATE producto
  SET 
	codigoproducto=_codigoproducto,
	grupo=_grupo,
	familia=_familia,
	linea=_linea,
	nombremarca=_nombremarca,
	productobase=_productobase,
	codigonestle=_codigonestle,
	nombreproducto=_nombreproducto,
	pesoneto=_pesoneto

 WHERE codigoproducto=_codigoproducto;


            RETURN (select max(codigoproducto) from producto)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





