package pl.mgrzech.alcohols_collection.userTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mgrzech.alcohols_collection.controllers.UserController;
import pl.mgrzech.alcohols_collection.entities.User;
import pl.mgrzech.alcohols_collection.user.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private final List<User> listAllUsers = new ArrayList<>();
    private final User emptyUser = new User();

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        listAllUsers.add(new User(1, "nameAdmin", "passwordAdmin", true, "Admin"));
        listAllUsers.add(new User(2, "nameUser", "passwordUser", true, "User"));
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {
        when(userService.findAllUser()).thenReturn(listAllUsers);

        mockMvc.perform(get("/admin/user/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/all_users"))
                .andExpect(model().attribute("users", hasSize(listAllUsers.size())));
    }

    @Test
    public void shouldReturnOneUserSearchedById() throws Exception {
        when(userService.findUserById(1)).thenReturn(listAllUsers.get(0));

        mockMvc.perform(get("/admin/user/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add_user"))
                .andExpect(model().attribute("user", instanceOf(User.class)))
                .andExpect(model().attribute("user", listAllUsers.get(0)));
    }

    @Test
    public void shouldReturnEmptyUserSearchedById() throws Exception {
        when(userService.findUserById(3)).thenReturn(emptyUser);

        mockMvc.perform(get("/admin/user/edit/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add_user"))
                .andExpect(model().attribute("user", instanceOf(User.class)))
                .andExpect(model().attribute("user", emptyUser));
    }

    @Test
    public void shouldShowAddSortTypeView() throws Exception {
        mockMvc.perform(get("/admin/user/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", instanceOf(User.class)))
                .andExpect(view().name("user/add_user"));
    }

    @Test
    public void shouldReturnAddUserViewAfterCorrectValidation() throws Exception {
        mockMvc.perform(post("/admin/user/add")
                .flashAttr("user", listAllUsers.get(0)))
                .andExpect(redirectedUrl("/admin"));
    }

    @Test
    public void shouldReturnAddUserViewAfterIncorrectValidation() throws Exception {
        mockMvc.perform(post("/admin/user/add")
                .flashAttr("user", emptyUser))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add_user"));
    }
}
