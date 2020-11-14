package pl.mgrzech.alcohols_collection.newsletterTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mgrzech.alcohols_collection.controllers.NewsletterController;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.newsletter.NewsletterService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewsletterControllerTests {

    @Mock
    private NewsletterService newsletterService;

    @InjectMocks
    private NewsletterController newsletterController;

    private MockMvc mockMvc;
    private final List<Newsletter> listNewsletters = new ArrayList<>();
    private final Newsletter emptyNewsletter = new Newsletter();

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(newsletterController).build();

        listNewsletters.add(new Newsletter(1, "testNameNewsletter1", "Newsletter1@TestEmail.com", 1111));
        listNewsletters.add(new Newsletter(2, "testNameNewsletter2", "Newsletter2@TestEmail.com", 2222));
    }

    @Test
    public void shouldReturnListNewsletters() throws Exception {
        when(newsletterService.findAllNewsletters()).thenReturn(listNewsletters);

        mockMvc.perform(get("/user/showAllNewsletter"))
                .andExpect(status().isOk())
                .andExpect(view().name("newsletter/all_newsletter"))
                .andExpect(model().attribute("newsletters", hasSize(listNewsletters.size())));
    }

    @Test
    public void shouldSaveNewsletterAfterCorrectValidation() throws Exception {
        mockMvc.perform(post("/newsletterAdd")
                .flashAttr("newsletter", listNewsletters.get(0)))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void shouldReturnToAddNewsletterViewAfterIncorrectValidation() throws Exception {
        mockMvc.perform(post("/newsletterAdd")
                .flashAttr("newsletter", emptyNewsletter))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void shouldReturnNewsletterToDeleteByUniqueCode() throws Exception {
        when(newsletterService.findNewsletterToDeleteByUniqueCode(1111)).thenReturn(listNewsletters.get(0));

        mockMvc.perform(get("/delete/newsletter/1111"))
                .andExpect(status().isOk())
                .andExpect(view().name("newsletter/confirm_delete_newsletter"))
                .andExpect(model().attribute("newsletter", instanceOf(Newsletter.class)))
                .andExpect(model().attribute("newsletter", listNewsletters.get(0)));
    }

    @Test
    public void shouldReturnEmptyNewsletterToDeleteByIncorrectUniqueCode() throws Exception {
        when(newsletterService.findNewsletterToDeleteByUniqueCode(3333)).thenReturn(emptyNewsletter);

        mockMvc.perform(get("/delete/newsletter/3333"))
                .andExpect(status().isOk())
                .andExpect(view().name("newsletter/confirm_delete_newsletter"))
                .andExpect(model().attribute("newsletter", instanceOf(Newsletter.class)))
                .andExpect(model().attribute("newsletter", emptyNewsletter));
    }

    @Test
    public void shouldReturnToMainViewAfterDeleteNewsletter() throws Exception {
        when(newsletterService.findNewsletterToDeleteByUniqueCode(3333)).thenReturn(emptyNewsletter);

        mockMvc.perform(post("/delete/confirmed/newsletter/1"))
                .andExpect(redirectedUrl("/"));
    }

}
