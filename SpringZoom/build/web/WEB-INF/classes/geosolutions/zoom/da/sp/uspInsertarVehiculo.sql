CREATE OR REPLACE FUNCTION uspInsertarVehiculo(
	_placa character varying,
	_nombrevehiculo character varying,
	_nombreconductor character varying,
	_nombretransportista character varying)

  RETURNS integer AS
$BODY$
DECLARE _idvehiculo integer;
DECLARE _retorno integer;
BEGIN	
	_codigovehiculo =(select max(codigovehiculo) from vehiculo)::integer;
	_codigovehiculo=_codigovehiculo+1;
	if(_codigovehiculo is null) then
	_codigovehiculo=1;
	end if;	
	INSERT INTO vehiculo(

	codigovehiculo,
	placa,
	nombrevehiculo,
	nombreconductor,
	nombretransportista )

 VALUES (	_codigovehiculo,
	_placa,
	_nombrevehiculo,
	_nombreconductor,
	_nombretransportista );


            _retorno =(select max(codigovehiculo) from vehiculo)::integer;
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





