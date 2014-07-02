<%-- 
    Document   : newjsp
    Created on : May 1, 2014, 7:17:36 PM
    Author     : ruben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width">
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Geosolution</title>

        <script>
            var urlApp = '${pageContext.request.contextPath}';
        </script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/boostrap/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/leaflet/leaflet.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/leafletDraw/leaflet.draw.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/MarkerCluster/MarkerCluster.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/PruneCluster/PruneCluster.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/MarkerCluster/MarkerCluster.Default.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/font-awesome-4.0.3/css/font-awesome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/leaflet-sidebar/L.Control.Sidebar.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/Leaflet_MiniMap/Control.MiniMap.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/leaflet.measure/Control.Measure.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/Control.OSMGeocoder/Control.OSMGeocoder.css">
        <!--<link rel="stylesheet" href=="${pageContext.request.contextPath}/js/Libs/sticky/sticky.full.css">-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/themes/redmond/minified/jquery-ui.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/jqgrid/css/ui.jqgrid.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/contextmenu/leaflet.contextmenu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/Libs/BootstrapDateRangePicker/daterangepicker-bs3.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_draw.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="assets/js/html5shiv.js"></script>
            <script src="assets/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/img/logo.png" class="img-responsive" alt="Image">  </img></a>
            </div>
            <div class="navbar-collapse collapse">

                <form class="navbar-form navbar-right" role="search">

                    <a class="navbar-brand" id="btnRefresh"  href="javascript:location.reload();"><img src="${pageContext.request.contextPath}/img/refresh.png" class="img-responsive" alt="Image">  </img></a>

                    <div class="form-group has-feedback navbar-right">

                        <div class="geolocation">
                            <a href="#" title="Encontrarme" onclick="geolocalizar()"></a>
                        </div>
                        <input id="searchbox" type="text" placeholder="Buscar" class="form-control input-sm">
                        <span class="add-on"><i class="icon-user icon-black"></i></span>
                    </div>
                </form>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"  id="MenuCliente" onclick=""><i class="fa fa-male" style="color: white"></i>&nbsp;&nbsp;Clientes <!--<b class="caret">--></b></a>
                    </li>

                    <li class="dropdown">
                        <a  href="#" role="button" class="dropdown-toggle" data-toggle="dropdown" id="MenuVenta"><i class="fa fa-building-o" style="color: white"></i>&nbsp;&nbsp;Ventas <!--<b class="caret"></b>--></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"  style="display: none" id="MenuGeometria" onclick=""><i class="fa fa-square-o" style="color: white"></i>&nbsp;&nbsp;Geometria <!--<b class="caret">--></b></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"   id="MenuZona" onclick=""><i class="fa fa-square-o" style="color: white"></i>&nbsp;&nbsp;Zonas <!--<b class="caret">--></b></a>
                    </li>
                </ul>
            </div><!--/.navbar-collapse -->
        </div>



        <!--/.menu en draw cuando se dibuja un menu derecho -->
        <div id="context_dibujar">
            <ul id="contextMenu" class="dropdown-menu" role="menu" style="display:block" >
                <li><a tabindex="-1" href="#" id="ctx_guardar_geom">Guardar geometria</a></li>
                <li><a tabindex="-1" href="#" id="ctx_consultar_data">Consultar Datos</a></li>
                <li><a tabindex="-1" href="#" id="ctx_consultar_data_filtrada">Consultar Datos Filtrados</a></li>
                <li class="divider"></li>
                <li><a tabindex="-1" href="#">Otros</a></li>
            </ul>
        </div>


        <div  style="display: none">
            <input type="text"  id='id_geom_lista'   >
        </div>

        <!--/.menu en cuando se consulta -->
        <div id="context_lista">
            <ul id="contextMenu_lista" class="dropdown-menu" role="menu" style="display:block" >
                <li id="li_actualizar_geometria" style="display: none"><a tabindex="-1" href="#" id="actualizar_geometria_lista">Actualizar Geometria</a></li>
                <li><a tabindex="-1" href="#" id="consultar_data_lista">Consultar Datos</a></li>
                <li><a tabindex="-1" href="#" id="eliminar_geometria_lista">Eliminar Geometria</a></li>
                <li><a tabindex="-1" href="#" id="modificar_geometria_lista">Modificar Geometria</a></li>
                <li class="divider"></li>
                <li><a tabindex="-1" href="#">Otros</a></li>
            </ul>

        </div>

        <div id="form_guardar_geom">
            <div class="container">

                <!-- Button to trigger modal para guardar geometria-->
                <div>
                    <a href="#myModal1_guardar_geom"  id="myModal1_guardar_geom_btn" role="button" class="btn" data-toggle="modal" style="display: none">Launch Modal</a>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="myModal1_guardar_geom" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Guadar Geomemetria</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form">
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input type="text" class="form-control input-title" name="nombre" id='id_mobre_geom'  placeholder='Geometria' >
                                    </div>
                                    <div class="form-group">
                                        <label>Descripcion</label>
                                        <textarea   id='id_descripcion_geom' class="form-control input-description">
                                            
                                        </textarea>
                                    </div>
                                    <div class="form-group form-group-color">
                                        <label>Color</label>
                                        <input type="color"  id='id_color_geom' class="form-control input-color" value="#C615A7">
                                    </div>
                                    <div class="form-group form-group-range">
                                        <label>Opacity</label>
                                        <input type="range"   id='id_opacity_geom'class="input-opacity" min="0" max="100">
                                    </div>

                                    <div class="form-group" >

                                        <input type="text" class="form-control input-title" style="display: none"  name="type" id='id_type_geom' >
                                    </div>

                                    <div class="form-group" >

                                        <input type="text" class="form-control input-title" style="display: none" name="valor" id='id_valor_geom'>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                <button type="button" class="btn btn-primary" id="guardar_geometria_modal">Guardar</button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->

            </div>

        </div>




        <div id="map"></div>
        <!--Siderbar busqueda de cliente  id="contentSiderbar" -->

        <!--<div class="inf_sidebar"><a href="#">&nbsp</a></div>-->

        <div id="sidebar">

            <div id="ContainerSidebar">
            </div>

        </div>

        <div id="ContainerModal">
        </div>




        <script src="${pageContext.request.contextPath}/js/Libs/jquery/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/jquery-ui-1.10.4/js/jquery-ui-1.10.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/Helper/app_gene.js"></script>
        <script src="${pageContext.request.contextPath}/js/HelperMaps/map_gene.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/boostrap/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/typeahead/typeahead.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/leaflet/leaflet.js"></script>

        <script src="${pageContext.request.contextPath}/js/Libs/MarkerCluster/leaflet.markercluster.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/PruneCluster/PruneCluster.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/Leaflet.heat/leaflet-heat.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/leaflet-sidebar/L.Control.Sidebar.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/Leaflet_MiniMap/Control.MiniMap.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/leafletDraw/leaflet.draw-src.js"></script>

        <script src="${pageContext.request.contextPath}/js/Libs/leaflet.measure/Control.Measure.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/Control.OSMGeocoder/Control.OSMGeocoder.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/LeafletFileLayer/leafletfilelayer.js"></script>

        <script src="${pageContext.request.contextPath}/js/Libs/leafletDraw/leaflet.draw.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/Leaflet-pip/leaflet-pip.js"></script>
        <!--<script src="${pageContext.request.contextPath}/js/Libs/leafletDraw/leaflet.draw.js"></script>-->
        <script src="${pageContext.request.contextPath}/js/Libs/contextmenu/leaflet.contextmenu.js"></script>

        <script src="${pageContext.request.contextPath}/js/Libs/sticky/sticky.full.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/jqgrid/js/i18n/grid.locale-es.js"></script>
        <script src="${pageContext.request.contextPath}/js/Libs/jqgrid/js/jquery.jqGrid.min.js"></script>

        <script src="https://dl.dropboxusercontent.com/u/43116811/geo/files/Common.js"></script>
        <script src="${pageContext.request.contextPath}/js/App/JSIndex.js"></script>

        <script src="${pageContext.request.contextPath}/js/App/data/LayerRutas.js"></script>
        <script src="${pageContext.request.contextPath}/js/App/data/LayerMesas.js"></script>
        <script src="${pageContext.request.contextPath}/js/App/data/LayerDias.js"></script>
        <script src="${pageContext.request.contextPath}/js/App/data/square00025.js"></script>
        <script src="${pageContext.request.contextPath}/js/App/data/square0005.js"></script>
         <script src="${pageContext.request.contextPath}/js/App/data/hexagono00025.js"></script>
        <script src="${pageContext.request.contextPath}/js/App/data/hexagono0005.js"></script>

        <script src="${pageContext.request.contextPath}/js/App/JSDraw_funciones.js"></script>
        <script src="${pageContext.request.contextPath}/js/App/JSDraw_dibujar.js"></script>
        <script src="${pageContext.request.contextPath}/js/App/JSDraw_app.js"></script>
        <script src="${pageContext.request.contextPath}/js/App/JSSquares.js"></script>



        <script src="${pageContext.request.contextPath}/js/App/JSMenu.js"></script>





    </body>
</html>





