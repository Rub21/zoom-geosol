/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geosolutions.zoom.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import geosolutions.zoom.be.ClienteBE;
import geosolutions.zoom.bl.ClienteBL;
import geosolutions.zoom.da.TestConection;
import geosolutions.zoom.system.XMLConfiguration;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
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
@RequestMapping("/LoadController")
@SessionAttributes({"oSession"})
public class LoadController {

    @RequestMapping(value = "/init.htm", method = RequestMethod.POST)
    public @ResponseBody
    String init(@RequestBody Map<String, Object> param, HttpServletRequest request/*,@ModelAttribute("oSession") Session oSession,ModelMap model*/) {

        ServletContext sc = request.getServletContext();
        String path = sc.getRealPath("/");
        String json = "";
        XMLConfiguration xmlManager = new XMLConfiguration();
        if (!xmlManager.readXmlParametrosSystem(path)) {
            
            json = new Gson().toJson("false");
        } else {
            
            TestConection otConection=new TestConection();
            try {
                if(otConection.testConection()==null){
                      json = new Gson().toJson("false");
                }
                else{
                    json = new Gson().toJson("true");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoadController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return json;
    }

}
