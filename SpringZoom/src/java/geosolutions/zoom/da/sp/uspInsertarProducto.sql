CREATE OR REPLACE FUNCTION uspInsertarProducto(
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
DECLARE _idproducto integer;
DECLARE _retorno integer;
BEGIN	
	_codigoproducto =(select max(codigoproducto) from producto)::integer;
	_codigoproducto=_codigoproducto+1;
	if(_codigoproducto is null) then
	_codigoproducto=1;
	end if;	
	INSERT INTO producto(

	codigoproducto,
	grupo,
	familia,
	linea,
	nombremarca,
	productobase,
	codigonestle,
	nombreproducto,
	pesoneto )

 VALUES (	_codigoproducto,
	_grupo,
	_familia,
	_linea,
	_nombremarca,
	_productobase,
	_codigonestle,
	_nombreproducto,
	_pesoneto );


            _retorno =(select max(codigoproducto) from producto)::integer;
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





