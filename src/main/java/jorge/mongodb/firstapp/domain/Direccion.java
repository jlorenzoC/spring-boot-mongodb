package jorge.mongodb.firstapp.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Direccion {
    @Id
    private String id;
    private String calle;
    private Integer numeroEstablecimiento;
    private String entreCalles;
    private String municipio;
    private String estado;
    private String pais;
    private Integer ZIP;
}
