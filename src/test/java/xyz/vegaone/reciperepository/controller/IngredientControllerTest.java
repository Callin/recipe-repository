package xyz.vegaone.reciperepository.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import xyz.vegaone.reciperepository.configuration.security.RecipeRepositoryBasicAuthenticationEntryPoint;
import xyz.vegaone.reciperepository.dto.Ingredient;
import xyz.vegaone.reciperepository.service.IngredientService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {IngredientController.class})
class IngredientControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private IngredientService ingredientService;

    @MockBean
    private RecipeRepositoryBasicAuthenticationEntryPoint recipeRepositoryBasicAuthenticationEntryPoint;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void createShouldCreateIngredient() throws Exception {
        Ingredient ingredient = getIngredient();
        String content = objectMapper.writeValueAsString(ingredient);

        when(ingredientService.create(any())).thenReturn(ingredient);

        mockMvc.perform(
                post("/ingredient")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }

    @Test
    @WithMockUser
    void createShouldCreateIngredientInvalidBody() throws Exception {
        Ingredient ingredient = new Ingredient();
        String content = objectMapper.writeValueAsString(ingredient);

        when(ingredientService.create(any())).thenReturn(ingredient);

        mockMvc.perform(
                post("/ingredient")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.error", Matchers.containsString("measurementUnit must not be null")))
                .andExpect(jsonPath("$.error", Matchers.containsString("name must not be null")));
    }

    @Test
    @WithMockUser
    void updateShouldUpdateTheIngredient() throws Exception {
        Ingredient ingredient = getIngredient();
        ingredient.setId(1);
        String content = objectMapper.writeValueAsString(ingredient);

        when(ingredientService.update(any())).thenReturn(ingredient);

        mockMvc.perform(
                put("/ingredient")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }

    @Test
    @WithMockUser
    void getShouldGetTheIngredient() throws Exception {
        Ingredient ingredient = getIngredient();
        ingredient.setId(1);
        String content = objectMapper.writeValueAsString(ingredient);

        when(ingredientService.get(1)).thenReturn(ingredient);

        mockMvc.perform(
                get("/ingredient/" + 1))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }

    @Test
    @WithMockUser
    void deleteShouldDeleteIngredient() throws Exception {
        doNothing().when(ingredientService).delete(1);

        mockMvc.perform(
                delete("/ingredient/" + 1))
                .andExpect(status().isOk());
    }

    private Ingredient getIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Some ingredient name");
        ingredient.setMeasurementUnit("Some measurement unit");
        ingredient.setWeight(100);
        return ingredient;
    }
}