function get_valor(type, layer) {
    var cordenadas;
    var valor;
    if (type === 'marker') {
        var longitud = layer.toGeoJSON().geometry.coordinates[0];
        var latitud = layer.toGeoJSON().geometry.coordinates[1];
        var marker = longitud + ' ' + latitud;
    } else if (type === 'rectangle') {
        cordenadas = layer.toGeoJSON().geometry.coordinates[0];
        var rectangle = null;
        $.each(cordenadas, function(key, value) {
            if (rectangle === null) {
                rectangle = rectangle + cordenadas[key][0] + ' ' + cordenadas[key][1];
            } else {
                rectangle = rectangle + ',' + cordenadas[key][0] + ' ' + cordenadas[key][1];
            }
        });

        valor = rectangle.toString();
    } else if (type === 'circle') {
        var circle = layer.toGeoJSON().geometry.coordinates[0] + ' ' + layer.toGeoJSON().geometry.coordinates[1] + '/' + layer._getLngRadius();
        valor = circle.toString();
    } else if (type === 'polygon') {
        cordenadas = layer.toGeoJSON().geometry.coordinates[0];
        var polygon = null;
        $.each(cordenadas, function(key, value) {
            if (polygon === null) {
                polygon = polygon + cordenadas[key][0] + ' ' + cordenadas[key][1];
            } else {
                polygon = polygon + ',' + cordenadas[key][0] + ' ' + cordenadas[key][1];
            }
        });
        valor = polygon.toString()
    } else if (type === 'polyline') {
        cordenadas = layer.toGeoJSON().geometry.coordinates;
        var polyline = null;
        $.each(cordenadas, function(key, value) {

            if (polyline === null) {
                polyline = polyline + cordenadas[key][0] + ' ' + cordenadas[key][1];
            } else {
                polyline = polyline + ',' + cordenadas[key][0] + ' ' + cordenadas[key][1];
            }
        });
    }
    return valor;

}

function guardar_geometria() {
    var nombre = $('#id_mobre_geom').val();
    var descripcion = $('#id_descripcion_geom').val();
    var color = $('#id_color_geom').val();
    var opacity = parseInt($('#id_opacity_geom').val());
    var valor = $('#id_valor_geom').val();
    var type = $('#id_type_geom').val();

    var oGeometria = {
        idgeometria: 'geom',
        nombre: nombre,
        tipo: type,
        valor: valor,
        descripcion: descripcion,
        color: color,
        opacity: opacity
    };


    $.ajaxGeoSolution('GeometriaController/guardarGeometria.htm', {arraygeometrias: [oGeometria]}, false, function(response) {
        // alert(response);
    });
}

function consultar_data() {
    $("#MenuGeometria").trigger("click");
    var geom = $('#id_valor_geom').val();
    var type = $('#id_type_geom').val();
    var oGeometria = {
        IndOpSp: 2, //2=consulta por geomtria sin registraar
        idgeometria: '',
        nombre: '',
        tipo: type,
        valor: geom
    };
    setTimeout(function() {
        $.ajaxGeoSolution('GeometriaController/listarClienteGeometria.htm', {
            oGeometria: oGeometria
        }, false, function(response) {
            $('#gridClienteGeometria').jqGrid('clearGridData');
            jQuery("#gridClienteGeometria").jqGrid('setGridParam', {
                data: response
            }).trigger('reloadGrid');
        });
    }, 100);
}




function listar_geometrias() {
    $.ajaxGeoSolution('GeometriaController/listarGeometriaObject.htm', {parametro: "null"}, false, function(data) {
        layerGroup.clearLayers();
        $("#gridgeometria").jqGrid("clearGridData", true);
        jQuery("#gridgeometria").jqGrid('addRowData', 0, data);

    });
}


function modificargeometria(layer) {
    console.log('Objeto Modificado:' + layer.options.id);
    console.log('Objeto tipo:' + layer.options.type);
    var idgeometria = layer.options.id;
    var rowIds = $('#gridgeometria').jqGrid('getDataIDs');
    for (i = 0; i < rowIds.length; i++) {
        console.log(rowIds[i]);
        var rowData = $('#gridgeometria').jqGrid("getRowData", rowIds[i]);
        if (rowData.idgeometria === idgeometria) {
            console.log(rowData.valor);
            var newValor = get_valor(layer.options.type, layer);
            console.log(newValor);
            $('#gridgeometria').jqGrid('setCell', rowIds[i], 'valor', newValor);
        }
    }
}

function populatePopup(layer, layerType) {
    $("#map .input-opacity").val(layer.options.fillOpacity * 100);
    $("#map .input-opacity").val(layer.options.opacity * 100);

}