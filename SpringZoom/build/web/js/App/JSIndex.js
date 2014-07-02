/*__________________________VARIABLES GLOBALES___________________________________*/

var CapaLayers = null;
var sidebar = null;



/*__________________________INICIO DE LA PAGINA__________________________*/
﻿$(document).on('ready', function() {
    
    initConfig();
    cargarMapa();
    cargarCapasMapa();
    miniMaper();
    CreateDraw();
//    $('.hide_sidebar').click(function() {
//        $('.sidebar').hide(200);
//        $('.inf_sidebar').css('display', 'block');
//        //control map
//        $('#map .leaflet-left .leaflet-control').css('top', '85px');
//        $('#map .leaflet-left .leaflet-control').css('left', '-1px');
//        $('#map .leaflet-left .leaflet-control').css('margin-bottom', '30px');
//        $('#map .leaflet-left .leaflet-control-scale').css('margin-left', '5px');
//
//    });
//
//    $('.inf_sidebar').click(function() {
//        $('.sidebar').show(200);
//        $('.inf_sidebar').css('display', 'none');
//        $('#map .leaflet-left .leaflet-control').css('top', '45px');
//        $('#map .leaflet-left .leaflet-control').css('left', '300px');
//        $('#map .leaflet-left .leaflet-control').css('margin-bottom', '15px');
//        $('#map .leaflet-left .leaflet-control-scale').css('margin-left', '300px');
//    });
//
//
//    $('#map .leaflet-right .leaflet-control').css('top', '45px');
//    $('.hide_sidebar').click();
    //$('.leaflet-control-minimap-toggle-display').click();
    $('.leaflet-control-minimap').css('top', '10px');
    $('.close').click(function() {
        $('.ocultarSiderbar').show();
    });
    $('#hrefocultarSiderbar').click(function() {
        sidebar.toggle();
        $('.ocultarSiderbar').hide();
    });
});

/*__________________CARGAR MAPA GLOBAL______________________*/
/*funcion para la verificacion de la base de datos */

function initConfig(){
      $.ajaxGeoSolution('LoadController/init.htm', {}, false, function(response) {
          console.log(response);  
          if(response==='false')
              alert('error en la coneccion de la base de datos');
              
        });

}

/*Funcion para la carga del mapa pricipal*/
/*funciones para manejo de mapas */
function cargarMapa() {
    // create a map in the "map" div, set the view to a given place and zoom
    map = L.map('map',
            {center: ll,
                zoom: 15,
                contextmenu: true,
                contextmenuWidth: 140,
                contextmenuItems: [{
                        text: 'Coordenadas',
                        callback: showCoordinates
                    }, {
                        text: 'Centrar mapa aqui',
                        callback: centerMap
                    }, '-', {
                        text: 'Acercar',
                        callback: zoomIn
                    }, {
                        text: 'Alejar',
                        callback: zoomOut
                    }]


            }).setView([-12.0, -77.0], 8);
    // add an OpenStreetMap tile layer
    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://geosolution.pe">Geosolution</a> '
    }).addTo(map);


}

/*__________________CARGAR CAPAS DEL MAPA______________________*/
/*Funcion para la carga de los mapas*/

function cargarCapasMapa() {
    var baseMaps = {
        "osm": osm,
        "goglesatelite": goglesatelite,
        "goglemap": goglemap,
        "opencyclemap": opencyclemap,
        "cloudmadeOriginal": cloudmadeOriginal,
        "cloudmadeMigthNigth": cloudmadeMigthNigth,
        "cloudmadeRedAlert": cloudmadeRedAler,
        "mapbox": mapbox
    };

    /*Agreguscador de geoencoder */
    sidebar = L.control.sidebar('sidebar', {
        closeButton: true,
        position: 'left'
    });
    map.addControl(sidebar);

    setTimeout(function() {
        sidebar.show();
    }, 500);

    map.addControl(osmGeocoder);
    CapaLayers = L.control.layers(baseMaps);
    CapaLayers.addTo(map);
    /*agregar control de medicion*/
    L.control.measure().addTo(map);
    $("a.leaflet-control-measure").parents().removeClass('leaflet-control-zoom');
    /*agregar control de escala*/
    L.control.scale().addTo(map);
    /*Agrear cargador de cvs*/
    var OcultarSideBar = L.control({position: 'topleft'});
    OcultarSideBar.onAdd = function(map) {

        var div = L.DomUtil.create('div', 'ocultarSiderbar leaflet-control-filelayer leaflet-control-zoom leaflet-bar');
        div.innerHTML = '<a id="hrefocultarSiderbar" class="leaflet-control-filelayer leaflet-control-zoom-in leaflet-bar-part" href="#" title="Load local file (GPX, KML, GeoJSON)">' +
                '<i class="fa fa-eye"></i>' +
                '</a>';

        return div;
    };


    OcultarSideBar.addTo(map);
    $('.ocultarSiderbar').hide();


    var style = {color: 'blue', opacity: 1, fillOpacity: 0.3, weight: 2, clickable: false};
    L.Control.FileLayerLoad.LABEL = '<i class="fa fa-folder-open"></i>';
    L.Control.fileLayerLoad({
        fitBounds: true,
        layerOptions: {style: style,
            pointToLayer: function(data, latlng) {
                return L.circleMarker(latlng, {style: style});
            }},
    }).addTo(map);
    L.Control.fileLayerLoad({">>": ">>"});
}

