function optener_datos_square00025() {
    var data = {
        "type": "FeatureCollection",
        "features": [
        ]
    };
    osquareBE = {
        "IndOpSp": 25, //pasa sacra geometrias de la la tabls square0005
        "gid": 0,
        "num_clientes": 0
    };

    $.ajaxGeoSolution('GeometriaController/listardatossquare.htm', {osquareBE: osquareBE}, false, function(response) {

        jQuery.each(square00025.features, function(i, val) {
            if (response[i].num_clientes > 0) {
                square00025.features[i].properties["num_clientes"] = response[i].num_clientes;
                data.features.push(square00025.features[i]);
            }
        });

    });

    //setTimeout(function() {
    function getColor(d) {
        return d > 20 ? '#800026' :
                d > 15 ? '#bd0026' :
                d > 10 ? '#e31a1c' :
                d > 5 ? '#fd8d3c' :
                d > 3 ? '#feb24c' :
                d > 2 ? '#fed976' :
                d > 1 ? '#ffeda0' :
                '#ffffcc';
    }


    function style(feature) {
        return {
            fillColor: getColor(feature.properties.num_clientes),
            weight: .5,
            opacity: 1,
            color: 'white',
            dashArray: '0.2',
            fillOpacity: 0.8
        };
    }



    LayerSquare0025 = L.geoJson(data, {
        style: style,
        onEachFeature: onEachFeature
    }).addTo(map);



    function highlightFeature(e) {
        var layer = e.target;

        layer.setStyle({
            weight: 2,
            color: '#666',
            dashArray: '',
            fillOpacity: 0.7
        });

        if (!L.Browser.ie && !L.Browser.opera) {
            layer.bringToFront();
        }
        info.update(layer.feature.properties);
    }


    function resetHighlight(e) {
        LayerSquare0025.resetStyle(e.target);

        info.update();
    }
    function zoomToFeature(e) {
        map.fitBounds(e.target.getBounds());
    }


    function onEachFeature(feature, layer) {
        layer.on({
            mouseover: highlightFeature,
            mouseout: resetHighlight,
            click: zoomToFeature
        });
    }


    var info = L.control();

    info.onAdd = function(map) {
        this._div = L.DomUtil.create('div', 'info');
        this.update();
        return this._div;
    };


    info.update = function(props) {

        if (typeof props !== 'undefined')
            this._div.innerHTML = '<h4>Numero de clientes: ' + props.num_clientes + '</h4>';

    };

    info.addTo(map);

    // }, 2000);
}


function optener_datos_square0005() {
    var data = {
        "type": "FeatureCollection",
        "features": [
        ]
    };
    osquareBE = {
        "IndOpSp": 5, //pasa sacra geometrias de la la tabls square0005
        "gid": 0,
        "num_clientes": 0
    };

    $.ajaxGeoSolution('GeometriaController/listardatossquare.htm', {osquareBE: osquareBE}, false, function(response) {

        jQuery.each(square0005.features, function(i, val) {
            if (response[i].num_clientes > 0) {
                square0005.features[i].properties["num_clientes"] = response[i].num_clientes;
                data.features.push(square0005.features[i]);
            }
        });

    });

    //setTimeout(function() {
    function getColor(d) {
        return d > 100 ? '#800026' :
                d > 80 ? '#bd0026' :
                d > 60 ? '#e31a1c' :
                d > 30 ? '#fd8d3c' :
                d > 20 ? '#feb24c' :
                d > 10 ? '#fed976' :
                d > 1 ? '#ffeda0' :
                '#ffffcc';
    }


    function style(feature) {
        return {
            fillColor: getColor(feature.properties.num_clientes),
            weight: .5,
            opacity: 1,
            color: 'white',
            dashArray: '0.2',
            fillOpacity: 0.8
        };
    }



    layerSquare0005 = L.geoJson(data, {
        style: style,
        onEachFeature: onEachFeature
    }).addTo(map);



    function highlightFeature(e) {
        var layer = e.target;

        layer.setStyle({
            weight: 2,
            color: '#666',
            dashArray: '',
            fillOpacity: 0.7
        });

        if (!L.Browser.ie && !L.Browser.opera) {
            layer.bringToFront();
        }
        info.update(layer.feature.properties);
        bringToFront_draw();
    }


    function resetHighlight(e) {
        layerSquare0005.resetStyle(e.target);

        info.update();
        bringToFront_draw();
    }
    function zoomToFeature(e) {
        map.fitBounds(e.target.getBounds());
    }


    function onEachFeature(feature, layer) {
        layer.on({
            mouseover: highlightFeature,
            mouseout: resetHighlight,
            click: zoomToFeature
        });
    }


    var info = L.control();

    info.onAdd = function(map) {
        this._div = L.DomUtil.create('div', 'info');
        this.update();
        return this._div;
    };


    info.update = function(props) {
        if (typeof props !== 'undefined')
            this._div.innerHTML = '<h4>Numero de clientes: ' + props.num_clientes + '</h4>';

    };

    info.addTo(map);

    // }, 2000);
}

//setTimeout(function() {
//   optener_datos_square00025();
//}, 2000);






