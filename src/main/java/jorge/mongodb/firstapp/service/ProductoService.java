package jorge.mongodb.firstapp.service;

import jorge.mongodb.firstapp.domain.*;
import jorge.mongodb.firstapp.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository
    ) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Producto postProducto(Producto producto) {
        Producto productoGuardado = productoRepository.save(producto);
        for (Categoria categoria : productoGuardado.getCategorias()) {
            List<Producto> productos = categoria.getProductos();
            if (productos == null)
                productos = new ArrayList<>();
            productos.add(productoGuardado);
            categoria.setProductos(productos);
            categoriaRepository.save(categoria);
        }
        return productoGuardado;
    }
}
