CREATE OR REPLACE FUNCTION uspInsertarVendedor(
	_nombrevendedor character varying)

  RETURNS integer AS
$BODY$
DECLARE _idvendedor integer;
DECLARE _retorno integer;
BEGIN	
	_codigovendedor =(select max(codigovendedor) from vendedor)::integer;
	_codigovendedor=_codigovendedor+1;
	if(_codigovendedor is null) then
	_codigovendedor=1;
	end if;	
	INSERT INTO vendedor(

	codigovendedor,
	nombrevendedor )

 VALUES (	_codigovendedor,
	_nombrevendedor );


            _retorno =(select max(codigovendedor) from vendedor)::integer;
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





