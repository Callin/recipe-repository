package xyz.vegaone.reciperepository.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import xyz.vegaone.reciperepository.dto.Recipe;
import xyz.vegaone.reciperepository.service.RecipeService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {RecipeController.class})
class RecipeControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createShouldCreateRecipe() throws Exception {
        Recipe recipe = buildRecipe();
        String payload = objectMapper.writeValueAsString(recipe);

        when(recipeService.create(any())).thenReturn(recipe);

        mockMvc.perform(
                post("/recipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().string(payload));
    }

    @Test
    void updateShouldUpdateRecipe() throws Exception {
        Recipe recipe = buildRecipe();
        recipe.setId(1);
        String payload = objectMapper.writeValueAsString(recipe);

        when(recipeService.update(any())).thenReturn(recipe);

        mockMvc.perform(
                put("/recipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().string(payload));
    }

    @Test
    void getShouldGetRecipe() throws Exception {
        Recipe recipe = buildRecipe();
        recipe.setId(1);
        String payload = objectMapper.writeValueAsString(recipe);

        when(recipeService.get(1)).thenReturn(recipe);

        mockMvc.perform(
                get("/recipe/" + 1))
                .andExpect(status().isOk())
                .andExpect(content().string(payload));
    }

    @Test
    void deleteShouldDeleteRecipe() throws Exception {
        doNothing().when(recipeService).delete(1);

        mockMvc.perform(
                delete("/recipe/" + 1))
                .andExpect(status().isOk());
    }

    private Recipe buildRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Recipe name");
        recipe.setNumberOfStars(5);
        recipe.setCookingMethod("Some cooking method");
        recipe.setRatingDescription("Review");
        recipe.setIngredientList(Collections.emptyList());

        return recipe;
    }
}