package jorge.mongodb.firstapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Producto {
    @Id
    private String id;
    private String nombre;
    @JsonIgnoreProperties(value = "productos", allowSetters = true)
    @DBRef(lazy = true)
    private List<Categoria> categorias = new ArrayList<>();
    private String fabricanteId;
}
