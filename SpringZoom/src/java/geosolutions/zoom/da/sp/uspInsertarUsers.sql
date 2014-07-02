CREATE OR REPLACE FUNCTION uspInsertarUsers(
	_firstname character varying,
	_lastname character varying)

  RETURNS integer AS
$BODY$
DECLARE _idusers integer;
DECLARE _retorno integer;
BEGIN	
	_id =(select max(id) from users)::integer;
	_id=_id+1;
	if(_id is null) then
	_id=1;
	end if;	
	INSERT INTO users(

	id,
	firstname,
	lastname )

 VALUES (	_id,
	_firstname,
	_lastname );


            _retorno =(select max(id) from users)::integer;
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





