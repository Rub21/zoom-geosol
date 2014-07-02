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





CREATE OR REPLACE FUNCTION uspActualizarGeometries(
	_id character varying,
	_type character varying,
	_geom USER-DEFINED)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM geometries WHERE id <> _id) THEN

	UPDATE geometries
  SET 
	id=_id,
	type=_type,
	geom=_geom

 WHERE id=_id;


            RETURN (select max(id) from geometries)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





CREATE OR REPLACE FUNCTION uspInsertarGeometries(
	_type character varying,
	_geom USER-DEFINED)

  RETURNS integer AS
$BODY$
DECLARE _idgeometries integer;
DECLARE _retorno integer;
BEGIN	
	_id =(select max(id) from geometries)::integer;
	_id=_id+1;
	if(_id is null) then
	_id=1;
	end if;	
	INSERT INTO geometries(

	id,
	type,
	geom )

 VALUES (	_id,
	_type,
	_geom );


            _retorno =(select max(id) from geometries)::integer;
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





CREATE OR REPLACE FUNCTION uspActualizarVentas(
	_codigovendedor character varying,
	_fuerzaventas character varying,
	_grupoventas character varying,
	_codigocliente character varying,
	_codigozona character varying,
	_codigomesa character varying,
	_codigodia character varying,
	_fechafacturacion character varying,
	_tipodocumento character varying,
	_numerodocumento character varying,
	_codigoproducto character varying,
	_codigobonificacion character varying,
	_bonificacion character varying,
	_codigoalmacen character varying,
	_formaventa character varying,
	_anulado character varying,
	_motivoanulacion character varying,
	_tipotransaccion character varying,
	_cantidadunitaria integer,
	_cantidadformato numeric,
	_valorunitario numeric,
	_itemcostoventa numeric,
	_itemvalorventa numeric,
	_totalventa numeric,
	_codigovehiculo character varying,
	_fecha_time integer)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM ventas WHERE codigovendedor <> _codigovendedor) THEN

	UPDATE ventas
  SET 
	codigovendedor=_codigovendedor,
	fuerzaventas=_fuerzaventas,
	grupoventas=_grupoventas,
	codigocliente=_codigocliente,
	codigozona=_codigozona,
	codigomesa=_codigomesa,
	codigodia=_codigodia,
	fechafacturacion=_fechafacturacion,
	tipodocumento=_tipodocumento,
	numerodocumento=_numerodocumento,
	codigoproducto=_codigoproducto,
	codigobonificacion=_codigobonificacion,
	bonificacion=_bonificacion,
	codigoalmacen=_codigoalmacen,
	formaventa=_formaventa,
	anulado=_anulado,
	motivoanulacion=_motivoanulacion,
	tipotransaccion=_tipotransaccion,
	cantidadunitaria=_cantidadunitaria,
	cantidadformato=_cantidadformato,
	valorunitario=_valorunitario,
	itemcostoventa=_itemcostoventa,
	itemvalorventa=_itemvalorventa,
	totalventa=_totalventa,
	codigovehiculo=_codigovehiculo,
	fecha_time=_fecha_time

 WHERE codigovendedor=_codigovendedor;


            RETURN (select max(codigovendedor) from ventas)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





CREATE OR REPLACE FUNCTION uspInsertarVentas(
	_fuerzaventas character varying,
	_grupoventas character varying,
	_codigocliente character varying,
	_codigozona character varying,
	_codigomesa character varying,
	_codigodia character varying,
	_fechafacturacion character varying,
	_tipodocumento character varying,
	_numerodocumento character varying,
	_codigoproducto character varying,
	_codigobonificacion character varying,
	_bonificacion character varying,
	_codigoalmacen character varying,
	_formaventa character varying,
	_anulado character varying,
	_motivoanulacion character varying,
	_tipotransaccion character varying,
	_cantidadunitaria integer,
	_cantidadformato numeric,
	_valorunitario numeric,
	_itemcostoventa numeric,
	_itemvalorventa numeric,
	_totalventa numeric,
	_codigovehiculo character varying,
	_fecha_time integer)

  RETURNS integer AS
