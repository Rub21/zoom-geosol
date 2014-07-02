

setTimeout(function() {
    //  CreateDraw();
    
    
}, 100);



$(document).on('ready', function() {
   // $("#MenuGeometria").trigger("click");
    $('#guardar_geometria_modal').click(function() {
        guardar_geometria();
        drawnItems.clearLayers();
        $(".close").trigger("click");
        $("#MenuGeometria").trigger("click");
        // $("#btListarGeometria").trigger("click");
        setTimeout(function() {
            listar_geometrias();
        }, 500);
          console.log(square);
        
 
    });

    $('#ctx_consultar_data').click(function() {
        consultar_data();
               console.log(square);
    });



});
