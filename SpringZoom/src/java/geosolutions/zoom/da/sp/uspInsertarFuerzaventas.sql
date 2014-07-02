CREATE OR REPLACE FUNCTION uspInsertarFuerzaventas(
	_fuerzaventas character varying)

  RETURNS integer AS
$BODY$
DECLARE _idfuerzaventas integer;
DECLARE _retorno integer;
BEGIN	
	_codigofuerzaventas =(select max(codigofuerzaventas) from fuerzaventas)::integer;
	_codigofuerzaventas=_codigofuerzaventas+1;
	if(_codigofuerzaventas is null) then
	_codigofuerzaventas=1;
	end if;	
	INSERT INTO fuerzaventas(

	codigofuerzaventas,
	fuerzaventas )

 VALUES (	_codigofuerzaventas,
	_fuerzaventas );


            _retorno =(select max(codigofuerzaventas) from fuerzaventas)::integer;
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