$BODY$
DECLARE _idventas integer;
DECLARE _retorno integer;
BEGIN	
	_codigovendedor =(select max(codigovendedor) from ventas)::integer;
	_codigovendedor=_codigovendedor+1;
	if(_codigovendedor is null) then
	_codigovendedor=1;
	end if;	
	INSERT INTO ventas(

	codigovendedor,
	fuerzaventas,
	grupoventas,
	codigocliente,
	codigozona,
	codigomesa,
	codigodia,
	fechafacturacion,
	tipodocumento,
	numerodocumento,
	codigoproducto,
	codigobonificacion,
	bonificacion,
	codigoalmacen,
	formaventa,
	anulado,
	motivoanulacion,
	tipotransaccion,
	cantidadunitaria,
	cantidadformato,
	valorunitario,
	itemcostoventa,
	itemvalorventa,
	totalventa,
	codigovehiculo,
	fecha_time )

 VALUES (	_codigovendedor,
	_fuerzaventas,
	_grupoventas,
	_codigocliente,
	_codigozona,
	_codigomesa,
	_codigodia,
	_fechafacturacion,
	_tipodocumento,
	_numerodocumento,
	_codigoproducto,
	_codigobonificacion,
	_bonificacion,
	_codigoalmacen,
	_formaventa,
	_anulado,
	_motivoanulacion,
	_tipotransaccion,
	_cantidadunitaria,
	_cantidadformato,
	_valorunitario,
	_itemcostoventa,
	_itemvalorventa,
	_totalventa,
	_codigovehiculo,
	_fecha_time );


            _retorno =(select max(codigovendedor) from ventas)::integer;
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





CREATE OR REPLACE FUNCTION uspActualizarProducto(
	_codigoproducto character varying,
	_grupo character varying,
	_familia character varying,
	_linea character varying,
	_nombremarca character varying,
	_productobase character varying,
	_codigonestle character varying,
	_nombreproducto character varying,
	_pesoneto numeric)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM producto WHERE codigoproducto <> _codigoproducto) THEN

	UPDATE producto
  SET 
	codigoproducto=_codigoproducto,
	grupo=_grupo,
	familia=_familia,
	linea=_linea,
	nombremarca=_nombremarca,
	productobase=_productobase,
	codigonestle=_codigonestle,
	nombreproducto=_nombreproducto,
	pesoneto=_pesoneto

 WHERE codigoproducto=_codigoproducto;


            RETURN (select max(codigoproducto) from producto)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





CREATE OR REPLACE FUNCTION uspInsertarProducto(
	_grupo character varying,
	_familia character varying,
	_linea character varying,
	_nombremarca character varying,
	_productobase character varying,
	_codigonestle character varying,
	_nombreproducto character varying,
	_pesoneto numeric)

  RETURNS integer AS
$BODY$
DECLARE _idproducto integer;
DECLARE _retorno integer;
BEGIN	
	_codigoproducto =(select max(codigoproducto) from producto)::integer;
	_codigoproducto=_codigoproducto+1;
	if(_codigoproducto is null) then
	_codigoproducto=1;
	end if;	
	INSERT INTO producto(

	codigoproducto,
	grupo,
	familia,
	linea,
	nombremarca,
	productobase,
	codigonestle,
	nombreproducto,
	pesoneto )

 VALUES (	_codigoproducto,
	_grupo,
	_familia,
	_linea,
	_nombremarca,
	_productobase,
	_codigonestle,
	_nombreproducto,
	_pesoneto );


            _retorno =(select max(codigoproducto) from producto)::integer;
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