/*funciones utilies para el carga de mapas  */
function showCoordinates(e) {
    alert(e.latlng);
    
}

function centerMap(e) {
    map.panTo(e.latlng);
}

function zoomIn(e) {
    map.zoomIn();
}

function zoomOut(e) {
    map.zoomOut();
}





/*Funcion para la carga de minimapa*/

function miniMaper() {
    miniMap = new L.Control.MiniMap(osm, {toggleDisplay: true}).addTo(map);
}

/*Barra de herramientas*/


/*
 function CreateDraw() {
 map.addLayer(drawnItems);
 var drawControl = new L.Control.Draw({
 position: 'topright',
 draw: {
 polyline: {
 allowIntersection: true,
 repeatMode: false,
 drawError: {
 color: '#b00b00',
 timeout: 2500
 },
 icon: new L.DivIcon({
 iconSize: new L.Point(8, 8),
 className: 'leaflet-div-icon leaflet-editing-icon'
 }),
 guidelineDistance: 20,
 maxGuideLineLength: 4000,
 shapeOptions: {
 stroke: true,
 color: '#000389',
 weight: 3,
 opacity: 0.7,
 fill: false,
 clickable: true
 },
 metric: true, // Whether to use the metric meaurement system or imperial
 showLength: true, // Whether to display distance in the tooltip
 zIndexOffset: 2000 // This should be > than the highest z-index any map layers
 },
 polygon: {
 title: 'Herramientas de dibujo',
 allowIntersection: false,
 showArea: true,
 drawError: {
 color: '#B00004',
 timeout: 1000
 },
 shapeOptions: {
 color: '#B00004',
 opacity: 0.7,
 }
 },
 circle: {
 shapeOptions: {
 color: '#005288'
 }
 },
 rectangle: {
 shapeOptions: {
 color: '#C615A7',
 opacity: 0.7
 }
 
 }
 },
 edit: {
 featureGroup: drawnItems,
 selectedPathOptions: {
 color: '#FF0000'
 }
 }
 });
 
 map.addControl(drawControl);
 map.on('draw:created', function(e) {
 var type = e.layerType,
 layer = e.layer;
 
 //funcion para guardar geometrias
 
 
 if (type === 'marker') {
 layer.bindPopup('Creacion de un Marker');
 }
 drawnItems.addLayer(layer);
 
 // funciones a ejecutar al crear las capas
 //debugger;
 if (IndOperCreate === 'Cliente') {
 filtrarPuntosClientes(layer, PuntosClientesGlobal);
 }
 
 if (IndOperCreate === 'Geometria') {
 open_popup(type, layer);
 //guardargeometria(type, layer);
 }
 });
 
 map.on('draw:edited', function(e) {
 var layers = e.layers;
 var countOfEditedLayers = 0;
 layers.eachLayer(function(layer) {
 countOfEditedLayers++;
 modificargeometria(layer);
 });
 console.log("Edited " + countOfEditedLayers + " layers");
 });
 
 map.on('draw:deleted', function(e) {
 var layers = e.layers;
 var countOfEditedLayers = 0;
 layers.eachLayer(function(layer) {
 
 eliminargeometria(layer);
 });
 });
 }*/


