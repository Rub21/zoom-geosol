/*funcion para filtrar puntos en poligonos*/
function  FiltroPuntosPolygon(Polygon, Puntos) {//
    var statesData = {"type": "FeatureCollection", "features": []};
    var puntosFiltradosCliente = [];
    var lenPuntos = Puntos.length;
    var GeoJSON = Polygon.toGeoJSON();
    statesData.features.push(GeoJSON);
    var gjLayer = L.geoJson(statesData);
    for (var i = 0; i < lenPuntos; i++) {
        var layer = null;
        layer = leafletPip.pointInLayer([Puntos[i][1], Puntos[i][0]], gjLayer, true);
        if (layer.length > 0) {
            puntosFiltradosCliente.push([Puntos[i][0], Puntos[i][1]]);
        }
    }
    return puntosFiltradosCliente;
}

/*Funcion para geolocalizar*/
function geolocalizar() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(mostrar, error);
    } else {
        error('not supported');
    }
}


//Funcion para mostrar mi posicion
var marker = null;
var circle = null;
function mostrar(posicion) {
    /* posicion es el parametro que tiene los valores, las variables para latitud y longitud las obtenemos del parametro posicion.coords*/
    if (marker == null && circle == null) {
    }
    else {
        map.removeLayer(circle);
        map.removeLayer(marker);
    }
    var lat = posicion.coords.latitude; //obtengo la latitud
    var lon = posicion.coords.longitude; //obtengo la longitud
    var error = posicion.coords.accuracy;
    marker = L.marker([lat, lon]).addTo(map);
    marker.bindPopup("<b>Me encuentro aqui </b><br> con un margen de error de: " + error + " m ").openPopup();
    circle = L.circle([lat, lon], error, {
        color: 'red',
        fillColor: '#f03',
        fillOpacity: 0.5
    }).addTo(map);
    map.setView([lat, lon], 18);
}

/*Funcion para el error cuando no hay geolocalizacion*/
function error(error) {
    //El parametro error tambien tiene valores de los errores exactos, pero aqui no lo usaremos.
    alert("No te encontre, pero lo voy a seguir intentando." + error);
}



/*funcion que filtra los puntos dentro de un coroplejico*/
function  FiltroPuntosPolygonCoroplejico(Polygon, Puntos) {//
    var statesData = {"type": "FeatureCollection", "features": []};
    var puntosFiltradosCliente = [];
    var lenPuntos = Puntos.length;
    //var GeoJSON = Polygon.toGeoJSON();
    statesData.features.push(Polygon);
    var gjLayer = L.geoJson(statesData);
    for (var i = 0; i < lenPuntos; i++) {
        var layer = null;
        layer = leafletPip.pointInLayer([Puntos[i][1], Puntos[i][0]], gjLayer, true);
        if (layer.length > 0) {
            puntosFiltradosCliente.push([Puntos[i][0], Puntos[i][1]]);
        }
    }
    return puntosFiltradosCliente;
}




/*funcion que me permita oscurecer un color*/
function oscurecerColor(color, cant) {
    //voy a extraer las tres partes del color
    var rojo = color.substr(1, 2);
    var verd = color.substr(3, 2);
    var azul = color.substr(5, 2);

    //voy a convertir a enteros los string, que tengo en hexadecimal
    var introjo = parseInt(rojo, 16);
    var intverd = parseInt(verd, 16);
    var intazul = parseInt(azul, 16);

    //ahora verifico que no quede como negativo y resto
    if (introjo - cant >= 0)
        introjo = introjo - cant;
    if (intverd - cant >= 0)
        intverd = intverd - cant;
    if (intazul - cant >= 0)
        intazul = intazul - cant;

    //voy a convertir a hexadecimal, lo que tengo en enteros
    rojo = introjo.toString(16);
    verd = intverd.toString(16);
    azul = intazul.toString(16);

    //voy a validar que los string hexadecimales tengan dos caracteres
    if (rojo.length < 2)
        rojo = "0" + rojo;
    if (verd.length < 2)
        verd = "0" + verd;
    if (azul.length < 2)
        azul = "0" + azul;

    //voy a construir el color hexadecimal
    var oscuridad = "#" + rojo + verd + azul;

    //la función devuelve el valor del color hexadecimal resultante
    return oscuridad;
}
