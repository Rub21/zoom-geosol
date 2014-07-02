CREATE OR REPLACE FUNCTION uspInsertarAnulado(
	_motivoanulacion character varying)

  RETURNS integer AS
$BODY$
DECLARE _idanulado integer;
DECLARE _retorno integer;
BEGIN	
	_codigoanulado =(select max(codigoanulado) from anulado)::integer;
	_codigoanulado=_codigoanulado+1;
	if(_codigoanulado is null) then
	_codigoanulado=1;
	end if;	
	INSERT INTO anulado(

	codigoanulado,
	motivoanulacion )

 VALUES (	_codigoanulado,
	_motivoanulacion );


            _retorno =(select max(codigoanulado) from anulado)::integer;
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





