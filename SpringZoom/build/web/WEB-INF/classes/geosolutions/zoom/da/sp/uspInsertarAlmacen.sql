CREATE OR REPLACE FUNCTION uspInsertarAlmacen(
	_nombrealmacen character varying)

  RETURNS integer AS
$BODY$
DECLARE _idalmacen integer;
DECLARE _retorno integer;
BEGIN	
	_codigoalmacen =(select max(codigoalmacen) from almacen)::integer;
	_codigoalmacen=_codigoalmacen+1;
	if(_codigoalmacen is null) then
	_codigoalmacen=1;
	end if;	
	INSERT INTO almacen(

	codigoalmacen,
	nombrealmacen )

 VALUES (	_codigoalmacen,
	_nombrealmacen );


            _retorno =(select max(codigoalmacen) from almacen)::integer;
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





