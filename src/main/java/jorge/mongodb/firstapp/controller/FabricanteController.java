package jorge.mongodb.firstapp.controller;

import jorge.mongodb.firstapp.domain.Fabricante;
import jorge.mongodb.firstapp.dto.FabricanteDto;
import jorge.mongodb.firstapp.repository.FabricanteRepository;
import jorge.mongodb.firstapp.service.FabricanteService;
import jorge.mongodb.firstapp.util.CuerpoDeRespuesta;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fabricante")
public class FabricanteController {

    private final FabricanteService fabricanteService;
    private final FabricanteRepository fabricanteRepository;

    FabricanteController(
            FabricanteService fabricanteService,
            FabricanteRepository fabricanteRepository
    ) {
        this.fabricanteService = fabricanteService;
        this.fabricanteRepository = fabricanteRepository;
    }

    @PostMapping
    public Fabricante post(@RequestBody FabricanteDto fabricante) {
        return fabricanteService.postFabricante(fabricante);
    }

    @PutMapping
    private Fabricante put(@RequestBody FabricanteDto fabricante) {
        return fabricanteService.postFabricante(fabricante);
    }

    @GetMapping
    private List<Fabricante> getAll() {
        return fabricanteRepository.findAll();
    }

    @GetMapping("{fabricanteId}")
    private ResponseEntity get(@PathVariable String fabricanteId) {
        CuerpoDeRespuesta cuerpoDeRespuesta = new CuerpoDeRespuesta("El fabricante no existe");
        return fabricanteRepository.findById(fabricanteId).isPresent()
                ? new ResponseEntity<>(fabricanteRepository.findById(fabricanteId).get(), HttpStatus.FOUND)
                : new ResponseEntity<>(cuerpoDeRespuesta, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    private ResponseEntity<CuerpoDeRespuesta> delete(@RequestBody FabricanteDto fabricante) {
        fabricanteService.deleteFabricante(fabricante);
        CuerpoDeRespuesta cuerpoDeRespuesta = new CuerpoDeRespuesta("Se ha eliminado el fabricante: " + fabricante);
        return new ResponseEntity<>(cuerpoDeRespuesta, HttpStatus.OK);
    }
}

