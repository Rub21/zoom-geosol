/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geosolutions.zoom.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import geosolutions.zoom.be.ClienteBE;
import geosolutions.zoom.bl.ClienteBL;
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
@RequestMapping("/ClienteController")
@SessionAttributes({"oSession"})
public class ClienteController {

    @RequestMapping(value = "/cargarCombos.htm", method = RequestMethod.POST)
    public @ResponseBody
    String cargarCombos(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {

        int indCombo = 0;
        ClienteBL oClienteBL = new ClienteBL();
        List list = new LinkedList();
        Gson gson = new Gson();
        ClienteBE oClienteBE = gson.fromJson(param.get("poClienteBE").toString(), ClienteBE.class);
        list = oClienteBL.cargarCombosFiltroCliente(oClienteBE);
        String json = new Gson().toJson(list);
        return json;

    }

    @RequestMapping(value = "/mostrarPuntosCliente.htm", method = RequestMethod.POST)
    public @ResponseBody
    String mostrarPuntosCliente(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {

        ClienteBL oClienteBL = new ClienteBL();
        List list = new LinkedList();
        Gson gson = new GsonBuilder().serializeNulls().create();
        ClienteBE oClienteBE = gson.fromJson(gson.toJson(param.get("poClienteBE")), ClienteBE.class);
        list = oClienteBL.consultarCliente(oClienteBE);
        String json = gson.toJson(list);
        return json;

    }

    @RequestMapping(value = "/consultarClienteObject.htm", method = RequestMethod.POST)
    public @ResponseBody
    String consultarClienteObject(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {

        ClienteBL oClienteBL = new ClienteBL();
        List list = new LinkedList();
        Gson gson = new GsonBuilder().serializeNulls().create();
        ClienteBE oClienteBE = gson.fromJson(gson.toJson(param.get("poClienteBE")), ClienteBE.class);
        list = oClienteBL.consultarClienteObject(oClienteBE);
        String json = gson.toJson(list);
        return json;

    }

    @RequestMapping(value = "/listaLayers.htm", method = RequestMethod.POST)
    public @ResponseBody
    String listaLayers(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {
        ClienteBL oClienteBL = new ClienteBL();
        List list = new LinkedList();
        int ind = Integer.parseInt(param.get("ind").toString());
        Gson gson = new GsonBuilder().serializeNulls().create();
        ClienteBE oClienteBE = gson.fromJson(gson.toJson(param.get("poClienteBE")), ClienteBE.class);
        oClienteBE.setIndOpSp(ind);
        list = oClienteBL.listaLayers(oClienteBE);
        String json = gson.toJson(list);
        return json;
    }

    @RequestMapping(value = "/autocompletarCliente.htm", method = RequestMethod.POST)
    public @ResponseBody
    String autocompletarCliente(@RequestBody Map<String, Object> param/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {

        ClienteBL oClienteBL = new ClienteBL();
        List list = new LinkedList();
        Gson gson = new GsonBuilder().serializeNulls().create();
        String  nombre= gson.fromJson(gson.toJson(param.get("pnvDenominacion")), String.class);
        ClienteBE oClienteBE=new ClienteBE();
        oClienteBE.setIndOpSp(2);
        oClienteBE.setNombrecliente(nombre);
        list = oClienteBL.listarRegistrosClienteBE(oClienteBE);
        String json = gson.toJson(list);
        return json;

    }

}
