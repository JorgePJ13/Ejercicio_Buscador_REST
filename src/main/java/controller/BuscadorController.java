package controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Pagina;
import service.BuscadorService;

// Controller: se genera una vista al cliente
// ControllerRest: No hay vistas, los metodos devuelven los datos directamente al cliente
@RestController
public class BuscadorController {

	@Autowired
	BuscadorService service;

	// cuando se devuelve un objeto o lista de objetos, tiene que haber produces
	// cuando se recibe en el body, tiene que haber consumes
	
	@GetMapping(value = "Paginas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pagina> paginas() {
		return service.paginas();
	}

	/*
	 * @GetMapping(value = "Buscador", produces = MediaType.APPLICATION_JSON_VALUE)
	 * public List<Pagina> buscador(@RequestParam("tematica") String tematica) {
	 * return service.buscar(tematica); }
	 */

	@GetMapping(value = "Paginas/{tematica}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pagina> buscador(@PathVariable("tematica") String tematica) {
		return service.buscar(tematica);
	}

	@PostMapping(value = "Pagina", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alta(@RequestBody Pagina pagina) {
		service.alta(pagina);
	}

	@PutMapping(value = "Pagina", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Pagina actualizar(@RequestBody Pagina pagina) {
		return service.actualizar(pagina);
	}
	
	@PutMapping(value = "Pagina")
	public void actualizarTematica(@RequestParam("direccion") String direccion, @RequestParam("tematica") String tematica) {
		service.actualizarTematica(direccion, tematica);
	}
	
	@DeleteMapping(value = "Pagina")
	public void eliminar(@RequestParam String tematica) {
		service.eliminarTematica(tematica);
	}
}
