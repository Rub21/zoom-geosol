/*___________________VARIABLES GLOBALES___________________*/
//Index
var map, cm, ll = new L.LatLng(-36.852668, 174.762675);
var osm = L.tileLayer('http://tile.openstreetmap.org/{z}/{x}/{y}.png');
var mapbox = L.tileLayer('https://{s}.tiles.mapbox.com/v3/examples.map-zr0njcqy/{z}/{x}/{y}.png');
var goglesatelite = L.tileLayer('https://khms0.google.com/kh/v=145&src=app&x={x}&y={y}&z={z}');
var goglemap = L.tileLayer('https://mts0.google.com/vt/hl=es&src=app&x={x}&y={y}&z={z}');
var opencyclemap = L.tileLayer('http://{s}.tile.opencyclemap.org/cycle/{z}/{x}/{y}.png');
var cloudmadeOriginal = L.tileLayer('http://{s}.tile.cloudmade.com/bdee0890581544d9999e29abf71023cb/1/256/{z}/{x}/{y}.png');
var cloudmadeMigthNigth = L.tileLayer('http://{s}.tile.cloudmade.com/bdee0890581544d9999e29abf71023cb/999/256/{z}/{x}/{y}.png');
var cloudmadeRedAler = L.tileLayer('http://{s}.tile.cloudmade.com/bdee0890581544d9999e29abf71023cb/8/256/{z}/{x}/{y}.png');
var osmGeocoder = new L.Control.OSMGeocoder();

var miniMap;
var IndOperCreate = '';
var IndOperDelete = '';
var IndOperEdit = '';


//Cliente
var PuntosClientesGlobal = []; //[[x],[y],[title]]

var ArrayClientesDias = []; //[[x],[y],[title]]
var ArrayClientesMesas = [];
var ArrayClientesRutas = [];

var clientesBusqueda = [];
var heatCliente = null;
var markersClusterCliente = L.markerClusterGroup();
var circleBusqCliente = null;
var LayerMesasCliente = null;
var LayerRutasCliente = null;
var LayerDiasCliente = null;

var layerSquare0005 = null;
var LayerSquare0025 = null;
var Layerhexagono00025 = null;
var Layerhexagono0005 = null;

var layersNegocio = L.control({position: 'topright'});

layersNegocio.onAdd = function(map) {
    this._div = L.DomUtil.create('div', 'layers');
    this._div.innerHTML = '<div id="Licon">L</div> <div id="Ldetail"><h5>Layer Zoom</h5> <div class="checkbox">' +
            '<label>' +
            '<input id="chkLayerRutas" type="checkbox" value="" >' +
            'Rutas' +
            '</label>' +
            '</div>' +
            '<div class="checkbox">' +
            '<label>' +
            '<input id="chkLayerMesas" type="checkbox" value="" >' +
            'Mesas' +
            '</label>' +
            '</div>' +
            '<div class="checkbox">' +
            '<label>' +
            '<input id="chkLayerDias" type="checkbox" value="" >' +
            'Dias' +
            '</label>' +
            '</div></div>'
    return this._div;
};

var legendCliente = L.control({position: 'bottomleft'});
var infoClienteLayer = L.control({position: 'topleft'});

var legendLayerRutas = L.control({position: 'bottomleft'});
var infoLayerRutas = L.control({position: 'topleft'});

var legendLayerMesas = L.control({position: 'bottomleft'});
var infoLayerMesas = L.control({position: 'topleft'});


var ArrayLeyendas = [];
var ArrayInformacion = [];
//Ventas
//Geometria  
var drawnItems = new L.FeatureGroup();
var layerGroup = L.layerGroup([]);
var marker_geometria = null;


/*___________________RANGO ESTADISTICOS___________________*/
//Rango de las Mesas
var rangoMesas = [
    [1, 10, '#FFDDBB', '<=10'],
    [11, 50, '#FFBF80', '<=50'],
    [51, 100, '#FFA953', '<=100'],
    [101, 300, '#FF962D', '<=300'],
    [301, 500, '#FF8811', '<=500'],
    [501, 1000, '#DD6F00', '<=1000'],
    [1001, 1500, '#B75B00', '<=1500'],
    [1501, 2000, '#954A00', '<=2000'],
    [2001, 3000, '#753A00', '<=3000'],
    [3001, 5000, '#5E2F00', '<=5000'],
    [5001, 1000000, '#331A00', '>5000']
];

