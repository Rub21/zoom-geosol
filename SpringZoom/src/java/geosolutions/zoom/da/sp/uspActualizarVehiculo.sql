CREATE OR REPLACE FUNCTION uspActualizarVehiculo(
	_codigovehiculo character varying,
	_placa character varying,
	_nombrevehiculo character varying,
	_nombreconductor character varying,
	_nombretransportista character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM vehiculo WHERE codigovehiculo <> _codigovehiculo) THEN

	UPDATE vehiculo
  SET 
	codigovehiculo=_codigovehiculo,
	placa=_placa,
	nombrevehiculo=_nombrevehiculo,
	nombreconductor=_nombreconductor,
	nombretransportista=_nombretransportista

 WHERE codigovehiculo=_codigovehiculo;


            RETURN (select max(codigovehiculo) from vehiculo)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





