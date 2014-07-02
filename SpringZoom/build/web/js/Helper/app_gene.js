/// <reference path="json2.js" />
        jQuery.ajaxSinParametrosGeoSolution = function(purl, pAsync, psuccess) {
            $.ajax({
                type: "POST",
                url: purl,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                async: pAsync,
                success: psuccess,
                error: function(a, b, c) {
                    jsErrLog.ErrorTrap(jQuery.parseJSON(a.responseText).Message, location.href, purl, "");
                }
            });
        }

jQuery.ajaxGeoSolution = function(purl, pdata, pAsync, psuccess) {
    $.ajax({
        type: "POST",
        url: purl,
        data: JSON.stringify(pdata),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: pAsync,
        success: psuccess,
        error: function(a, b, c) {
            console.log(jQuery.parseJSON(a.responseText).Message, location.href, purl, "");
        }
    });
}

jQuery.xmlGeoSolution = function(purl, psuccess) {
    $.ajax({
        type: "GET",
        url: "../../Xml/" + purl,
        dataType: "xml",
        success: psuccess
    });
}

jQuery.ajaxObjectGeoSolution = function(purl, pParam, pdata, pAsync, psuccess) {
    $.ajax({
        type: "POST",
        url: purl,
        data: "{'" + pParam + "':" + JSON.stringify(pdata) + "}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: pAsync,
        success: psuccess,
        error: function(a, b, c) {
            jsErrLog.ErrorTrap(jQuery.parseJSON(a.responseText).Message, location.href, purl, "");
        }
    });
}

jQuery.DropDownListGeoSolution = function(purl, pdata, pDropDownList) {
    $(pDropDownList).html('');

    $.ajaxGeoSolution(purl, pdata, false, function(response) {
        //var data = (typeof response.d) == 'string' ? eval('(' + response.d + ')') : response.d;

        $.each(response, function(i, dat) {
            $(pDropDownList).append('<option value="' + dat.Id + '" title="' + dat.Valor + '">' + dat.Valor + '</option>');
        });
    });
}

jQuery.DropDownListInt64GeoSolution = function(purl, pdata, pDropDownList) {
    $(pDropDownList).html('');

    $.ajaxGeoSolution(purl, pdata, false, function(response) {
        // var data = (typeof response.d) == 'string' ? eval('(' + response.d + ')') : response.d;

        $.each(response, function(i, dat) {
            $(pDropDownList).append('<option value="' + dat.Id2 + '" title="' + dat.Valor + '">' + dat.Valor + '</option>');
        });
    });
}

/*funcion para la carga de los combos*/


jQuery.CargarComboGeoSolution = function(purl, pdata, pDropDownList) {
    $(pDropDownList).html('');
    $.ajaxGeoSolution(purl, pdata, false, function(response) {
        $(pDropDownList).append('<option value="null" title="TODOS">TODOS</option>');
        $.each(response, function(i, dat) {
            $(pDropDownList).append('<option value="' + dat[0] + '" title="' + dat[1] + '">' + dat[1] + '</option>');
        });
    });
}

jQuery.DropDownListCodGeoSolution = function(purl, pdata, pDropDownList) {
    $(pDropDownList).html('');

    $.ajaxGeoSolution(purl, pdata, false, function(response) {
        //alert(response)
        // var data = (typeof response.d) == 'string' ? eval('(' + response.d + ')') : response.d;

        $.each(response, function(i, dat) {
            $(pDropDownList).append('<option value="' + dat.Codigo + '" title="' + dat.Valor + '">' + dat.Valor + '</option>');
        });
    });
}

jQuery.SelectCombo = function(purl, pdata, pDropDownList, pFunctionEach) {
    $(pDropDownList).html('');

    $.ajaxGeoSolution(purl, pdata, false, function(response) {
        var data = (typeof response.d) == 'string' ? eval('(' + response.d + ')') : response.d;

        $.each(data, pFunctionEach);
    });
}

jQuery.fn.AutocompleteGeoSolution = function(purl, pTextBoxControl, pWidth, pFunctionItem, pFunctionSelect) {
    $(pTextBoxControl).autocomplete({
        source: function(request, response) {
            var param = new Object();
            param.pnvDenominacion = request.term;

            $.ajaxGeoSolution(purl, param, true, function(respon) {
                var data = (typeof respon) == 'string' ? eval('(' + respon + ')') : respon;

                if (data != null) {
                    response($.map(data, pFunctionItem));
                }
            });
        },
        open: function(event, ui) {
            $(this).autocomplete("widget").css({
                "width": pWidth
            });
        },
        select: pFunctionSelect
    });
}

