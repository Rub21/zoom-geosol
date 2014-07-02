CREATE OR REPLACE FUNCTION uspInsertarGeometries(
	_type character varying,
	_geom USER-DEFINED)

  RETURNS integer AS
$BODY$
DECLARE _idgeometries integer;
DECLARE _retorno integer;
BEGIN	
	_id =(select max(id) from geometries)::integer;
	_id=_id+1;
	if(_id is null) then
	_id=1;
	end if;	
	INSERT INTO geometries(

	id,
	type,
	geom )

 VALUES (	_id,
	_type,
	_geom );


            _retorno =(select max(id) from geometries)::integer;
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





