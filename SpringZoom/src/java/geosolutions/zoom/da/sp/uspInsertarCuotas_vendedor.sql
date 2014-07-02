CREATE OR REPLACE FUNCTION uspInsertarCuotas_vendedor(
	_nombrevendedorc character varying,
	_fecha_cuota character varying,
	_anio_cuota integer,
	_mes_cuota character varying,
	_cuota_lacteos numeric,
	_cuota_cafe numeric,
	_cuota_bebidas numeric,
	_cuota_culinarios numeric)

  RETURNS integer AS
$BODY$
DECLARE _idcuotas_vendedor integer;
DECLARE _retorno integer;
BEGIN	
	_codigovendedorc =(select max(codigovendedorc) from cuotas_vendedor)::integer;
	_codigovendedorc=_codigovendedorc+1;
	if(_codigovendedorc is null) then
	_codigovendedorc=1;
	end if;	
	INSERT INTO cuotas_vendedor(

	codigovendedorc,
	nombrevendedorc,
	fecha_cuota,
	anio_cuota,
	mes_cuota,
	cuota_lacteos,
	cuota_cafe,
	cuota_bebidas,
	cuota_culinarios )

 VALUES (	_codigovendedorc,
	_nombrevendedorc,
	_fecha_cuota,
	_anio_cuota,
	_mes_cuota,
	_cuota_lacteos,
	_cuota_cafe,
	_cuota_bebidas,
	_cuota_culinarios );


            _retorno =(select max(codigovendedorc) from cuotas_vendedor)::integer;
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





