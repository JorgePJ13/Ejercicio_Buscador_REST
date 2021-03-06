package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import model.Pagina;

@Service // hace que Spring cree una instancia de la clase
public class BuscadorServiceImpl implements BuscadorService {
	static ArrayList<Pagina> paginas = new ArrayList<>();

	public BuscadorServiceImpl() {
		paginas.add(new Pagina("http://www.fnac.es", "libros", "Libros y m?s cosas"));
		paginas.add(new Pagina("http://www.gamer.es", "juegos", "Juegos on-line"));
		paginas.add(new Pagina("http://www.casadellibro.es", "libros", "La Web de los libros"));
		paginas.add(new Pagina("http://www.mydisc.es", "musica", "M?sica de todo tipo"));
		paginas.add(new Pagina("http://www.radio.es", "musica", "M?sica de actualidad"));
	}

	@Override
	public List<Pagina> buscar(String tematica) {
		return paginas.stream().filter(p -> p.getTematica().equals(tematica)).collect(Collectors.toList());
	}

	@Override
	public void alta(Pagina pagina) {
		paginas.add(pagina);

	}

	@Override
	public List<Pagina> paginas() {
		return paginas;
	}

	@Override
	public Pagina actualizar(Pagina pagina) {
		for (int i = 0; i < paginas.size(); i++) {
			if (paginas.get(i).getDireccion().equals(pagina.getDireccion())) {
				paginas.set(i, pagina);
				return pagina;
			}
		}
		return null; // devolvemos null en caso de no encontrar la pagina a actualizar
	}

	@Override
	public void actualizarTematica(String direccion, String tematica) {
		paginas.forEach(p -> {
			if (p.getDireccion().equals(direccion)) {
				p.setTematica(tematica);
			}
		});
	}

	@Override
	public void eliminarTematica(String tematica) {
		// eliminar aquellas paginas donde la tematica sea igual al parametro de entrada
		paginas.removeIf(p -> p.getTematica().equals(tematica));
	}

}
