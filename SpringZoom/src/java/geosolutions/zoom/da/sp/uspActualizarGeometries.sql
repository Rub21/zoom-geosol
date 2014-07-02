CREATE OR REPLACE FUNCTION uspActualizarGeometries(
	_id character varying,
	_type character varying,
	_geom USER-DEFINED)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM geometries WHERE id <> _id) THEN

	UPDATE geometries
  SET 
	id=_id,
	type=_type,
	geom=_geom

 WHERE id=_id;


            RETURN (select max(id) from geometries)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





