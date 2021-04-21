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
import com.example.demo.application.model.Destinatario;
import com.example.demo.application.model.DestinatarioCrud;

@Controller
@RequestMapping(value = "/crud")
public class ControladorDestinatarioCrud {

    @Autowired
    private DestinatarioCrud destCrud;
	
  //Devolver todos los destinatarios
    @RequestMapping(value="/listaDestinatarios", method = RequestMethod.GET)
    public String listaDestinatarios(ModelMap mp){
    	mp.put("destinatarios", destCrud.findAll());
        return "crud/listaDestinatarios";
    }

    //Crear un nuevo destinatario
    @RequestMapping(value="/nuevoDestinatario", method = RequestMethod.GET)
    public String nuevoDestinatario(ModelMap mp){
        mp.put("destinatario", new Destinatario());
        return "crud/nuevoDestinatario";
    }

    //Comprueba que no existe un registro igual en la base de datos
    @RequestMapping(value="/crearDestinatario", method = RequestMethod.POST)
    public String crearDestinatario(@Valid Destinatario destinatario, BindingResult bindingResult, ModelMap mp){
        if(bindingResult.hasErrors()){
        	mp.put("destinatarios", destCrud.findAll());
            return "crud/listaDestinatarios";
        } else {
        	destCrud.save(destinatario);
            mp.put("destinatario", destinatario);
            return "crud/destinatarioCreado";
        }
    }

    @RequestMapping(value = "/destinatarioCreado", method = RequestMethod.POST)
    public String destinatarioCreado(@RequestParam("destinatario") Destinatario destinatario){
        return "/crud/destinatarioCreado";
    }
    
 // Borrar un camionero por dni
 	@RequestMapping(value = "/borrar/{dni}", method = RequestMethod.GET)
 	public String borrarDestinatario(@PathVariable("dni") String dni, ModelMap mp) {
 		Optional<Destinatario> optionalDestinatario = destCrud.findById(dni);
 		Destinatario dest = optionalDestinatario.get();
 		destCrud.delete(dest);
 		mp.put("destinatarios", destCrud.findAll());
 		return "crud/listaDestinatarios";
 	}

 	// Editar Destinatario
 	@RequestMapping(value = "/editarDestinatario/{dni}", method = RequestMethod.GET)
 	public String editarCamion(@PathVariable("dni") String dni, ModelMap mp) {
 		mp.put("destinatario", destCrud.findById(dni));
 		return "crud/editarDestinatario";
 	}
 	
 	// Actualizar destinatario
 	@RequestMapping(value = "/actualizarDestinatario", method = RequestMethod.POST)
 	public String actualizarDestinatario(@Valid Destinatario destinatario, BindingResult bindingResult, ModelMap mp) {
 		if (bindingResult.hasErrors()) {
 			mp.put("destinatarios", destCrud.findAll());
 			return "crud/listaDestinatarios";
 		}

 		destCrud.findById(destinatario.getDni());
 		destinatario.setDni(destinatario.getDni());
 		destinatario.setNombre(destinatario.getNombre());
 		destinatario.setApellidos(destinatario.getApellidos());
 		destinatario.setDireccion(destinatario.getDireccion());
 		destinatario.setCp(destinatario.getCp());

 		destCrud.save(destinatario);
 		mp.put("destinatario", destinatario);
 		return "crud/actualizarDestinatario";
 	}
}
