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

import com.example.demo.application.model.Paquete;
import com.example.demo.application.model.Provincia;
import com.example.demo.application.model.ProvinciaCrud;

@Controller
@RequestMapping(value = "/crud5")
public class ControladorProvinciaCrud {

    @Autowired
    private ProvinciaCrud provCrud;
	
  //Devolver todas las provincias
    @RequestMapping(value="/listaProvincias", method = RequestMethod.GET)
    public String listaProvincias(ModelMap mp){
    	mp.put("provincias", provCrud.findAll());
        return "crud/listaProvincias";
    }

    //Crear una nueva provincia
    @RequestMapping(value="/nuevoProvincia", method = RequestMethod.GET)
    public String nuevoProvincia(ModelMap mp){
        mp.put("provincia", new Provincia());
        return "crud/nuevoProvincia";
    }

    //Comprueba que no existe un registro igual en la base de datos
    @RequestMapping(value="/crearProvincia", method = RequestMethod.POST)
    public String crearProvincia(@Valid Provincia provincia, BindingResult bindingResult, ModelMap mp){
        if(bindingResult.hasErrors()){
        	mp.put("provincias", provCrud.findAll());
            return "crud/listaProvincias";
        } else {
        	provCrud.save(provincia);
            mp.put("provincia", provincia);
            return "crud/provinciaCreado";
        }
    }

    @RequestMapping(value = "/provinciaCreado", method = RequestMethod.POST)
    public String provinciaCreado(@RequestParam("provincia") Provincia provincia){
        return "/crud/provinciaCreado";
    }
    
	// Borrar una provincia por codigo
	@RequestMapping(value = "/borrar/{codigo}", method = RequestMethod.GET)
	public String borrarProvincia(@PathVariable("codigo") int codigo, ModelMap mp) {
		Optional<Provincia> optionalProvincia = provCrud.findById(codigo);
		Provincia prov = optionalProvincia.get();
		provCrud.delete(prov);
		mp.put("provincias", provCrud.findAll());
		return "crud/listaProvincias";
	}

 	// Editar Camion
 	@RequestMapping(value = "/editarProvincia/{codigo}", method = RequestMethod.GET)
 	public String editarCamion(@PathVariable("codigo") Integer codigo, ModelMap mp) {
 		mp.put("provincia", provCrud.findById(codigo));
 		return "crud/editarProvincia";
 	}
	
	// Actualizar destinatario
	@RequestMapping(value = "/actualizarProvincia", method = RequestMethod.POST)
	public String actualizarProvincia(@Valid Provincia provincia, BindingResult bindingResult, ModelMap mp) {
		if (bindingResult.hasErrors()) {
			mp.put("provincias", provCrud.findAll());
			return "crud/listaProvincias";
		}

		provCrud.findById(provincia.getCodigo());
		provincia.setCodigo(provincia.getCodigo());
		provincia.setNombre(provincia.getNombre());

		provCrud.save(provincia);
		mp.put("provincia", provincia);
		return "crud/actualizarProvincia";
	}
}
