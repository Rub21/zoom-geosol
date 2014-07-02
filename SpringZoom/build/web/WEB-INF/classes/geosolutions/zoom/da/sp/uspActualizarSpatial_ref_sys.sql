CREATE OR REPLACE FUNCTION uspActualizarSpatial_ref_sys(
	_srid integer,
	_auth_name character varying,
	_auth_srid integer,
	_srtext character varying,
	_proj4text character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM spatial_ref_sys WHERE srid <> _srid) THEN

	UPDATE spatial_ref_sys
  SET 
	srid=_srid,
	auth_name=_auth_name,
	auth_srid=_auth_srid,
	srtext=_srtext,
	proj4text=_proj4text

 WHERE srid=_srid;


            RETURN (select max(srid) from spatial_ref_sys)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





