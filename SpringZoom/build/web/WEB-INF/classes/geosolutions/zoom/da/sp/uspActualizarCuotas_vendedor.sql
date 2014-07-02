CREATE OR REPLACE FUNCTION uspActualizarCuotas_vendedor(
	_codigovendedorc character varying,
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
BEGIN	
--IF  EXISTS (SELECT 1 FROM cuotas_vendedor WHERE codigovendedorc <> _codigovendedorc) THEN

	UPDATE cuotas_vendedor
  SET 
	codigovendedorc=_codigovendedorc,
	nombrevendedorc=_nombrevendedorc,
	fecha_cuota=_fecha_cuota,
	anio_cuota=_anio_cuota,
	mes_cuota=_mes_cuota,
	cuota_lacteos=_cuota_lacteos,
	cuota_cafe=_cuota_cafe,
	cuota_bebidas=_cuota_bebidas,
	cuota_culinarios=_cuota_culinarios

 WHERE codigovendedorc=_codigovendedorc;


            RETURN (select max(codigovendedorc) from cuotas_vendedor)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





