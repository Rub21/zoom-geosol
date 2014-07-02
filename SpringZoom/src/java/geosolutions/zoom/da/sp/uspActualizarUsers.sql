CREATE OR REPLACE FUNCTION uspActualizarUsers(
	_id integer,
	_firstname character varying,
	_lastname character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM users WHERE id <> _id) THEN

	UPDATE users
  SET 
	id=_id,
	firstname=_firstname,
	lastname=_lastname

 WHERE id=_id;


            RETURN (select max(id) from users)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





