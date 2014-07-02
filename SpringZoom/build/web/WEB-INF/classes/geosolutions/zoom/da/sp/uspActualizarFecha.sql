CREATE OR REPLACE FUNCTION uspActualizarFecha(
	_dias_facturados_anio integer,
	_dias_facturados_mes integer,
	_fechafacturacion character varying,
	_anio integer,
	_semestre character varying,
	_trimestre character varying,
	_bimestre character varying,
	_mes_num integer,
	_mes_desc character varying,
	_mes_anio character varying,
	_semana integer,
	_semana_anio character varying,
	_dia_nombre character varying,
	_dia_semana integer,
	_mes_anio_balance character varying,
	_mes_anio_gasto character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM fecha WHERE dias_facturados_anio <> _dias_facturados_anio) THEN

	UPDATE fecha
  SET 
	dias_facturados_anio=_dias_facturados_anio,
	dias_facturados_mes=_dias_facturados_mes,
	fechafacturacion=_fechafacturacion,
	anio=_anio,
	semestre=_semestre,
	trimestre=_trimestre,
	bimestre=_bimestre,
	mes_num=_mes_num,
	mes_desc=_mes_desc,
	mes_anio=_mes_anio,
	semana=_semana,
	semana_anio=_semana_anio,
	dia_nombre=_dia_nombre,
	dia_semana=_dia_semana,
	mes_anio_balance=_mes_anio_balance,
	mes_anio_gasto=_mes_anio_gasto

 WHERE dias_facturados_anio=_dias_facturados_anio;


            RETURN (select max(dias_facturados_anio) from fecha)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