jQuery.fn.AutocompleteWithPobjectGeoSolution = function(purl, pObject, pTextBoxControl, pWidth, pFunctionItem, pFunctionSelect) {
    $(pTextBoxControl).autocomplete({
        source: function(request, response) {
            //  var pObject = GetObject();

            var param = {
                pObject: pObject,
                pnvDenominacion: request.term
            }

            $.ajaxGeoSolution(purl, param, true, function(respon) {
                var data = (typeof respon.d) == 'string' ? eval('(' + respon.d + ')') : respon.d;

                if (data != null) {
                    response($.map(data, pFunctionItem));
                }
            });
        },
        open: function(event, ui) {
            $(this).autocomplete("widget").css({
                "width": pWidth
            });
        },
        select: pFunctionSelect
    });
}

jQuery.SearchByCodeWithAutoCompletePobjectApp = function(purl, GetObject, pTextBoxControl, pLenght, pFunction) {
    $("#" + pTextBoxControl).blur(function() {
        if ($('#' + pTextBoxControl).val() == "") {
            return;
        }

        var objeto = new Object();
        objeto.param = $('#' + pTextBoxControl).val();
        objeto.param = $.AutoCompleteCodeGeoSolution(objeto.param, pLenght);
        $('#' + pTextBoxControl).val(objeto.param);

        var pObject = GetObject();

        var param = {
            pObject: pObject,
            pnvDenominacion: objeto.param
        }
        $.ajaxGeoSolution(purl, param, true, pFunction);

        /*$.ajaxGeoSolution(purl, param, true, function (respon) {
         var data = (typeof respon.d) == 'string' ? eval('(' + respon.d + ')') : respon.d;
         
         if (data != null) {
         response($.map(data, pFunctionItem));
         }
         });*/
    });
}


jQuery.SearchByCodeWithAutoComplete = function(purl, pTextBoxControl, pLenght, pFunction) {
    $("#" + pTextBoxControl).blur(function() {
        if ($('#' + pTextBoxControl).val() == "") {
            return;
        }

        var objeto = new Object();
        objeto.param = $('#' + pTextBoxControl).val();
        objeto.param = $.AutoCompleteCodeGeoSolution(objeto.param, pLenght);
        $('#' + pTextBoxControl).val(objeto.param);

        $.ajaxGeoSolution(purl, objeto, true, pFunction);
    });

    //    $("#" + pTextBoxControl).keypress(function (e) {
    //        if (e.keyCode == 13) {
    //            $("#" + pTextBoxControl).unbind("blur");
    //            var objeto = new Object();
    //            objeto.param = $('#' + pTextBoxControl).val();
    //            objeto.param = $.AutoCompleteCodeGeoSolution(objeto.param, pLenght);
    //            $('#' + pTextBoxControl).val(objeto.param);
    //            $.ajaxGeoSolution(purl, objeto, true, pFunction);
    //            cb = parseInt($(this).attr('tabindex'));
    //            if ($(':input[tabindex=\'' + (cb + 1) + '\']') != null) {
    //                $(':input[tabindex=\'' + (cb + 1) + '\']').focus();
    //                $(':input[tabindex=\'' + (cb + 1) + '\']').select();
    //                e.preventDefault();
    //                e.stopPropagation();
    //                $("#" + pTextBoxControl).bind("blur", (function () {
    //                    var objeto = new Object();
    //                    objeto.param = $('#' + pTextBoxControl).val();
    //                    objeto.param = $.AutoCompleteCodeGeoSolution(objeto.param, pLenght);
    //                    $('#' + pTextBoxControl).val(objeto.param);
    //                    $.ajaxGeoSolution(purl, objeto, true, pFunction);
    //                }));
    //            }
    //        }
    //    });
}