CREATE OR REPLACE FUNCTION uspActualizarAlmacen(
	_codigoalmacen character varying,
	_nombrealmacen character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM almacen WHERE codigoalmacen <> _codigoalmacen) THEN

	UPDATE almacen
  SET 
	codigoalmacen=_codigoalmacen,
	nombrealmacen=_nombrealmacen

 WHERE codigoalmacen=_codigoalmacen;


            RETURN (select max(codigoalmacen) from almacen)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





CREATE OR REPLACE FUNCTION uspInsertarAlmacen(
	_nombrealmacen character varying)

  RETURNS integer AS
$BODY$
DECLARE _idalmacen integer;
DECLARE _retorno integer;
BEGIN	
	_codigoalmacen =(select max(codigoalmacen) from almacen)::integer;
	_codigoalmacen=_codigoalmacen+1;
	if(_codigoalmacen is null) then
	_codigoalmacen=1;
	end if;	
	INSERT INTO almacen(

	codigoalmacen,
	nombrealmacen )

 VALUES (	_codigoalmacen,
	_nombrealmacen );


            _retorno =(select max(codigoalmacen) from almacen)::integer;
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





CREATE OR REPLACE FUNCTION uspInsertarAnulado(
	_motivoanulacion character varying)

  RETURNS integer AS
$BODY$
DECLARE _idanulado integer;
DECLARE _retorno integer;
BEGIN	
	_codigoanulado =(select max(codigoanulado) from anulado)::integer;
	_codigoanulado=_codigoanulado+1;
	if(_codigoanulado is null) then
	_codigoanulado=1;
	end if;	
	INSERT INTO anulado(

	codigoanulado,
	motivoanulacion )

 VALUES (	_codigoanulado,
	_motivoanulacion );


            _retorno =(select max(codigoanulado) from anulado)::integer;
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





CREATE OR REPLACE FUNCTION uspActualizarCliente(
	_codigocliente character varying,
	_nombrecliente character varying,
	_direccion character varying,
	_distrito character varying,
	_categoriacliente character varying,
	_gironegocio character varying,
	_coordenada_y double precision,
	_coordenada_x double precision,
	_visual integer)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM cliente WHERE codigocliente <> _codigocliente) THEN

	UPDATE cliente
  SET 
	codigocliente=_codigocliente,
	nombrecliente=_nombrecliente,
	direccion=_direccion,
	distrito=_distrito,
	categoriacliente=_categoriacliente,
	gironegocio=_gironegocio,
	coordenada_y=_coordenada_y,
	coordenada_x=_coordenada_x,
	visual=_visual

 WHERE codigocliente=_codigocliente;


            RETURN (select max(codigocliente) from cliente)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





CREATE OR REPLACE FUNCTION uspInsertarCliente(
	_nombrecliente character varying,
	_direccion character varying,
	_distrito character varying,
	_categoriacliente character varying,
	_gironegocio character varying,
	_coordenada_y double precision,
	_coordenada_x double precision,
	_visual integer)

  RETURNS integer AS
$BODY$
DECLARE _idcliente integer;
DECLARE _retorno integer;
BEGIN	
	_codigocliente =(select max(codigocliente) from cliente)::integer;
	_codigocliente=_codigocliente+1;
	if(_codigocliente is null) then
	_codigocliente=1;
	end if;	
	INSERT INTO cliente(

	codigocliente,
	nombrecliente,
	direccion,
	distrito,
	categoriacliente,
	gironegocio,
	coordenada_y,
	coordenada_x,
	visual )

 VALUES (	_codigocliente,
	_nombrecliente,
	_direccion,
	_distrito,
	_categoriacliente,
	_gironegocio,
	_coordenada_y,
	_coordenada_x,
	_visual );


            _retorno =(select max(codigocliente) from cliente)::integer;
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





