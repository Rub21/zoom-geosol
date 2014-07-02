/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geosolutions.zoom.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import geosolutions.zoom.be.ClienteBE;
import geosolutions.zoom.be.VentasBE;
import geosolutions.zoom.bl.ClienteBL;
import geosolutions.zoom.bl.VentasBL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//import org.na.helper.HelperMapper;
//import org.na.helper.Session;
//import org.na.modelo.RegCuenta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author geosolution
 */
@Controller
@RequestMapping("/VentasController")
@SessionAttributes({"oSession"})
public class VentasController {
    Gson gson = new Gson();
    
    
    @RequestMapping(value = "/cargarCombos.htm", method = RequestMethod.POST)
    public @ResponseBody
    String cargarCombos(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {
        VentasBL oVentasBL = new VentasBL();
        List list = new LinkedList();
        VentasBE oVentasBE = gson.fromJson(gson.toJson(param.get("poVentasBE")), VentasBE.class);
        list = oVentasBL.cargarCombosFiltroVentas(oVentasBE);
        String json = gson.toJson(list);
        return json;
    }
    
    @RequestMapping(value = "/filtroventas.htm", method = RequestMethod.POST)
    public @ResponseBody
    String filtroventas(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {
        VentasBL oVentasBL = new VentasBL();
        List list = new LinkedList();
        VentasBE oVentasBE = gson.fromJson(gson.toJson(param.get("poVentasBE")), VentasBE.class);
        list = oVentasBL.FiltroVentas(oVentasBE);
        String json = gson.toJson(list);
        return json;
    }
    
    
     @RequestMapping(value = "/ListarVentas.htm", method = RequestMethod.POST)
    public @ResponseBody
    String ListarVentas(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {
        VentasBL oVentasBL = new VentasBL();
        List list = new LinkedList();
        int ind = Integer.parseInt(param.get("ind").toString());
        VentasBE oVentasBE = gson.fromJson(gson.toJson(param.get("poVentasBE")), VentasBE.class);
        oVentasBE.setIndOpSp(ind);
        list = oVentasBL.listarRegistrosVentasBE(oVentasBE);
        String json = gson.toJson(list);
        return json;
    }
}
