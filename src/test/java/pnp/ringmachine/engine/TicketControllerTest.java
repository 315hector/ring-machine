package pnp.ringmachine.engine;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.RestDocumentationContextProvider;

import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.options;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.misc.Version.print;

@SpringBootTest
//@SpringJUnitConfig(RestDocumentationExtension.class)
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
public class TicketControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webContext, RestDocumentationContextProvider restDocs) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .apply(documentationConfiguration(restDocs)
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8080)
                )
                .defaultRequest(options("/api/rest")
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .build();
    }


    @Test
    void getHomePage() throws Exception {

//        ResultActions actions = this.mockMvc.perform(get("/api/rest"))
//                .andDo(print());
    }

    @Test
    void getTicket() throws Exception {
        ResultActions actions = this.mockMvc.perform(get("/api/rest/tickets"))
                .andExpect(status().isOk()).andDo(print());

        actions.andDo(document("{class-name}/{method-name}",
                responseFields(
                        fieldWithPath("label").description("это заголовок тикета"),
                        fieldWithPath("description").description("подробное опизание для дальнейшей обработки"),
                        fieldWithPath("status").description("описывает состояние в данный момент (например в работе)"),
                        fieldWithPath("project").description("привязка к namespace"),
                        fieldWithPath("created").description("время когда был заведён систему (не меняется)"),
                        fieldWithPath("updated").description("время последней модификации"),
                        fieldWithPath("type").description("тип запроса (баг, задача или подзадача)"),
                        subsectionWithPath("_links").description("<<resources-tags-links, Links>> to Speakers HATEOAS")
                ), links(halLinks(),
                        linkWithRel("self").description("ссылка на объект коллекции"),
                        linkWithRel("href").description("url на объект коллекции"))
                ));
    }

    @Test
    void createTicket() throws Exception {

        String bodyRequest = "{\n" +
                "\t\"label\" : \"допweили модуль срочно!!!\",\n" +
                "    \"description\" : \"сроwefк на данный модуль до пятницы\",\n" +
                "    \"status\" : \"IN_PROGRESS\",\n" +
                "    \"project\" : \"PRJ-wef02\",\n" +
                "    \"created\" : \"creatwefe data format?\",\n" +
                "    \"updated\" : \"updatewefd data format?\",\n" +
                "    \"type\" : \"TASK\"\n" +
                "}";

        ResultActions actions = this.mockMvc.perform(post("/tickets").content(bodyRequest))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/tickets/1")));

    }
}