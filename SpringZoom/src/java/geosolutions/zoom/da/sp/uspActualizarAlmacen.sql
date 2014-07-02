CREATE OR REPLACE FUNCTION uspActualizarAlmacen(
	_codigoalmacen character varying,
	_nombrealmacen character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM almacen WHERE codigoalmacen <> _codigoalmacen) THEN

	UPDATE almacen
  SET 
	codigoalmacen=_codigoalmacen,
	nombrealmacen=_nombrealmacen

 WHERE codigoalmacen=_codigoalmacen;


            RETURN (select max(codigoalmacen) from almacen)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





