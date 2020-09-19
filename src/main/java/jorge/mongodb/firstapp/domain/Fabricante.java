package jorge.mongodb.firstapp.domain;

import jorge.mongodb.firstapp.dto.FabricanteDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Data
@Document
@NoArgsConstructor
public class Fabricante {
    @Id
    private String id;
    private String nombre;
    private String direccionId;
    @DBRef
    private List<Producto> productos = new ArrayList<>();

    public Fabricante(FabricanteDto fabricante) {
        this.id = fabricante.getId();
        this.nombre = fabricante.getNombre();
        this.direccionId = fabricante.getDireccionId();
        this.productos = fabricante.getProductos();
    }
}
