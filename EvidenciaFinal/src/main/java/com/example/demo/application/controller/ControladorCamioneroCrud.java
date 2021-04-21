package com.example.demo.application.controller;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.model.Camionero;
import com.example.demo.application.model.CamioneroCrud;

@Controller
@RequestMapping(value = "/crud2")
public class ControladorCamioneroCrud {

    @Autowired
    private CamioneroCrud camCrud;
    

    //Devolver todos los camioneros
    @RequestMapping(value="/listaCamioneros", method = RequestMethod.GET)
    public String listaCamioneros(ModelMap mp){
    	mp.put("camioneros", camCrud.findAll());
        return "crud/listaCamioneros";
    }

    //Crear un nuevo camionero
    @RequestMapping(value="/nuevoCamionero", method = RequestMethod.GET)
    public String nuevoCamionero(ModelMap mp){
        mp.put("camionero", new Camionero());
        return "crud/nuevoCamionero";
    }

    //Comprueba que no existe un registro igual en la base de datos
    @RequestMapping(value="/crearCamionero", method = RequestMethod.POST)
    public String crearCamionero(@Valid Camionero camionero, BindingResult bindingResult, ModelMap mp){
        if(bindingResult.hasErrors()){
        	mp.put("camioneros", camCrud.findAll());
            return "crud/listaCamioneros";
        } else {
            camCrud.save(camionero);
            mp.put("camionero", camionero);
            return "crud/camioneroCreado";
        }
    }

    @RequestMapping(value = "/camioneroCreado", method = RequestMethod.POST)
    public String camioneroCreado(@RequestParam("camionero") Camionero camionero){
        return "/crud/camioneroCreado";
    }
    
 // Borrar un camionero por dni
 	@RequestMapping(value = "/borrar/{dni}", method = RequestMethod.GET)
 	public String borrarCamionero(@PathVariable("dni") String dni, ModelMap mp) {
 		Optional<Camionero> optionalCamionero = camCrud.findById(dni);
 		Camionero cam = optionalCamionero.get();
 		camCrud.delete(cam);
 		mp.put("camioneros", camCrud.findAll());
 		return "crud/listaCamioneros";
 	}

 	// Editar Camion
 	@RequestMapping(value = "/editarCamionero/{dni}", method = RequestMethod.GET)
 	public String editarCamionero(@PathVariable("dni") String dni, ModelMap mp) {
 		mp.put("camionero", camCrud.findById(dni));
 		return "crud/editarCamionero";
 	}

 	// Actualizar camion
 	@RequestMapping(value = "/actualizarCamionero", method = RequestMethod.POST)
 	public String actualizarCamionero(@Valid Camionero camionero, BindingResult bindingResult, ModelMap mp) {
 		if (bindingResult.hasErrors()) {
 			mp.put("camioneros", camCrud.findAll());
 			return "crud/listaCamioneros";
 		}

 		camCrud.findById(camionero.getDni());
 		camionero.setDni(camionero.getDni());
 		camionero.setNombre(camionero.getNombre());
 		camionero.setApellidos(camionero.getApellidos());
 		camionero.setTelefono(camionero.getTelefono());
 		camionero.setDireccion(camionero.getDireccion());
 		camionero.setSalario(camionero.getSalario());
 		camionero.setPoblacion(camionero.getPoblacion());
 		camionero.setFechaNacimiento(camionero.getFechaNacimiento());
 		camionero.setDestinatarioByDniDestinatario(camionero.getDestinatarioByDniDestinatario());

 		camCrud.save(camionero);
 		mp.put("camionero", camionero);
 		return "crud/actualizarCamionero";
 	}
}
