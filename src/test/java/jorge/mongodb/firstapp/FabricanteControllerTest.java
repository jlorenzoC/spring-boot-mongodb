package jorge.mongodb.firstapp;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private List<FieldDescriptor> fabricanteFieldDescriptor;

    @BeforeEach
    void setUp(
            WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentationContextProvider
    ) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .build();
        fabricanteFieldDescriptor = removeElementsFromArray(fabricanteFieldDescriptors, direccionFieldDescriptor);
    }

    @Test
    void getAllFabricantes() throws Exception {

        this.mockMvc.perform(get("/fabricante"))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[]").description("Un arreglo de fabricantes").optional())
                                .andWithPrefix("[].", fabricanteFieldDescriptor)));
    }

    @Test
    void postFabricante() throws Exception {
        fabricante.setId(null);
        fabricante.getDireccion().setId(null);
        this.mockMvc.perform(post("/fabricante")
                .content(objectMapper.writeValueAsString(fabricante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(fabricanteFieldDescriptors),
                        responseFields(fabricanteFieldDescriptor)
                ));
    }
}