function get_hexagono00025() {
    var data = {
        "type": "FeatureCollection",
        "features": [
        ]
    };
    osquareBE = {
        "IndOpSp": 45, //pasa sacra geometrias de hexagono de la tabla
        "gid": 0,
        "num_clientes": 0
    };

    $.ajaxGeoSolution('GeometriaController/listardatossquare.htm', {osquareBE: osquareBE}, false, function(response) {

        jQuery.each(hexagono00025.features, function(i, val) {
            if (response[i].num_clientes > 0) {
                hexagono00025.features[i].properties["num_clientes"] = response[i].num_clientes;
                data.features.push(hexagono00025.features[i]);
            }
        });

    });

    //setTimeout(function() {
    function getColor(d) {
        return d > 20 ? '#800026' :
                d > 15 ? '#bd0026' :
                d > 10 ? '#e31a1c' :
                d > 5 ? '#fd8d3c' :
                d > 3 ? '#feb24c' :
                d > 2 ? '#fed976' :
                d > 1 ? '#ffeda0' :
                '#ffffcc';
    }


    function style(feature) {
        return {
            fillColor: getColor(feature.properties.num_clientes),
            weight: .5,
            opacity: 1,
            color: 'white',
            dashArray: '0.2',
            fillOpacity: 0.8
        };
    }



    Layerhexagono00025 = L.geoJson(data, {
        style: style,
        onEachFeature: onEachFeature
    }).addTo(map);



    function highlightFeature(e) {
        var layer = e.target;

        layer.setStyle({
            weight: 2,
            color: '#666',
            dashArray: '',
            fillOpacity: 0.7
        });

        if (!L.Browser.ie && !L.Browser.opera) {
            layer.bringToFront();
        }
        info.update(layer.feature.properties);
    }


    function resetHighlight(e) {
        Layerhexagono00025.resetStyle(e.target);

        info.update();
    }
    function zoomToFeature(e) {
        map.fitBounds(e.target.getBounds());
    }


    function onEachFeature(feature, layer) {
        layer.on({
            mouseover: highlightFeature,
            mouseout: resetHighlight,
            click: zoomToFeature
        });
    }


    var info = L.control();

    info.onAdd = function(map) {
        this._div = L.DomUtil.create('div', 'info');
        this.update();
        return this._div;
    };


    info.update = function(props) {

        if (typeof props !== 'undefined')
            this._div.innerHTML = '<h4>Numero de clientes: ' + props.num_clientes + '</h4>';

    };

    info.addTo(map);

    // }, 2000);
}


function get_hexagono0005() {
    var data = {
        "type": "FeatureCollection",
        "features": [
        ]
    };
    osquareBE = {
        "IndOpSp": 45, //pasa sacra geometrias de hexagono de la tabla
        "gid": 0,
        "num_clientes": 0
    };

    $.ajaxGeoSolution('GeometriaController/listardatossquare.htm', {osquareBE: osquareBE}, false, function(response) {

        jQuery.each(hexagono0005.features, function(i, val) {
            //if (response[i].num_clientes > 0) {
            hexagono0005.features[i].properties["num_clientes"] = response[i].num_clientes;
            data.features.push(hexagono0005.features[i]);
            //}
        });

    });

    //setTimeout(function() {
    function getColor(d) {
        return d > 20 ? '#800026' :
                d > 15 ? '#bd0026' :
                d > 10 ? '#e31a1c' :
                d > 5 ? '#fd8d3c' :
                d > 3 ? '#feb24c' :
                d > 2 ? '#fed976' :
                d > 1 ? '#ffeda0' :
                '#ffffcc';
    }


    function style(feature) {
        return {
            fillColor: getColor(feature.properties.num_clientes),
            weight: .5,
            opacity: 1,
            color: 'white',
            dashArray: '0.2',
            fillOpacity: 0.8
        };
    }



    Layerhexagono0005 = L.geoJson(data, {
        style: style,
        onEachFeature: onEachFeature
    }).addTo(map);



    function highlightFeature(e) {
        var layer = e.target;

        layer.setStyle({
            weight: 2,
            color: '#666',
            dashArray: '',
            fillOpacity: 0.7
        });

        if (!L.Browser.ie && !L.Browser.opera) {
            layer.bringToFront();
        }
        info.update(layer.feature.properties);
    }


    function resetHighlight(e) {
        Layerhexagono0005.resetStyle(e.target);

        info.update();
    }
    function zoomToFeature(e) {
        map.fitBounds(e.target.getBounds());
    }


    function onEachFeature(feature, layer) {
        layer.on({
            mouseover: highlightFeature,
            mouseout: resetHighlight,
            click: zoomToFeature
        });
    }


    var info = L.control();

    info.onAdd = function(map) {
        this._div = L.DomUtil.create('div', 'info');
        this.update();
        return this._div;
    };


    info.update = function(props) {

        if (typeof props !== 'undefined')
            this._div.innerHTML = '<h4>Numero de clientes: ' + props.num_clientes + '</h4>';

    };

    info.addTo(map);

    // }, 2000);
}