CREATE OR REPLACE FUNCTION uspInsertarSpatial_ref_sys(
	_auth_name character varying,
	_auth_srid integer,
	_srtext character varying,
	_proj4text character varying)

  RETURNS integer AS
$BODY$
DECLARE _idspatial_ref_sys integer;
DECLARE _retorno integer;
BEGIN	
	_srid =(select max(srid) from spatial_ref_sys)::integer;
	_srid=_srid+1;
	if(_srid is null) then
	_srid=1;
	end if;	
	INSERT INTO spatial_ref_sys(

	srid,
	auth_name,
	auth_srid,
	srtext,
	proj4text )

 VALUES (	_srid,
	_auth_name,
	_auth_srid,
	_srtext,
	_proj4text );


            _retorno =(select max(srid) from spatial_ref_sys)::integer;
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





