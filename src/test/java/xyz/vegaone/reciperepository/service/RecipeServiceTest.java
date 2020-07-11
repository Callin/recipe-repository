package xyz.vegaone.reciperepository.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import xyz.vegaone.reciperepository.domain.RecipeEntity;
import xyz.vegaone.reciperepository.dto.Ingredient;
import xyz.vegaone.reciperepository.dto.Recipe;
import xyz.vegaone.reciperepository.repo.RecipeRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RecipeServiceTest {

    private static RecipeRepository recipeRepository;

    private static Mapper mapper;

    private static RecipeService recipeService;

    @BeforeAll
    public static void before() {
        mapper = DozerBeanMapperBuilder.buildDefault();
        recipeRepository = Mockito.mock(RecipeRepository.class);
        recipeService = new RecipeService(recipeRepository, mapper);
    }

    @Test
    void create_Ok() {
        // given
        Recipe recipe = buildRecipeDto(null);
        RecipeEntity recipeEntity = mapper.map(recipe, RecipeEntity.class);
        when(recipeRepository.save(any())).thenReturn(recipeEntity);

        // when
        Recipe savedRecipe = recipeService.create(recipe);

        // then
        assertNotNull(savedRecipe);
    }

    @Test
    void update() {
        // given
        Recipe recipe = buildRecipeDto(null);
        RecipeEntity recipeEntity = mapper.map(recipe, RecipeEntity.class);
        when(recipeRepository.save(any())).thenReturn(recipeEntity);

        // when
        Recipe savedRecipe = recipeService.create(recipe);

        // then
        assertNotNull(savedRecipe);
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }

    private Recipe buildRecipeDto(Integer id) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName("some recipe");
        ingredient.setMeasurementUnit("kg");
        ingredient.setWeight(10);

        Recipe recipe = new Recipe();
        recipe.setIngredientList(Collections.singletonList(ingredient));
        recipe.setRatingDescription("Some description");
        recipe.setCookingMethod("Some cooking method");
        recipe.setNumberOfStars(5);
        recipe.setName("Recipe name");

        return recipe;
    }
}