CREATE OR REPLACE FUNCTION uspInsertarGrupoventa(
	_grupoventas character varying,
	_canal character varying)

  RETURNS integer AS
$BODY$
DECLARE _idgrupoventa integer;
DECLARE _retorno integer;
BEGIN	
	_codigogrupoventa =(select max(codigogrupoventa) from grupoventa)::integer;
	_codigogrupoventa=_codigogrupoventa+1;
	if(_codigogrupoventa is null) then
	_codigogrupoventa=1;
	end if;	
	INSERT INTO grupoventa(

	codigogrupoventa,
	grupoventas,
	canal )

 VALUES (	_codigogrupoventa,
	_grupoventas,
	_canal );


            _retorno =(select max(codigogrupoventa) from grupoventa)::integer;
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





