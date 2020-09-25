package jorge.mongodb.firstapp.util;

import jorge.mongodb.firstapp.domain.*;
import jorge.mongodb.firstapp.dto.FabricanteDto;
import org.springframework.restdocs.payload.*;

import java.util.List;
import java.util.stream.*;

import static jorge.mongodb.firstapp.util.ToolBox.fieldDescriptor;

public class RecursosEstaticos {

    public static final FieldDescriptor[] productoFieldDescriptor = new FieldDescriptor[]{
            fieldDescriptor("productos[]", "Un arreglo de productos hechos por el fabricante",
                    JsonFieldType.ARRAY),
            fieldDescriptor("productos[].id", "El id de un producto hecho por el fabricante",
                    JsonFieldType.STRING),
            fieldDescriptor("productos[].nombre", "El nombre de un producto hecho por el fabricante",
                    JsonFieldType.STRING),
            fieldDescriptor("productos[].fabricanteId", "El id del fabricante del producto",
                    JsonFieldType.STRING)
    };
    public static final FieldDescriptor[] categoriaFieldDescriptor = new FieldDescriptor[]{
            fieldDescriptor("productos[].categorias[]", "Un arreglo de categorias a las que pertenece el " +
                            "producto",
                    JsonFieldType.ARRAY),
            fieldDescriptor("productos[].categorias[].id", "El id de la categoría a la cual pertenece el producto",
                    JsonFieldType.STRING),
            fieldDescriptor("productos[].categorias[].nombre", "El nombre de la categoría a la cual pertenece el " +
                    "producto", JsonFieldType.STRING)
    };

    private static final FieldDescriptor[] fabricanteFieldDescriptor = new FieldDescriptor[]{
            fieldDescriptor("id", "El id del fabricante", JsonFieldType.STRING),
            fieldDescriptor("nombre", "El nombre del fabricante", JsonFieldType.STRING),
            fieldDescriptor("direccionId", "El id de la dirección del fabricante", JsonFieldType.STRING),
            fieldDescriptor("productos", "Un arreglo de productos hechos por el fabricante", JsonFieldType.ARRAY)
    };

    public static final FieldDescriptor[] direccionFieldDescriptor = new FieldDescriptor[]{
            fieldDescriptor("direccion.id", "Id de la dirección del fabricante", JsonFieldType.STRING),
            fieldDescriptor("direccion.calle", "Calle donde reside el fabricante", JsonFieldType.STRING),
            fieldDescriptor("direccion.numeroEstablecimiento", "Número del establecimiento donde reside el " +
                    "fabricante", JsonFieldType.NUMBER),
            fieldDescriptor("direccion.entreCalles", "Las dos primeras calles que interceptan perpendicularmente la " +
                    "calle principal del establecimiento del fabricante", JsonFieldType.STRING),
            fieldDescriptor("direccion.municipio", "Municipio donde reside el fabricante", JsonFieldType.STRING),
            fieldDescriptor("direccion.estado", "Estado donde reside el fabricante", JsonFieldType.STRING),
            fieldDescriptor("direccion.pais", "País donde reside el fabricante", JsonFieldType.STRING),
            fieldDescriptor("direccion.zip", "Código postal de la zona donde reside el fabricante", JsonFieldType.NUMBER)
    };

    static List<Categoria> categorias =
            Stream.of(new Categoria(null, "electrodoméstico", null)).collect(Collectors.toList());
    static List<Producto> productos =
            Stream.of(new Producto(null, "mouse", null, null)).collect(Collectors.toList());
    static Direccion direccion = new Direccion(null, "Peralta", 3, "Costa y Lorem", "Mayari",
            "Holguín", "Cuba",
            106);
    public static FabricanteDto fabricante = new FabricanteDto(null, "Emp. Guru", null, direccion, null);
    public static FieldDescriptor[] fabricanteFieldsDescriptor = Stream.of(fabricanteFieldDescriptor,
            productoFieldDescriptor, categoriaFieldDescriptor, direccionFieldDescriptor).flatMap(Stream::of).toArray(FieldDescriptor[]::new);
}
