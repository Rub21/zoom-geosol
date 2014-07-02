CREATE OR REPLACE FUNCTION uspActualizarAnulado(
	_codigoanulado character varying,
	_motivoanulacion character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM anulado WHERE codigoanulado <> _codigoanulado) THEN

	UPDATE anulado
  SET 
	codigoanulado=_codigoanulado,
	motivoanulacion=_motivoanulacion

 WHERE codigoanulado=_codigoanulado;


            RETURN (select max(codigoanulado) from anulado)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





