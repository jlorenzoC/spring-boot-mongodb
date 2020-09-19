package jorge.mongodb.firstapp.dto;

import jorge.mongodb.firstapp.domain.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class FabricanteDto {
    private String id;
    private String nombre;
    private String direccionId;
    private Direccion direccion;
    private List<Producto> productos;
}
