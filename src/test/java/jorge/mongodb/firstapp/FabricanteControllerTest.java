package jorge.mongodb.firstapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.*;
import org.springframework.restdocs.payload.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
public class FabricanteControllerTest {

    private MockMvc mockMvc;

    private static FieldDescriptor[] fabricante = new FieldDescriptor[]{
            fieldWithPath("id").description("El id del fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("nombre").description("El nombre del fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("direccionId").description("El id de la dirección del fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("productos[]").description("Un arreglo de productos hechos por el fabricante").type(JsonFieldType.ARRAY).optional(),
            fieldWithPath("productos[].id").description("El id de un producto hecho por el fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("productos[].nombre").description("El nombre de un producto hecho por el fabricante").type(JsonFieldType.STRING).optional(),
            fieldWithPath("productos[].fabricanteId").description("El id del fabricante del producto").type(JsonFieldType.STRING).optional(),
            fieldWithPath("productos[].categorias[]").description("Un arreglo de categorias a las que pertenece el producto").type(JsonFieldType.ARRAY).optional(),
            fieldWithPath("productos[].categorias[].id").description("El id de la categoría a la cual pertenece el producto").type(JsonFieldType.STRING).optional(),
            fieldWithPath("productos[].categorias[].nombre").description("El nombre de la categoría a la cual pertenece el producto").type(JsonFieldType.STRING).optional()
    };

    @BeforeEach
    public void setUp(
            WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentationContextProvider
    ) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .build();
    }

    @Test
    public void getAllFabricantes() throws Exception {
        this.mockMvc.perform(get("/fabricante"))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[]").description("Un arreglo de fabricantes"))
                                .andWithPrefix("[].", fabricante)
                ));
    }
}