//pParamObject incluye atributos de: NameGrid,ColNames,ColModel,NamePager,Multiselect,Width,Height,Caption
jQuery.fn.GridBusquedaGeoSolution = function(pParamObject, pSearchParam, purl) {
    var ControlGrid = $(pParamObject.NameGrid);
    ControlGrid.jqGrid({
        datatype: function() {
            if (purl != null) {
                $.ajaxGeoSolution(purl, {
                    poSearchParam: pSearchParam,
                    pPaginaActual: ControlGrid.getGridParam("page"),
                    pTamanioPagina: ControlGrid.getGridParam("rowNum")
                }, false, function(data, textStatus) {
                    if (textStatus == "success") {
                        ControlGrid[0].addJSONData(jQuery.parseJSON(data.d));
                    }
                });
            }
        },
        colModel: pParamObject.ColModel,
        jsonReader: {
            page: "CurrentPage",
            total: "PageCount",
            records: "RecordCount",
            repeatitems: true,
            cell: "Row",
            root: "Items",
            id: "ID"
        },
        pager: $(pParamObject.NamePager),
        loadtext: 'Cargando datos...',
        recordtext: "{0} - {1} de {2} elementos",
        emptyrecords: 'No hay resultados',
        pgtext: 'Pág: {0} de {1}',
        rowNum: "500",
        rowList: [10, 20, 30],
        //shrinkToFit: false,
        viewrecords: true,
        multiselect: pParamObject.Multiselect,
        sortname: "Name",
        sortorder: "asc",
        //jsonReader: { repeatitems: false },
        width: pParamObject.Width,
        height: pParamObject.Height,
        caption: pParamObject.Caption,
        ondblClickRow: pParamObject.DoubleClick,
        onSelectRow: pParamObject.onSelectRow
    }).navGrid(pParamObject.NamePager, {
        edit: false,
        add: false,
        search: false,
        del: false
    });

    jQuery.extend(jQuery.jgrid.edit, {
        ajaxEditOptions: {
            contentType: "application/json"
        },
        recreateForm: true,
        serializeEditData: function(postData) {
            return JSON.stringify(postData);
        }
    });

    jQuery.extend(jQuery.jgrid.del, {
        ajaxDelOptions: {
            contentType: "application/json"
        },
        serializeDelData: function(postData) {
            return JSON.stringify(postData);
        }
    });
};

jQuery.GridEditDelUrlGeoSolution = function(pParamObject, url, fnEdit, fnDel) {
    var idjq = $(pParamObject.NameGrid);
    $.GridEditDelDatatypeGeoSolution(pParamObject, function() {
        $.ajaxGeoSolution(
                url, {
                    pPaginaActual: idjq.getGridParam("page"),
                    pTamanioPagina: idjq.getGridParam("rowNum")
                }, false, function(data, textStatus) {
            if (textStatus == "success") {
                idjq[0].addJSONData(jQuery.parseJSON(data.d));
            }
        });
    }, fnEdit, fnDel);
}

jQuery.GridDatatypeGeoSolution = function(pParamObject, fndatatype) {
    var ControlGrid = $(pParamObject.NameGrid);

    ControlGrid.jqGrid({
        datatype: function() {
            fndatatype()
        },
        colModel: pParamObject.ColModel,
        jsonReader: {
            page: "CurrentPage",
            total: "PageCount",
            records: "RecordCount",
            repeatitems: true,
            cell: "Row",
            root: "Items",
            id: "ID"
        },
        pager: $(pParamObject.NamePager),
        loadtext: 'Cargando datos...',
        recordtext: "{0} - {1} de {2} elementos",
        emptyrecords: 'No hay resultados',
        pgtext: 'Pág: {0} de {1}',
        rowNum: pParamObject.rowNum == null || pParamObject.rowNum == undefined ? "11" : pParamObject.rowNum,
        rowList: [11, 22, 33],
        shrinkToFit: pParamObject.shrinkToFit,
        viewrecords: true,
        multiselect: pParamObject.Multiselect,
        onSelectRow: pParamObject.OnSelectRow,
        sortname: "Name",
        sortorder: "asc",
        //jsonReader: { repeatitems: false },
        width: pParamObject.Width,
        height: pParamObject.Height,
        caption: pParamObject.Caption,
        ondblClickRow: pParamObject.DoubleClick,
        subGrid: pParamObject.subGrid,
        subGridRowExpanded: pParamObject.subGridRowExpanded,
        subGridRowColapsed: pParamObject.subGridRowColapsed,
        /*add By Acero![APP]*/
        // grouping: pParamObject.Grouping,
        // groupingView: {
        //     groupField: [pParamObject.NameGroup],
        //     groupColumnShow: [false],
        //     groupText: ['<b>{0}</b>'],
        //     groupCollapse: true,
        //     groupOrder: ['asc'],
        //     //groupSummary: [true],
        //     groupDataSorted: false
        // },
        /*End Add By Acero![APP]*/
        //editurl: "server.php",
        //afterInsertRow: pParamObject.afterInsertRow
    }).navGrid(pParamObject.NamePager, {
        edit: false,
        add: false,
        search: false,
        del: false
    });
}

