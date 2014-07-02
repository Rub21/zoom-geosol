CREATE OR REPLACE FUNCTION uspActualizarGrupoventa(
	_codigogrupoventa character varying,
	_grupoventas character varying,
	_canal character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM grupoventa WHERE codigogrupoventa <> _codigogrupoventa) THEN

	UPDATE grupoventa
  SET 
	codigogrupoventa=_codigogrupoventa,
	grupoventas=_grupoventas,
	canal=_canal

 WHERE codigogrupoventa=_codigogrupoventa;


            RETURN (select max(codigogrupoventa) from grupoventa)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