CREATE OR REPLACE FUNCTION uspActualizarCuotas_vendedor(
	_codigovendedorc character varying,
	_nombrevendedorc character varying,
	_fecha_cuota character varying,
	_anio_cuota integer,
	_mes_cuota character varying,
	_cuota_lacteos numeric,
	_cuota_cafe numeric,
	_cuota_bebidas numeric,
	_cuota_culinarios numeric)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM cuotas_vendedor WHERE codigovendedorc <> _codigovendedorc) THEN

	UPDATE cuotas_vendedor
  SET 
	codigovendedorc=_codigovendedorc,
	nombrevendedorc=_nombrevendedorc,
	fecha_cuota=_fecha_cuota,
	anio_cuota=_anio_cuota,
	mes_cuota=_mes_cuota,
	cuota_lacteos=_cuota_lacteos,
	cuota_cafe=_cuota_cafe,
	cuota_bebidas=_cuota_bebidas,
	cuota_culinarios=_cuota_culinarios

 WHERE codigovendedorc=_codigovendedorc;


            RETURN (select max(codigovendedorc) from cuotas_vendedor)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





CREATE OR REPLACE FUNCTION uspInsertarCuotas_vendedor(
	_nombrevendedorc character varying,
	_fecha_cuota character varying,
	_anio_cuota integer,
	_mes_cuota character varying,
	_cuota_lacteos numeric,
	_cuota_cafe numeric,
	_cuota_bebidas numeric,
	_cuota_culinarios numeric)

  RETURNS integer AS
$BODY$
DECLARE _idcuotas_vendedor integer;
DECLARE _retorno integer;
BEGIN	
	_codigovendedorc =(select max(codigovendedorc) from cuotas_vendedor)::integer;
	_codigovendedorc=_codigovendedorc+1;
	if(_codigovendedorc is null) then
	_codigovendedorc=1;
	end if;	
	INSERT INTO cuotas_vendedor(

	codigovendedorc,
	nombrevendedorc,
	fecha_cuota,
	anio_cuota,
	mes_cuota,
	cuota_lacteos,
	cuota_cafe,
	cuota_bebidas,
	cuota_culinarios )

 VALUES (	_codigovendedorc,
	_nombrevendedorc,
	_fecha_cuota,
	_anio_cuota,
	_mes_cuota,
	_cuota_lacteos,
	_cuota_cafe,
	_cuota_bebidas,
	_cuota_culinarios );


            _retorno =(select max(codigovendedorc) from cuotas_vendedor)::integer;
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





CREATE OR REPLACE FUNCTION uspActualizarFecha(
	_dias_facturados_anio integer,
	_dias_facturados_mes integer,
	_fechafacturacion character varying,
	_anio integer,
	_semestre character varying,
	_trimestre character varying,
	_bimestre character varying,
	_mes_num integer,
	_mes_desc character varying,
	_mes_anio character varying,
	_semana integer,
	_semana_anio character varying,
	_dia_nombre character varying,
	_dia_semana integer,
	_mes_anio_balance character varying,
	_mes_anio_gasto character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM fecha WHERE dias_facturados_anio <> _dias_facturados_anio) THEN

	UPDATE fecha
  SET 
	dias_facturados_anio=_dias_facturados_anio,
	dias_facturados_mes=_dias_facturados_mes,
	fechafacturacion=_fechafacturacion,
	anio=_anio,
	semestre=_semestre,
	trimestre=_trimestre,
	bimestre=_bimestre,
	mes_num=_mes_num,
	mes_desc=_mes_desc,
	mes_anio=_mes_anio,
	semana=_semana,
	semana_anio=_semana_anio,
	dia_nombre=_dia_nombre,
	dia_semana=_dia_semana,
	mes_anio_balance=_mes_anio_balance,
	mes_anio_gasto=_mes_anio_gasto

 WHERE dias_facturados_anio=_dias_facturados_anio;


            RETURN (select max(dias_facturados_anio) from fecha)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





CREATE OR REPLACE FUNCTION uspInsertarFecha(
	_dias_facturados_mes integer,
	_fechafacturacion character varying,
	_anio integer,
	_semestre character varying,
	_trimestre character varying,
	_bimestre character varying,
	_mes_num integer,
	_mes_desc character varying,
	_mes_anio character varying,
	_semana integer,
	_semana_anio character varying,
	_dia_nombre character varying,
	_dia_semana integer,
	_mes_anio_balance character varying,
	_mes_anio_gasto character varying)

  RETURNS integer AS