/*Created by Acero*/
jQuery.GridDatatypeDemon = function(pParamObject, fndatatype) {
    var ControlGrid = $(pParamObject.NameGrid);

    ControlGrid.jqGrid({
        datatype: function() {
            fndatatype()
        },
        colModel: pParamObject.ColModel,
        jsonReader: {
            page: "CurrentPage",
            total: "PageCount",
            records: "RecordCount",
            repeatitems: true,
            cell: "Row",
            root: "Items",
            id: "ID"
        },
        loadtext: 'Cargando datos...',
        emptyrecords: 'No hay resultados',
        shrinkToFit: pParamObject.shrinkToFit,
        viewrecords: true,
        multiselect: pParamObject.Multiselect,
        onSelectRow: pParamObject.OnSelectRow,
        sortname: "Name",
        sortorder: "asc",
        width: pParamObject.Width,
        height: pParamObject.Height,
        caption: pParamObject.Caption,
        ondblClickRow: pParamObject.DoubleClick,
        subGrid: pParamObject.subGrid,
        subGridRowExpanded: pParamObject.subGridRowExpanded,
        subGridRowColapsed: pParamObject.subGridRowColapsed,
        grouping: pParamObject.Grouping,
        groupingView: {
            groupField: [pParamObject.NameGroup],
            groupColumnShow: [false],
            groupText: ['<b>{0}</b>'],
            groupCollapse: true,
            groupOrder: ['asc'],
            groupDataSorted: false
        },
        editurl: "server.php",
        afterInsertRow: pParamObject.afterInsertRow
    }).navGrid(pParamObject.NamePager, {
        edit: false,
        add: false,
        search: false,
        del: false
    });
}
/*end Created*/
/*Created by Ypino*/
jQuery.GridDatatypeWithSearchGeoSolution = function(pParamObject, fndatatype) {
    var ControlGrid = $(pParamObject.NameGrid);

    ControlGrid.jqGrid({
        datatype: function() {
            fndatatype()
        },
        colModel: pParamObject.ColModel,
        jsonReader: {
            page: "CurrentPage",
            total: "PageCount",
            records: "RecordCount",
            repeatitems: true,
            cell: "Row",
            root: "Items",
            id: "ID"
        },
        pager: $(pParamObject.NamePager),
        loadtext: 'Cargando datos...',
        recordtext: "{0} - {1} de {2} elementos",
        emptyrecords: 'No hay resultados',
        pgtext: 'Pág: {0} de {1}',
        rowNum: pParamObject.rowNum == null || pParamObject.rowNum == undefined ? "11" : pParamObject.rowNum,
        rowList: [11, 22, 33],
        shrinkToFit: pParamObject.shrinkToFit,
        viewrecords: true,
        multiselect: pParamObject.Multiselect,
        onSelectRow: pParamObject.OnSelectRow,
        sortname: "Name",
        sortorder: "asc",
        //jsonReader: { repeatitems: false },
        width: pParamObject.Width,
        height: pParamObject.Height,
        caption: pParamObject.Caption,
        ondblClickRow: pParamObject.DoubleClick,
        /*add By Acero![APP]*/
        grouping: pParamObject.Grouping,
        groupingView: {
            groupField: [pParamObject.NameGroup],
            groupColumnShow: [false],
            groupText: ['<b>{0}</b>'],
            groupCollapse: true,
            groupOrder: ['asc'],
            //groupSummary: [true],
            groupDataSorted: false
        },
        /*End Add By Acero![APP]*/
        editurl: "server.php",
        afterInsertRow: pParamObject.afterInsertRow
    }).navGrid(pParamObject.NamePager, {
        edit: false,
        add: false,
        search: true,
        del: false
    },
    {}, // default settings for edit
            {}, // default settings for add
            {}, // delete instead that del:false we need this
            {caption: "Buscar Contribuyente",
                Find: "Buscar",
                Reset: "Limpiar",
                odata: ['Igual a '],
                sopt: ['eq'],
                closeAfterSearch: true,
                search: false,
                onSearch: pParamObject.OnSearch

            }, // enable the advanced searching
    {closeOnEscape: true}
    );

}


