package id.objectmethod.relazioni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.objectmethod.relazioni.domain.Figlio;
import id.objectmethod.relazioni.domain.Padre;
import id.objectmethod.relazioni.repo.PadreRepository;

@RestController
@RequestMapping("/api/padre")
public class PadreController {

	@Autowired
	private PadreRepository pRepo;

	@GetMapping("/{id}/find")
	public Padre findById(@PathVariable("id") Long id) {
		Padre p = pRepo.findById(id).get();

		List<Figlio> figli = p.getFigli();
		System.out.println("Ha " + figli.size() + " figli");

		return p;
	}

	@GetMapping("/birichini")
	public List<Padre> findBirichini() {
		List<Padre> p = pRepo.findWithAmanteOverEighty();
		return p;
	}

	@PostMapping("/save")
	public Padre savePadre(@RequestBody Padre padre) {
		for (Figlio f : padre.getFigli()) {
			f.setPadre(padre);
		}
		Padre p = pRepo.save(padre);
		return p;
	}

}
