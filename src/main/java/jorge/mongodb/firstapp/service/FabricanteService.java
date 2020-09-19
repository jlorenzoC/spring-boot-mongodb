package jorge.mongodb.firstapp.service;

import jorge.mongodb.firstapp.domain.*;
import jorge.mongodb.firstapp.dto.FabricanteDto;
import jorge.mongodb.firstapp.repository.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;
    private final DireccionRepository direccionRepository;
    private final ProductoRepository productoRepository;

    FabricanteService(
            FabricanteRepository fabricanteRepository,
            DireccionRepository direccionRepository,
            ProductoRepository productoRepository
    ) {
        this.fabricanteRepository = fabricanteRepository;
        this.direccionRepository = direccionRepository;
        this.productoRepository = productoRepository;
    }

    public Fabricante postFabricante(FabricanteDto fabricante) {
        Fabricante fabricanteNuevo = new Fabricante(fabricante);
        fabricanteNuevo = fabricanteRepository.save(fabricanteNuevo);
        if (fabricante.getDireccion() != null) {
            Direccion direccion = direccionRepository.save(fabricante.getDireccion());
            fabricanteNuevo.setDireccionId(direccion.getId());
        }
        String fabricanteId = fabricanteNuevo.getId();
        fabricanteNuevo.getProductos().forEach(e -> {
            e.setFabricanteId(fabricanteId);
            productoRepository.save(e);
        });
        return fabricanteRepository.save(fabricanteNuevo);
    }

    public void deleteFabricante(FabricanteDto fabricanteDto) {
        Optional<Fabricante> fabricanteOpt = fabricanteRepository.findById(fabricanteDto.getId());
        if (fabricanteOpt.isPresent()) {
            Fabricante fabricante = fabricanteOpt.get();
            fabricanteRepository.delete(fabricante);
            direccionRepository.deleteById(fabricante.getDireccionId());
        }
    }


}