/*create By Acero![APP]*/
jQuery.GridDatatypeZeroCGeoSolution = function(pParamObject, fndatatype) {
    var ControlGrid = $(pParamObject.NameGrid);
    ControlGrid.jqGrid({
        datatype: function() {
            fndatatype()
        },
        colModel: pParamObject.ColModel,
        jsonReader: {
            page: "CurrentPage",
            total: "PageCount",
            records: "RecordCount",
            repeatitems: true,
            cell: "Row",
            root: "Items",
            id: "ID"
        },
        pager: $(pParamObject.NamePager),
        loadtext: 'Cargando datos...',
        recordtext: "{0} - {1} de {2} elementos",
        emptyrecords: 'No hay resultados',
        pgtext: 'Pág: {0} de {1}',
        rowNum: pParamObject.rowNum == null || pParamObject.rowNum == undefined ? "22" : pParamObject.rowNum,
        rowList: pParamObject.RowList,
        viewrecords: true,
        multiselect: pParamObject.Multiselect,
        onSelectRow: pParamObject.OnSelectRow,
        sortname: "Name",
        sortorder: "asc",
        width: pParamObject.Width,
        height: pParamObject.Height,
        caption: pParamObject.Caption,
        ondblClickRow: pParamObject.DoubleClick,
        grouping: pParamObject.Grouping,
        groupingView: {
            groupField: [pParamObject.NameGroup],
            groupColumnShow: [false],
            groupText: ['<b>{0}</b>'],
            groupCollapse: pParamObject.GroupCollapse,
            groupOrder: ['asc'],
            groupDataSorted: false
        },
        editurl: "server.php",
        afterInsertRow: pParamObject.afterInsertRow
    }).navGrid(pParamObject.NamePager, {
        edit: false,
        add: false,
        search: false,
        del: false
    });
}
/*End Create By Acero![APP]*/
/*para mostrar el dialog*/
function mostrarReporteRequerimiento(aspx, width, height, left, top) {
    window.open(aspx, null, 'directories=no,channelmode =0,resizable=no,status=no,location=no,toolbar=no,menubar=no,fullscreen=no,scrollbars=yes, center:yes,dependent=no,width=' + width + ',left=' + left + ',height=' + height + ',top=' + top);
}
/*mostrar el dialog*/

jQuery.GridEditDelDatatypeGeoSolution = function(pParamObject, fndatatype, fnEdit, fnDel) {
    var ControlGrid = $(pParamObject.NameGrid);
    var estado = "";

    pParamObject.ColModel.unshift({
        name: "del",
        index: "del",
        width: 40,
        sortable: false,
        label: " "
    });
    pParamObject.ColModel.unshift({
        name: "edit",
        index: "edit",
        width: 40,
        sortable: false,
        label: " "
    });

    ControlGrid.jqGrid({
        datatype: function() {
            fndatatype();

            var namegrid = pParamObject.NameGrid.replace('#', '');

            $("#" + namegrid + " tr [aria-describedby='" + namegrid + "_edit']").css({
                'background-image': 'url("../../Images/imgJqGrid/Editar.png")',
                'background-repeat': 'no-repeat',
                'cursor': 'pointer'
            });
            $("#" + namegrid + " tr [aria-describedby='" + namegrid + "_del']").css({
                'background-image': 'url("../../Images/imgJqGrid/Eliminar.png")',
                'background-repeat': 'no-repeat',
                'cursor': 'pointer'
            });

            $("#" + namegrid + " tr [aria-describedby='" + namegrid + "_edit']").click(function() {
                estado = "edit";
            });
            $("#" + namegrid + " tr [aria-describedby='" + namegrid + "_del']").click(function() {
                estado = "del";
            });
        },
        colModel: pParamObject.ColModel,
        jsonReader: {
            page: "CurrentPage",
            total: "PageCount",
            records: "RecordCount",
            repeatitems: true,
            cell: "Row",
            root: "Items",
            id: "ID"
        },
        pager: $(pParamObject.NamePager),
        loadtext: 'Cargando datos...',
        recordtext: "{0} - {1} de {2} elementos",
        emptyrecords: 'No hay resultados',
        pgtext: 'Pág: {0} de {1}',
        //rowNum: "10",
        rowNum: pParamObject.rowNum == null || pParamObject.rowNum == undefined ? "10" : pParamObject.rowNum,
        rowList: [10, 20, 30],
        //shrinkToFit: false,
        viewrecords: true,
        multiselect: pParamObject.Multiselect,
        sortname: "Name",
        sortorder: "asc",
        //jsonReader: { repeatitems: false },
        width: pParamObject.Width,
        height: pParamObject.Height,
        caption: pParamObject.Caption,
        ondblClickRow: pParamObject.DoubleClick,
        onSelectRow: function(id) {
            if (estado == "edit") {
                fnEdit(id);
            } else if (estado == "del") {
                fnDel(id);
            }
            estado = "";
        },
        grouping: pParamObject.grouping,
        groupingView: {
            groupField: [pParamObject.NameGroup],
            groupColumnShow: [false],
            groupText: ['<b>{0}</b>'],
            groupCollapse: false,
            groupOrder: ['asc'],
            //groupSummary: [true],
            groupDataSorted: false
        }
    }).navGrid(pParamObject.NamePager, {
        edit: false,
        add: false,
        search: false,
        del: false
    });
}