//Rango de las Mesas
var rangoRutas = [
    [1, 10, '#FFDDBB', '<=10'],
    [11, 50, '#FFBF80', '<=50'],
    [51, 100, '#FFA953', '<=100'],
    [101, 300, '#FF962D', '<=300'],
    [301, 500, '#FF8811', '<=500'],
    [501, 1000, '#DD6F00', '<=1000'],
    [1001, 1500, '#B75B00', '<=1500'],
    [1501, 2000, '#954A00', '<=2000'],
    [2001, 3000, '#753A00', '<=3000'],
    [3001, 5000, '#5E2F00', '<=5000'],
    [5001, 1000000, '#331A00', '>5000']
];

//Rango de mas Dias
var rangoDias = [
    [1, 10, '#FFDDBB', '<=10'],
    [11, 50, '#FFBF80', '<=50'],
    [51, 100, '#FFA953', '<=100'],
    [101, 300, '#FF962D', '<=300'],
    [301, 500, '#FF8811', '<=500'],
    [501, 1000, '#DD6F00', '<=1000'],
    [1001, 1500, '#B75B00', '<=1500'],
    [1501, 2000, '#954A00', '<=2000'],
    [2001, 3000, '#753A00', '<=3000'],
    [3001, 5000, '#5E2F00', '<=5000'],
    [5001, 1000000, '#331A00', '>5000']
];

var rangoDiasVenta = [
    [1, 49999, '#00FFFF', '<= 50,000'],
    [50000, 99999, ' #74C476', '<= 100,000'],
    [100000, 199999, ' #F88B00', '<= 200,000'],
    [200000, 350000, ' #ffffcc', '<= 350,000'],
    [399999, 400000, '#c7e9b4', '<= 400,000'],
    [449999, 450000, '#7fcdbb', '<= 450,000'],
    [499999, 500000, '#41b6c4', '<= 500,000'],
    [549999, 550000, '#1d91c0', '<= 550,000'],
    [599999, 600000, '#225ea8', '<= 600,000'],
    [999999, 1000000, '#0c2c84', '<= 1,000,000']
];


var rangoMesasVenta = [
    [1, 49999, '#00FFFF', '<= 50,000'],
    [50000, 99999, ' #74C476', '<= 100,000'],
    [100000, 199999, ' #F88B00', '<= 200,000'],
    [200000, 350000, ' #ffffcc', '<= 350,000'],
    [399999, 400000, '#c7e9b4', '<= 400,000'],
    [449999, 450000, '#7fcdbb', '<= 450,000'],
    [499999, 500000, '#41b6c4', '<= 500,000'],
    [549999, 550000, '#1d91c0', '<= 550,000'],
    [599999, 600000, '#225ea8', '<= 600,000'],
    [999999, 1000000, '#0c2c84', '<= 1,000,000']
];


var rangoRutasVenta = [
    [1, 10000, '#FF00FF', '<= 10,000'],
    [10001, 20000, ' #00FF40', '<= 20,000'],
    [20001, 40000, ' #FFFF00', '<= 40,000'],
    [40001, 70000, '#008080', '<= 70,000'],
    [70001, 99999, ' #74C476', '<= 100,000'],
    [100000, 199999, ' #F88B00', '<= 200,000'],
    [200000, 350000, ' #ffffcc', '<= 350,000'],
    [399999, 400000, '#c7e9b4', '<= 400,000'],
    [449999, 450000, '#7fcdbb', '<= 450,000'],
    [499999, 500000, '#41b6c4', '<= 500,000'],
    [549999, 550000, '#1d91c0', '<= 550,000'],
    [599999, 600000, '#225ea8', '<= 600,000'],
    [999999, 1000000, '#0c2c84', '<= 1,000,000']
];
//Rango de mas Dias
var rangoDiasNombre = [
    ['Lunes', '#EDF8E9'],
    ['Martes', '#C7E9C0'],
    ['Miercoles', '#A1D99B'],
    ['Jueves', '#74C476'],
    ['Viernes', '#41AB5D'],
    ['Sabado', '#238B45']
];

//esconde el menu de contexto de polygonos
$('#context_dibujar').hide();
$('#context_lista').hide();
