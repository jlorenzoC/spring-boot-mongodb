package jorge.mongodb.firstapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jorge.mongodb.firstapp.util.RecursosEstaticos;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.*;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static jorge.mongodb.firstapp.util.RecursosEstaticos.*;
import static jorge.mongodb.firstapp.util.ToolBox.convertirASet;
import static jorge.mongodb.firstapp.util.ToolBox.removeElementsFromArray;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
class FabricanteControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<FieldDescriptor> fabricanteFieldsDescriptor;

    @BeforeEach
    void setUp(
            WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentationContextProvider
    ) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .build();
        fabricanteFieldsDescriptor = removeElementsFromArray(RecursosEstaticos.fabricanteFieldsDescriptor,
                direccionFieldDescriptor);
    }

    @Test
    void getAllFabricantes() throws Exception {
        mockMvc.perform(get("/fabricante"))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[]").description("Un arreglo de fabricantes").optional())
                                .andWithPrefix("[].", fabricanteFieldsDescriptor)));
    }

    @Test
    void postFabricante() throws Exception {
        fabricanteFieldsDescriptor.removeAll(convertirASet(productoFieldDescriptor, categoriaFieldDescriptor));
        mockMvc.perform(post("/fabricante")
                .content(objectMapper.writeValueAsString(fabricante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(RecursosEstaticos.fabricanteFieldsDescriptor),
                        responseFields(fabricanteFieldsDescriptor)
                ));
    }
}