jQuery.GridEditDatatypeGeoSolution = function(pParamObject, fndatatype, fnEdit) {
    var ControlGrid = $(pParamObject.NameGrid);
    var estado = "";

    pParamObject.ColModel.unshift({
        name: "edit",
        index: "edit",
        width: 25,
        sortable: false,
        label: " "
    });

    ControlGrid.jqGrid({
        datatype: function() {
            fndatatype();

            var namegrid = pParamObject.NameGrid.replace('#', '');

            $("#" + namegrid + " tr [aria-describedby='" + namegrid + "_edit']").css({
                'background-image': 'url("../../Images/imgJqGrid/Consultar.png")',
                'background-repeat': 'no-repeat',
                'cursor': 'pointer'
            });

            $("#" + namegrid + " tr [aria-describedby='" + namegrid + "_edit']").click(function() {
                estado = "edit";
            });
        },
        colModel: pParamObject.ColModel,
        jsonReader: {
            page: "CurrentPage",
            total: "PageCount",
            records: "RecordCount",
            repeatitems: true,
            cell: "Row",
            root: "Items",
            id: "ID"
        },
        pager: $(pParamObject.NamePager),
        loadtext: 'Cargando datos...',
        recordtext: "{0} - {1} de {2} elementos",
        emptyrecords: 'No hay resultados',
        pgtext: 'Pág: {0} de {1}',
        rowNum: "10",
        rowList: [10, 20, 30],
        //shrinkToFit: false,
        viewrecords: true,
        multiselect: pParamObject.Multiselect,
        sortname: "Name",
        sortorder: "asc",
        //jsonReader: { repeatitems: false },
        width: pParamObject.Width,
        height: pParamObject.Height,
        caption: pParamObject.Caption,
        ondblClickRow: pParamObject.DoubleClick,
        onSelectRow: function(id) {
            if (estado == "edit") {
                fnEdit(id);
            }
            estado = "";
        },
        grouping: pParamObject.grouping,
        groupingView: {
            groupField: [pParamObject.NameGroup],
            groupColumnShow: [false],
            groupText: ['<b>{0}</b>'],
            groupCollapse: false,
            groupOrder: ['asc'],
            //groupSummary: [true],
            groupDataSorted: false
        }
    }).navGrid(pParamObject.NamePager, {
        edit: false,
        add: false,
        search: false,
        del: false
    });
}

//************FUNCIONES CALENDARIO
jQuery.CalendarioGeoSolution = function(control, mask) {
    $(control).datepicker({
        changeMonth: true,
        changeYear: true,
        autoSize: false,
        inline: true,
        showButtonPanel: true,
        dateFormat: 'dd/mm/yy',
        dayNames: ["Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"],
        dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
        dayNamesShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
        monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"],
        monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Set", "Oct", "Nov", "Dic"],
        closeText: "Salir",
        currentText: "Hoy",
        nextText: "Siguiente",
        prevText: "Anterior"

    });
    $(control).datepicker($.datepicker.regional['es']);
    if (mask != undefined)
        $(control).mask("   99/99/9999");
};

