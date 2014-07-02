CREATE OR REPLACE FUNCTION uspActualizarVendedor(
	_codigovendedor character varying,
	_nombrevendedor character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM vendedor WHERE codigovendedor <> _codigovendedor) THEN

	UPDATE vendedor
  SET 
	codigovendedor=_codigovendedor,
	nombrevendedor=_nombrevendedor

 WHERE codigovendedor=_codigovendedor;


            RETURN (select max(codigovendedor) from vendedor)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





