CREATE OR REPLACE FUNCTION uspActualizarFuerzaventas(
	_codigofuerzaventas character varying,
	_fuerzaventas character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM fuerzaventas WHERE codigofuerzaventas <> _codigofuerzaventas) THEN

	UPDATE fuerzaventas
  SET 
	codigofuerzaventas=_codigofuerzaventas,
	fuerzaventas=_fuerzaventas

 WHERE codigofuerzaventas=_codigofuerzaventas;


            RETURN (select max(codigofuerzaventas) from fuerzaventas)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





