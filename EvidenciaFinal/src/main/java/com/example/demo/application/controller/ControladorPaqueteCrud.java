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

import com.example.demo.application.model.Destinatario;
import com.example.demo.application.model.Paquete;
import com.example.demo.application.model.PaqueteCrud;

@Controller
@RequestMapping(value = "/crud4")
public class ControladorPaqueteCrud {

	@Autowired
	private PaqueteCrud paqCrud;
	
	//Devolver todos los paquetes
    @RequestMapping(value="/listaPaquetes", method = RequestMethod.GET)
    public String listaPaquetes(ModelMap mp){
    	mp.put("paquetes", paqCrud.findAll());
        return "crud/listaPaquetes";
    }

    //Crear un nuevo paquete
    @RequestMapping(value="/nuevoPaquete", method = RequestMethod.GET)
    public String nuevoPaquete(ModelMap mp){
        mp.put("paquete", new Paquete());
        return "crud/nuevoPaquete";
    }

    //Comprueba que no existe un registro igual en la base de datos
    @RequestMapping(value="/crearPaquete", method = RequestMethod.POST)
    public String crearPaquete(@Valid Paquete paquete, BindingResult bindingResult, ModelMap mp){
        if(bindingResult.hasErrors()){
        	mp.put("paquetes", paqCrud.findAll());
            return "crud/listaPaquetes";
        } else {
        	paqCrud.save(paquete);
            mp.put("paquete", paquete);
            return "crud/paqueteCreado";
        }
    }

    @RequestMapping(value = "/paqueteCreado", method = RequestMethod.POST)
    public String paqueteCreado(@RequestParam("paquete") Paquete paquete){
        return "/crud/paqueteCreado";
    }
    
 // Borrar un paquete por codigo
 	@RequestMapping(value = "/borrar/{codigo}", method = RequestMethod.GET)
 	public String borrarPaquete(@PathVariable("codigo") int codigo, ModelMap mp) {
 		Optional<Paquete> optionalPaquete = paqCrud.findById(codigo);
 		Paquete paq = optionalPaquete.get();
 		paqCrud.delete(paq);
 		mp.put("paquetes", paqCrud.findAll());
 		return "crud/listaPaquetes";
 	}

 	// Editar Camion
  	@RequestMapping(value = "/editarPaquete/{codigo}", method = RequestMethod.GET)
  	public String editarPaquete(@PathVariable("codigo") Integer codigo, ModelMap mp) {
  		mp.put("paquete", paqCrud.findById(codigo));
  		return "crud/editarPaquete";
  	}
 	
 	// Actualizar destinatario
 	@RequestMapping(value = "/actualizarPaquete", method = RequestMethod.POST)
 	public String actualizarPaquete(@Valid Paquete paquete, BindingResult bindingResult, ModelMap mp) {
 		if (bindingResult.hasErrors()) {
 			mp.put("paquetes", paqCrud.findAll());
 			return "crud/listaPaquetes";
 		}

 		paqCrud.findById(paquete.getCodigo());
 		paquete.setCodigo(paquete.getCodigo());
 		paquete.setDescripcion(paquete.getDescripcion());
 		paquete.setPeso(paquete.getPeso());
 		paquete.setCodigoProvinciaFk(paquete.getCodigoProvinciaFk());
 		paquete.setProvinciaByCodigoProvinciaFk(paquete.getProvinciaByCodigoProvinciaFk());

 		paqCrud.save(paquete);
 		mp.put("paquete", paquete);
 		return "crud/actualizarPaquete";
 	}
}

