package systems.miso.systemsdomain;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import systems.miso.systemsdomain.model.dto.SystemsUserDTO;

@SpringBootTest(classes = SystemsdomainApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SystemsDomainSystemsUserIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void testFindOneUserGetEndpointOK() throws Exception {
        mockMvc.perform(get("/api/v1/user/find/one/{externalId}", "thisIsAnExternalId")
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.externalId").value("thisIsAnExternalId"))
                .andExpect(jsonPath("$.username").value("user1"))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"));
    }

    @Test
    @WithMockUser
    void testFindOneUserGetEndpointNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/user/find/one/{externalId}", "peepeepoopoo")
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void testFindUsersGetEndpointOK() throws Exception {
        mockMvc.perform(get("/api/v1/user/find/{searchString}", "Doe")
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$..lastname", everyItem(is("Doe"))))
                .andExpect(jsonPath("$..firstname", containsInAnyOrder("John", "Jane")));
    }

    @Test
    @WithMockUser
    void testFindUsersGetEndpointNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/user/find/{searchString}", "peepeepoopoo")
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void testInsertOneUserEndpointOK() throws Exception {
        final SystemsUserDTO newUser = SystemsUserDTO.builder()
            .externalId("newExternalId")
            .username("newUsername")
            .firstname("newFirstname")
            .lastname("newLastname")
            .build();

        final String newUserJson = new ObjectMapper().writeValueAsString(newUser);

        mockMvc.perform(post("/api/v1/user/add/one")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.externalId").value("newExternalId"))
                .andExpect(jsonPath("$.username").value("newUsername"))
                .andExpect(jsonPath("$.firstname").value("newFirstname"))
                .andExpect(jsonPath("$.lastname").value("newLastname"));
    }

    @Test
    @WithMockUser
    void testInsertOneUserEndpointBadRequest() throws Exception {
        final SystemsUserDTO newUser = SystemsUserDTO.builder().build();
        final String newUserJson = new ObjectMapper().writeValueAsString(newUser);

        mockMvc.perform(post("/api/v1/user/add/one")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
                .andExpect(status().isBadRequest());
    }
}