jQuery.TabGeoSolution = function(control) {
    $(control).tabs({
        collapsible: true
    });
};



jQuery.LimpiarFormulario = function(pNameForm) {
    var form = $("#" + pNameForm);

    $(":input", form).each(function() {
        var type = this.type;
        var tag = this.tagName.toLowerCase();

        if (type == "text" || type == "password" || tag == "textarea")
            this.value = "";
        else if (type == "checkbox" || type == "radio")
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = -1;
    });
}

jQuery.InhabilitarFormulario = function(pNameForm) {
    var form = "#" + pNameForm;

    $(form + " :input").each(function() {
        var type = this.type;
        var tag = this.tagName.toLowerCase();

        if (type == "text" || type == "password" || tag == "textarea")
            this.disabled = true;
        else if (type == "checkbox" || type == "radio")
            this.disabled = true;
        else if (tag == 'select')
            this.disabled = true;
    });
}

jQuery.HabilitarFormulario = function(pNameForm) {
    var form = "#" + pNameForm;

    $(form + " :input").each(function() {
        var type = this.type;
        var tag = this.tagName.toLowerCase();

        if (type == "text" || type == "password" || tag == "textarea")
            this.disabled = false;
        else if (type == "checkbox" || type == "radio")
            this.disabled = false;
        else if (tag == 'select')
            this.disabled = false;
    });
}

jQuery.Preloading = function() {
    //$('#div_precarga').hide().ajaxStart(function () {
    $('#div_precarga').ajaxStart(function() {
        $(this).show();
    }).ajaxStop(function() {
        $(this).hide();
    });
}


jQuery.GetBoolean = function(valor) {
    return String(valor) == "true";
}

jQuery.LlenarGrilla = function(grilla, ResultadoBE) {
    if ($.GetBoolean(ResultadoBE.bEsCorrecto)) {
        $(grilla)[0].addJSONData(jQuery.parseJSON(ResultadoBE.nvResultadoGrilla));
    } else {
        $(grilla)[0].addJSONData(jQuery.parseJSON('{"PageCount":0,"CurrentPage":1,"RecordCount":0,"Items":null}'));
        $.MessageAlertGeoSolution(MENSAJEOPERACION.ERROR_CARGAR_DATA.msje);
    }
}


jQuery.UploadGeoSolution = function(idUpload, idLOading, purl, pdata, psuccess) {
    //starting setting some animation when the ajax starts and completes
    $(idLOading).ajaxStart(function() {
        $(this).show();
    })
            .ajaxComplete(function() {
                $(this).hide();
            });
    $.ajaxFileUpload
            (
                    {
                        url: purl,
                        secureuri: false,
                        fileElementId: idUpload,
                        dataType: 'json',
                        data: pdata,
                        success: psuccess,
                        error: function(data, status, e) {
                            alert(e);
                        }
                    }
            )
    return false;
}


//funcion para establecer una hora
//FUNCION PARA LA CARGA DE ARCHIVOS
jQuery.timeGeoSolution = function(idtxtTime) {
    $(idtxtTime).timepickr();
    /*Para ver la documentacion de estos velores
     http://code.google.com/p/jquery-utils/wiki/UiTimepickr
     */
}


jQuery.alertConfirmGeoSolution = function(title, functionSi, functionNO) {

    if (confirm(title)) {
        functionSi()
    }
    else {
        functionNO()
    }
}


/*Actualizar id de las grillas */
function obtenerIdsGrilla(idGrilla) {
    var ids = new Array();
    $(idGrilla + " tbody tr").each(function(index, value) {
        ids.push(value.id)
    });
    return ids;
}

function actualizarIDGrid(idGrilla) {
    $(idGrilla + " tbody tr").each(function(index, value) {
        value.id = index
    });

    //$(idGrilla + " tbody tr td input").each(function (index2, value2) {
    //  value2.id = 'jqg_' + idGrilla + '_' + (index2 + 1)
    //});
}

