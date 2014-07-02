/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geosolutions.zoom.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import geosolutions.zoom.be.GeometriaBE;
import geosolutions.zoom.be.SquareBE;
import geosolutions.zoom.bl.GeometriaBL;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//import org.na.helper.HelperMapper;
//import org.na.helper.Session;
//import org.na.modelo.RegCuenta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author geosolution
 */
@Controller
@RequestMapping("/GeometriaController")
@SessionAttributes({"oSession"})
public class GeometriaController {

    Gson gson = new GsonBuilder().serializeNulls().create();
    String json = "";

    @RequestMapping(value = "guardarGeometria.htm", method = RequestMethod.POST)
    public @ResponseBody
    String guardarGeometria(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {
        // GeometriaBL oGeometriaBL = new GeometriaBL();
        // GeometriaBE oGeometriaBE = gson.fromJson(gson.toJson(param.get("poGeometriaBE")), GeometriaBE.class);
        //String test = gson.fromJson(gson.toJson(param.get("nombre")), String.class);
        //System.out.println(test);
        //int i = oGeometriaBL.insertarGeometriaBE(oGeometriaBE);
        // json = gson.toJson(test);
        //return  json;

        GeometriaBL oGeometriaBL = new GeometriaBL();

        LinkedList<GeometriaBE> listOBGeometria = new LinkedList<GeometriaBE>();

        Type listType = (Type) new TypeToken<ArrayList<GeometriaBE>>() {
        }.getType();

        List<GeometriaBE> lista = gson.fromJson(gson.toJson(param.get("arraygeometrias")), listType);

        oGeometriaBL.insertarGeometriaBE(lista);
        json = gson.toJson("Registro satisfactorio");
        return json;

    }

    @RequestMapping(value = "listarGeometriaObject.htm", method = RequestMethod.POST)
    public @ResponseBody
    String listarGeometriaObject(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {

        GeometriaBL oGeometriaBL = new GeometriaBL();
        String json = gson.toJson(oGeometriaBL.listarGeometriaBE());

        return json;

    }

    @RequestMapping(value = "/listarClienteGeometria.htm", method = RequestMethod.POST)
    public @ResponseBody
    String consultarClienteObject(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {

        GeometriaBL oGeometriaBL = new GeometriaBL();
        List list = new LinkedList();
        Gson gson = new GsonBuilder().serializeNulls().create();
        //ClienteBE oClienteBE = gson.fromJson(gson.toJson(param.get("poClienteBE")), ClienteBE.class);

        GeometriaBE oGeometriaBE = gson.fromJson(gson.toJson(param.get("oGeometria")), GeometriaBE.class);

        list = oGeometriaBL.consultarClienteObjectGeometria(oGeometriaBE);
        String json = gson.toJson(list);
        return json;

    }

    @RequestMapping(value = "/eliminarGeometria.htm", method = RequestMethod.POST)
    public @ResponseBody
    String eliminarGeometria(@RequestBody Map<String, Object> param) {

        GeometriaBL oGeometriaBL = new GeometriaBL();
        List list = new LinkedList();
        Gson gson = new GsonBuilder().serializeNulls().create();
        //ClienteBE oClienteBE = gson.fromJson(gson.toJson(param.get("poClienteBE")), ClienteBE.class);

        GeometriaBE oGeometriaBE = gson.fromJson(gson.toJson(param.get("oGeometria")), GeometriaBE.class);

        String retorno = oGeometriaBL.eliminarGeometria(oGeometriaBE) + "+";
        String json = gson.toJson(retorno);
        return json;

    }

    @RequestMapping(value = "/actualizarGeometria.htm", method = RequestMethod.POST)
    public @ResponseBody
    String actualizarGeometria(@RequestBody Map<String, Object> param) {

        GeometriaBL oGeometriaBL = new GeometriaBL();
        List list = new LinkedList();
        Gson gson = new GsonBuilder().serializeNulls().create();
        //ClienteBE oClienteBE = gson.fromJson(gson.toJson(param.get("poClienteBE")), ClienteBE.class);

        GeometriaBE oGeometriaBE = gson.fromJson(gson.toJson(param.get("oGeometria")), GeometriaBE.class);

        String retorno = oGeometriaBL.actualizarGeometria(oGeometriaBE);
        String json = gson.toJson(retorno);
        return json;

    }

    @RequestMapping(value = "/listardatossquare.htm", method = RequestMethod.POST)
    public @ResponseBody
    String listardatossquare(@RequestBody Map<String, Object> param) {

        SquareBE osquareBE = gson.fromJson(gson.toJson(param.get("osquareBE")), SquareBE.class);
        
        
        GeometriaBL oGeometriaBL = new GeometriaBL();
        String json = gson.toJson(oGeometriaBL.listarSquare(osquareBE));

        return json;

    }

}
