package com.conder.groupify;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.conder.groupify.security.SecurityConstants.HEADER_STRING;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GroupifyApplication.class)
public class GroupifyApplicationTests {

  @Rule
  public final JUnitRestDocumentation restDocumentation =
      new JUnitRestDocumentation("target/generated-snippets");

  private MockMvc mockMvc;

  @Autowired private WebApplicationContext context;

  private HttpHeaders headers;

  @Before
  public void setUp() {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(this.context)
            .apply(documentationConfiguration(this.restDocumentation))
            .build();
    headers = new HttpHeaders();
    headers.add(HEADER_STRING, "Bearer {json-web-token}");
  }

  @Test
  public void artistSearch() throws Exception {
    this.mockMvc
        .perform(get("/songkick/artist-search?artist=dylan").headers(headers))
        .andExpect(status().isOk())
        .andDo(
            document(
                "artistSearch",
                requestParameters(
                    parameterWithName("artist").description("The name of the artist to search"))))
        .andDo(
            document(
                "artistSearch",
                requestHeaders(
                    headerWithName("Authorization").description("Basic auth credentials"))))
        .andDo(document("artistSearch", preprocessResponse(prettyPrint())));
  }

  @Test
  public void artistSearchNextPage() throws Exception {
    this.mockMvc
        .perform(get("/songkick/artist-search-next-page?page=1").headers(headers))
        .andExpect(status().isOk())
        .andDo(
            document(
                "artistSearchNextPage",
                requestParameters(parameterWithName("page").description("The page"))))
        .andDo(
            document(
                "artistSearchNextPage",
                requestHeaders(
                    headerWithName("Authorization").description("Basic auth credentials"))))
        .andDo(document("artistSearchNextPage", preprocessResponse(prettyPrint())));
  }

  @Test
  public void artistUpcomingEvents() throws Exception {

    this.mockMvc
        .perform(get("/songkick/upcoming-artists-events?artistId=5826564").headers(headers))
        .andExpect(status().isOk())
        .andDo(
            document(
                "artistUpcomingEvents",
                requestParameters(parameterWithName("artistId").description("SongKick artist id"))))
        .andDo(
            document(
                "artistUpcomingEvents",
                requestHeaders(
                    headerWithName("Authorization").description("Basic auth credentials"))))
        .andDo(document("artistUpcomingEvents", preprocessResponse(prettyPrint())));
  }

  @Test
  public void artistAdd() throws Exception {
    this.mockMvc
        .perform(get("/songkick/add-artist?artistId=1A3B4D5C").headers(headers))
        .andExpect(status().isOk())
        .andDo(
            document(
                "addArtist",
                requestParameters(parameterWithName("artistId").description("SongKick Artist id"))))
        .andDo(document("addArtist", preprocessResponse(prettyPrint())));
  }

  @Test
  public void contextLoads() {}
}
