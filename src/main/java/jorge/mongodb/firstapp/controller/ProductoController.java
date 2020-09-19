package jorge.mongodb.firstapp.controller;

import jorge.mongodb.firstapp.domain.Producto;
import jorge.mongodb.firstapp.repository.ProductoRepository;
import jorge.mongodb.firstapp.service.ProductoService;
import jorge.mongodb.firstapp.util.CuerpoDeRespuesta;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("producto")
public class ProductoController {
    private final ProductoRepository productoRepository;
    private final ProductoService productoService;

    ProductoController(
            ProductoRepository productoRepository,
            ProductoService productoService
    ) {
        this.productoRepository = productoRepository;
        this.productoService = productoService;
    }

    @PostMapping
    private Producto post(@RequestBody Producto producto) {
        return productoService.postProducto(producto);
    }

    @PutMapping
    private Producto put(@RequestBody Producto producto) {
        return productoService.postProducto(producto);
    }

    @GetMapping("{productoId}")
    private ResponseEntity get(@PathVariable String productoId) {
        CuerpoDeRespuesta cuerpoDeRespuesta = new CuerpoDeRespuesta("El producto no existe");
        return productoRepository.findById(productoId).isPresent()
                ? new ResponseEntity<>(productoRepository.findById(productoId).get(), HttpStatus.FOUND)
                : new ResponseEntity<>(cuerpoDeRespuesta, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    private List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @DeleteMapping
    private ResponseEntity<CuerpoDeRespuesta> delete(@RequestBody Producto producto) {
        CuerpoDeRespuesta cuerpoDeRespuesta = new CuerpoDeRespuesta("Se ha eliminado el producto: " + producto);
        productoRepository.delete(producto);
        return new ResponseEntity<>(cuerpoDeRespuesta, HttpStatus.OK);
    }
}
