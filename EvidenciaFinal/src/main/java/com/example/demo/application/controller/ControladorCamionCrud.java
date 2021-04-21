package com.example.demo.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.application.model.Camion;
import com.example.demo.application.model.CamionCrud;

import java.util.Optional;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/crud")
public class ControladorCamionCrud {

    @Autowired
    private CamionCrud cCrud;

	//Devolver todos los camiones 
	@RequestMapping(value="", method = RequestMethod.GET) public String
	paginaPrincipal(ModelMap mp){ return "crud/paginaPrincipal"; }
	 
    //Devolver todos los camiones
    @RequestMapping(value="/listaCamiones", method = RequestMethod.GET)
    public String listaCamiones(ModelMap mp){
    	mp.put("camiones", cCrud.findAll());
        return "crud/listaCamiones";
    }

    //Crear un nuevo Camion
    @RequestMapping(value="/nuevoCamion", method = RequestMethod.GET)
    public String nuevoCamion(ModelMap mp){
        mp.put("camion", new Camion());
        return "crud/nuevoCamion";
    }

    //Comprueba que no existe un registro igual en la base de datos
    @RequestMapping(value="/crearCamion", method = RequestMethod.POST)
    public String crearCamion(@Valid Camion camion, BindingResult bindingResult, ModelMap mp){
        if(bindingResult.hasErrors()){
        	mp.put("camiones", cCrud.findAll());
            return "crud/listaCamiones";
        } else {
            cCrud.save(camion);
            mp.put("camion", camion);
            return "crud/camionCreado";
        }
    }

    @RequestMapping(value = "/camionCreado", method = RequestMethod.POST)
    public String camionCreado(@RequestParam("camion") Camion camion){
        return "/crud/camionCreado";
    }
    
 // Borrar un camion por matricula
 	@RequestMapping(value = "/borrar/{matricula}", method = RequestMethod.GET)
 	public String borrarCamion(@PathVariable("matricula") String matricula, ModelMap mp) {
 		Optional<Camion> optionalCamion = cCrud.findById(matricula);
 		Camion c = optionalCamion.get();
 		cCrud.delete(c);
 		mp.put("camiones", cCrud.findAll());
 		return "crud/listaCamiones";
 	}

 	// Editar Camion
 	@RequestMapping(value = "/editarCamion/{matricula}", method = RequestMethod.GET)
 	public String editarCamion(@PathVariable("matricula") String matricula, ModelMap mp) {
 		mp.put("camion", cCrud.findById(matricula));
 		return "crud/editarCamion";
 	}

 	// Actualizar camion
 	@RequestMapping(value = "/actualizarCamion", method = RequestMethod.POST)
 	public String actualizarCamion(@Valid Camion camion, BindingResult bindingResult, ModelMap mp) {
 		if (bindingResult.hasErrors()) {
 			mp.put("camiones", cCrud.findAll());
 			return "crud/listaCamiones";
 		}

 		cCrud.findById(camion.getMatricula());
 		camion.setMatricula(camion.getMatricula());
 		camion.setModelo(camion.getModelo());
 		camion.setPotencia(camion.getPotencia());
 		cCrud.save(camion);
 		mp.put("camion", camion);
 		return "crud/actualizarCamion";
 	} 
}
