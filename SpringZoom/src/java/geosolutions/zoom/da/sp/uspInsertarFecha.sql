CREATE OR REPLACE FUNCTION uspInsertarFecha(
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
DECLARE _idfecha integer;
DECLARE _retorno integer;
BEGIN	
	_dias_facturados_anio =(select max(dias_facturados_anio) from fecha)::integer;
	_dias_facturados_anio=_dias_facturados_anio+1;
	if(_dias_facturados_anio is null) then
	_dias_facturados_anio=1;
	end if;	
	INSERT INTO fecha(

	dias_facturados_anio,
	dias_facturados_mes,
	fechafacturacion,
	anio,
	semestre,
	trimestre,
	bimestre,
	mes_num,
	mes_desc,
	mes_anio,
	semana,
	semana_anio,
	dia_nombre,
	dia_semana,
	mes_anio_balance,
	mes_anio_gasto )

 VALUES (	_dias_facturados_anio,
	_dias_facturados_mes,
	_fechafacturacion,
	_anio,
	_semestre,
	_trimestre,
	_bimestre,
	_mes_num,
	_mes_desc,
	_mes_anio,
	_semana,
	_semana_anio,
	_dia_nombre,
	_dia_semana,
	_mes_anio_balance,
	_mes_anio_gasto );


            _retorno =(select max(dias_facturados_anio) from fecha)::integer;
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