function insetarIds(idGrilla, ids) {
    $(idGrilla + " tbody tr").each(function(index, value) {
        value.id = ids[index]
    });

    //$(idGrilla + " tbody tr td input").each(function (index2, value2) {
    //   value2.id = 'jqg_' + idGrilla + '_' + (ids[index2] + 1)
    //});
}

//generar numero unico
function fGetNumUnico() {
    var Dia = new Date();
    var d = Dia.getDay();
    var n = Dia.getMonth();
    var a = Dia.getFullYear();
    var m = Dia.getMinutes();
    var h = Dia.getHours();
    var s = Dia.getSeconds();
    var Num = "" + a + n + d + h + m + s;
    return parseInt(Num);
}



function limpiarForm(div) {
    //$(div + ' textarea').css({ 'background': '' });
    $(div + ' textarea').each(function() {
        $(this).val('');
    });

    //$(div + ' select').css({ 'background': '' });
    $(div + ' select').each(function() {
        $(this).selectedIndex = 0;
    });

    $(div + ' input').each(function() {
        $(this).val('');
        $(this).prop('checked', false)
    });
}




function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
}


function actualizarIDGrid(idGrilla) {
    $('#' + idGrilla + " tbody tr").each(function(index, value) {
        value.id = index
    });
    $('#' + idGrilla + " tbody tr td input").each(function(index2, value2) {
        value2.id = 'jqg_' + idGrilla + '_' + (index2 + 1)
    });
}

function PintarRowGrilla(idgrilla, namecolumn, valorComparar, color, colorletra) {
    actualizarIDGrid(idgrilla);
    columns = $('#' + idgrilla).jqGrid('getGridParam', 'colNames');
    $('#' + idgrilla + ' tr [aria-describedby=' + idgrilla + '_' + namecolumn + ']').each(function(r) {
        var c = columns.length;
        while (c > 0) {
            c--;
            if ($(this).text() == valorComparar)
                jQuery('#' + idgrilla).setCell(r + 1, c, '', {
                    'background-color': color,
                    'color': colorletra
                });
        }
    });
}


/*Inicio llenar con ceros a la izquierda*/
/*Permite rellenar con ceros a la izquierda de un numero, util para codigos de contribuyente,etc*/
jQuery.LlenarCerosIzquierda = function(pNumber, pLen) {
    pNumber = pNumber.toString();
    while (pNumber.length < pLen)
        pNumber = "0" + pNumber;
    return pNumber;
}
/*Fin llenar con ceros a la izquierda*/

/*Permite validar hora funciona con blur*/
jQuery.ValidarHora = function(control) {
    $(control).removeClass("errorGeoSolution");
    var userInput = $(control).val().replace(/^([0-9])([0-9]):([0-9])_$/, "0$1:$2$3");
    if (!userInput.match(/([0-1][0-9]|2[0-3]):[0-5][0-9]/)) {
        $(control).val("");
        $(control).addClass("errorGeoSolution");
    } else {
        $(control).val(userInput);
        return true;
    }
    ;
}

/*funcion que me permite eliminar un array*/
function eliminarElemArray(Array, Elemnt) {
    var idx = Array.indexOf(Elemnt);
    if (idx !== -1)
        Array.splice(idx, 1);
    return Array;
}
/*funcion que me permite eliminar un array de otro array*/
function eliminarArraytoArray(Array, ArrayDel) {

    var len = ArrayDel.length;
    for (var i = 0; i < len; i++) {
        Array = eliminarElemArray(Array, ArrayDel[i]);
    }
    return Array;
}
/**/


function redondeo(numero)
{
    return formato_numero(numero, 0, '.', ',');
}


function formato_numero(numero, decimales, separador_decimal, separador_miles) { // v2007-08-06
    numero = parseFloat(numero);
    if (isNaN(numero)) {
        return "";
    }

    if (decimales !== undefined) {
        // Redondeamos
        numero = numero.toFixed(decimales);
    }

    // Convertimos el punto en separador_decimal
    numero = numero.toString().replace(".", separador_decimal !== undefined ? separador_decimal : ",");

    if (separador_miles) {
        // Añadimos los separadores de miles
        var miles = new RegExp("(-?[0-9]+)([0-9]{3})");
        while (miles.test(numero)) {
            numero = numero.replace(miles, "$1" + separador_miles + "$2");
        }
    }

    return numero;
}
