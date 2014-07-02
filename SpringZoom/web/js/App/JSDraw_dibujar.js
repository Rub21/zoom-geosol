function CreateDraw() {

    var drawControl = new L.Control.Draw({
        position: 'topright',
        draw: {
            polyline: {
                metric: true
            },
            polygon: {
                allowIntersection: false,
                showArea: true,
                drawError: {
                    color: '#b00b00',
                    timeout: 1000,
                    message: '<strong>No es posible el cruce, elija otra cordenada<strong>'
                },
                shapeOptions: {
                    color: '#bada55',
                }
            },
            circle: {
                shapeOptions: {
                    color: '#662d91'
                }
            },
            marker: false
        },
        edit: {
            featureGroup: drawnItems,
            remove: true
        }
    });
    map.addLayer(drawnItems);
    map.addControl(drawControl);
    map.on('draw:created', function(e) {
        var type = e.layerType,
                layer = e.layer;
        //valor para modificar
        layer.options['type'] = type;

        if (type === 'marker') {
            layer.bindPopup('A popup!');
        }
        layer.on('contextmenu', function(evt) {

            $('#id_valor_geom').val(get_valor(type, layer));//guarda el valor d ela geometria
            console.log(layer);
            $('#id_type_geom').val(type);//guarda el tipo de gemoetria
            $('#id_color_geom').val(layer.options.color);


            abrir_menucontext(evt, layer);
        });

        drawnItems.addLayer(layer);
    });

    map.on('draw:edited', function(e) {
        console.log(e);
        var layers = e.layers;
        console.log(layers);
        var countOfEditedLayers = 0;
        layers.eachLayer(function(layer) {
            $('#id_valor_geom').val(get_valor(layer.options.type, layer));//guarda el valor d ela geometria
            $('#id_type_geom').val(layer.options.type);//guarda el tipo de gemoetria
            $('#id_color_geom').val(layer.options.color);
            //console.log(layer);
            countOfEditedLayers++;

        });
        console.log("Edited " + countOfEditedLayers + " layers");
    });


    map.on('draw:deleted', function(e) {
        var layers = e.layers;
        var countOfEditedLayers = 0;
        layers.eachLayer(function(layer) {
            //eliminargeometria(layer);
        });
    });



}

//funcion que abrir y cerra el context_dibujaro
function abrir_menucontext(e, layer) {
    $('#context_dibujar').show();
    $('#context_dibujar').css('left', e.originalEvent.clientX);
    $('#context_dibujar').css('top', e.originalEvent.clientY - 50);
    $('#context_dibujar').hover(function() {
        $('#context_dibujar').show();
    });
    $('#context_dibujar').mouseleave(function() {
        $('#context_dibujar').hide();
    });
    setTimeout(function() {
        if ($('#context_dibujar').is(':hover') === true) {
            $('#context_dibujar').show();
        } else {
            $('#context_dibujar').hide();
        }
    }, 2000);


    $("#ctx_guardar_geom").click(function(event) {
        //antes de guardar la geommetria abrimos el modal de la para poner los nombre
        abrir_modal_formulario();
        e.stopPropagation();

        //event.stopPropagation();
    });

    $("#ctx_consultar_data").click(function(event) {
        consultar_data(layer);

        e.stopPropagation();
        // event.stopPropagation();

    });




}


//abrir modal para llenar datos de geometria
function abrir_modal_formulario() {

    //limiar campos
    $('#id_mobre_geom').val('');
    $('#id_descripcion_geom').val('');
    // $('#id_color_geom').val('#C615A7');
    $('#id_opacity_geom').val('');

    $('#myModal1_guardar_geom_btn').trigger('click');

}






