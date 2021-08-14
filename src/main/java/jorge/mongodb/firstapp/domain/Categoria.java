package jorge.mongodb.firstapp.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Categoria {
    @Id
    private String id;
    private String nombre;
    @JsonIgnoreProperties(value = "categorias", allowSetters = true)
    @DBRef(lazy = true)
    private List<Producto> productos;

    public List<Producto> getProductos() {
        if (productos == null)
            return new ArrayList<>();
        return productos;
    }
}
