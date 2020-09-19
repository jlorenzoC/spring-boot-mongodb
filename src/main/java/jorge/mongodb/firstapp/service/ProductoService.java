package jorge.mongodb.firstapp.service;

import jorge.mongodb.firstapp.domain.*;
import jorge.mongodb.firstapp.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Producto prodto = productoRepository.save(producto);
        for (Categoria categoria : prodto.getCategorias()) {
            List<Producto> productos = categoria.getProductos();
//            Producto prod = new Producto(prodto);
            productos.add(prodto);
            categoria.setProductos(productos);
            categoriaRepository.save(categoria);
        }
        return prodto;
    }
}
