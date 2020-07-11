package xyz.vegaone.reciperepository.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import xyz.vegaone.reciperepository.domain.IngredientEntity;
import xyz.vegaone.reciperepository.dto.Ingredient;
import xyz.vegaone.reciperepository.repo.IngredientRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class IngredientServiceTest {

    private static IngredientRepository ingredientRepository;

    private static Mapper mapper;

    private static IngredientService ingredientService;

    @BeforeAll
    public static void before() {
        mapper = DozerBeanMapperBuilder.buildDefault();
        ingredientRepository = Mockito.mock(IngredientRepository.class);
        ingredientService = new IngredientService(ingredientRepository, mapper);
    }

    @Test
    void testCreate_Ok() {
        // given
        Ingredient ingredient = buildIngredientDto(null);
        IngredientEntity ingredientEntity = mapper.map(ingredient, IngredientEntity.class);
        when(ingredientRepository.save(any())).thenReturn(ingredientEntity);

        // when
        Ingredient savedIngredient = ingredientService.create(ingredient);

        // then
        assertNotNull(savedIngredient);
    }

    @Test
    void testUpdate_Ok() {
        // given
        Ingredient ingredient = buildIngredientDto(null);
        IngredientEntity ingredientEntity = mapper.map(ingredient, IngredientEntity.class);
        when(ingredientRepository.save(any())).thenReturn(ingredientEntity);

        // when
        Ingredient savedIngredient = ingredientService.update(ingredient);

        // then
        assertNotNull(savedIngredient);
    }


    private Ingredient buildIngredientDto(Integer id) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName("some recipe");
        ingredient.setMeasurementUnit("kg");
        ingredient.setWeight(10);
        return ingredient;
    }
}