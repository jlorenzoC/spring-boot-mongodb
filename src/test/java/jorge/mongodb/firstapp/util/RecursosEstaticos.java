package jorge.mongodb.firstapp.util;

import jorge.mongodb.firstapp.domain.*;
import jorge.mongodb.firstapp.dto.FabricanteDto;
import org.springframework.restdocs.payload.*;

import java.util.List;
import java.util.stream.*;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class RecursosEstaticos {

    public static final FieldDescriptor[] productoFieldDescriptor = new FieldDescriptor[]{
            fieldWithPath("productos[]").description("Un arreglo de productos hechos por el fabricante").type(JsonFieldType.ARRAY).optional(),
            fieldWithPath("productos[].id").description("El id de un producto hecho por el fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("productos[].nombre").description("El nombre de un producto hecho por el fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("productos[].fabricanteId").description("El id del fabricante del producto").type(JsonFieldType.STRING).optional()
    };
    public static final FieldDescriptor[] categoriaFieldDescriptor = new FieldDescriptor[]{
            fieldWithPath("productos[].categorias[]").description("Un arreglo de categorias a las que pertenece el producto").type(JsonFieldType.ARRAY).optional(),
            fieldWithPath("productos[].categorias[].id").description("El id de la categoría a la cual pertenece el producto").type(JsonFieldType.STRING).optional(),
            fieldWithPath("productos[].categorias[].nombre").description("El nombre de la categoría a la cual pertenece el producto").type(JsonFieldType.STRING).optional()
    };

    public static final FieldDescriptor[] fabricanteFieldDescriptor = new FieldDescriptor[]{
            fieldWithPath("id").description("El id del fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("nombre").description("El nombre del fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("direccionId").description("El id de la dirección del fabricante").type(JsonFieldType.STRING).optional()
    };

    public static final FieldDescriptor[] direccionFieldDescriptor = new FieldDescriptor[]{
            fieldWithPath("direccion.id").description("Id de la dirección del fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("direccion.calle").description("Calle donde reside el fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("direccion.numeroEstablecimiento").description("Número del establecimiento donde reside el fabricante").type(JsonFieldType.NUMBER).optional(),
            fieldWithPath("direccion.entreCalles").description( "Las dos primeras calles que interceptan perpendicularmente la calle principal del establecimiento del fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("direccion.municipio").description("Municipio donde reside el fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("direccion.estado").description("Estado donde reside el fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("direccion.pais").description("País donde reside el fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("direccion.zip").description("Código postal de la zona donde reside el fabricante").type(JsonFieldType.NUMBER).optional(),
    };

    static List<Categoria> categorias = Stream.of(new Categoria("id1categoria", "electrodoméstico", null)).collect(Collectors.toList());
    static List<Producto> productos = Stream.of(new Producto("id1producto", "Monitor", categorias, null)).collect(Collectors.toList());
    static Direccion direccion = new Direccion("1234ewr", "Peralta", 3, "Costa y Lorem", "Mayari", "Holguín", "Cuba", 106);
    public static FabricanteDto fabricante = new FabricanteDto("123rije", "Emp. Guru", null, direccion, productos);

    public static FieldDescriptor[] fabricanteFieldDescriptors = Stream.of(fabricanteFieldDescriptor, productoFieldDescriptor, categoriaFieldDescriptor, direccionFieldDescriptor).flatMap(Stream::of).toArray(FieldDescriptor[]::new);
}
