package jorge.mongodb.firstapp.service;

import jorge.mongodb.firstapp.domain.*;
import jorge.mongodb.firstapp.dto.FabricanteDto;
import jorge.mongodb.firstapp.repository.*;
import org.springframework.stereotype.Service;

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

    public Fabricante postFabricante(FabricanteDto fabricanteDto) {
        Fabricante fabricante = guardarFabricanteConSuDireccion(fabricanteDto);
        asociarFabricanteASuListaDeProductos(fabricante);
        return fabricante;
    }

    private Fabricante guardarFabricanteConSuDireccion(FabricanteDto fabricanteDto) {
        return fabricanteRepository.save(new Fabricante(crearDireccionYAsociarlaAlFabricante(fabricanteDto)));
    }

    private FabricanteDto crearDireccionYAsociarlaAlFabricante(FabricanteDto fabricanteDto) {
        Direccion direccionDto = fabricanteDto.getDireccion();
        String direccionId = fabricanteDto.getDireccionId();
        Direccion direccion = direccionRepository.findById(direccionId == null ? "" : direccionId)
                .map(direccionEnBaseDeDatos -> actualizarDireccionSiSonDistintitas(direccionEnBaseDeDatos, direccionDto))
                .orElse(direccionDto != null ? direccionRepository.save(direccionDto) : null);
        if (direccion != null) fabricanteDto.setDireccionId(direccion.getId());
        return fabricanteDto;
    }

    private Direccion actualizarDireccionSiSonDistintitas(Direccion d1, Direccion d2) {
        if (!d1.equals(d2) && d2 != null) return direccionRepository.save(d2);
        return d1;
    }

    private void asociarFabricanteASuListaDeProductos(Fabricante fabricante) {
        fabricante.getProductos().forEach(producto -> {
            producto.setFabricanteId(fabricante.getId());
            productoRepository.save(producto);
        });
    }

    public void deleteFabricante(FabricanteDto fabricanteDto) {
        fabricanteRepository.findById(fabricanteDto.getId()).map(fabricante -> {
            fabricanteRepository.delete(fabricante);
            direccionRepository.deleteById(fabricante.getDireccionId());
            return fabricante;
        }).get();
    }
}