$BODY$
DECLARE _idfecha integer;
DECLARE _retorno integer;
BEGIN	
	_dias_facturados_anio =(select max(dias_facturados_anio) from fecha)::integer;
	_dias_facturados_anio=_dias_facturados_anio+1;
	if(_dias_facturados_anio is null) then
	_dias_facturados_anio=1;
	end if;	
	INSERT INTO fecha(

	dias_facturados_anio,
	dias_facturados_mes,
	fechafacturacion,
	anio,
	semestre,
	trimestre,
	bimestre,
	mes_num,
	mes_desc,
	mes_anio,
	semana,
	semana_anio,
	dia_nombre,
	dia_semana,
	mes_anio_balance,
	mes_anio_gasto )

 VALUES (	_dias_facturados_anio,
	_dias_facturados_mes,
	_fechafacturacion,
	_anio,
	_semestre,
	_trimestre,
	_bimestre,
	_mes_num,
	_mes_desc,
	_mes_anio,
	_semana,
	_semana_anio,
	_dia_nombre,
	_dia_semana,
	_mes_anio_balance,
	_mes_anio_gasto );


            _retorno =(select max(dias_facturados_anio) from fecha)::integer;
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





CREATE OR REPLACE FUNCTION uspInsertarGrupoventa(
	_grupoventas character varying,
	_canal character varying)

  RETURNS integer AS
$BODY$
DECLARE _idgrupoventa integer;
DECLARE _retorno integer;
BEGIN	
	_codigogrupoventa =(select max(codigogrupoventa) from grupoventa)::integer;
	_codigogrupoventa=_codigogrupoventa+1;
	if(_codigogrupoventa is null) then
	_codigogrupoventa=1;
	end if;	
	INSERT INTO grupoventa(

	codigogrupoventa,
	grupoventas,
	canal )

 VALUES (	_codigogrupoventa,
	_grupoventas,
	_canal );


            _retorno =(select max(codigogrupoventa) from grupoventa)::integer;
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





CREATE OR REPLACE FUNCTION uspActualizarVehiculo(
	_codigovehiculo character varying,
	_placa character varying,
	_nombrevehiculo character varying,
	_nombreconductor character varying,
	_nombretransportista character varying)

  RETURNS integer AS
$BODY$
BEGIN	
--IF  EXISTS (SELECT 1 FROM vehiculo WHERE codigovehiculo <> _codigovehiculo) THEN

	UPDATE vehiculo
  SET 
	codigovehiculo=_codigovehiculo,
	placa=_placa,
	nombrevehiculo=_nombrevehiculo,
	nombreconductor=_nombreconductor,
	nombretransportista=_nombretransportista

 WHERE codigovehiculo=_codigovehiculo;


            RETURN (select max(codigovehiculo) from vehiculo)::integer;
--END IF;
		--;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;





CREATE OR REPLACE FUNCTION uspInsertarVehiculo(
	_placa character varying,
	_nombrevehiculo character varying,
	_nombreconductor character varying,
	_nombretransportista character varying)

  RETURNS integer AS
$BODY$
DECLARE _idvehiculo integer;
DECLARE _retorno integer;
BEGIN	
	_codigovehiculo =(select max(codigovehiculo) from vehiculo)::integer;
	_codigovehiculo=_codigovehiculo+1;
	if(_codigovehiculo is null) then
	_codigovehiculo=1;
	end if;	
	INSERT INTO vehiculo(

	codigovehiculo,
	placa,
	nombrevehiculo,
	nombreconductor,
	nombretransportista )

 VALUES (	_codigovehiculo,
	_placa,
	_nombrevehiculo,
	_nombreconductor,
	_nombretransportista );


            _retorno =(select max(codigovehiculo) from vehiculo)::integer;
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





