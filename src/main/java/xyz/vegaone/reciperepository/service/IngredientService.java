package xyz.vegaone.reciperepository.service;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import xyz.vegaone.reciperepository.domain.IngredientEntity;
import xyz.vegaone.reciperepository.dto.Ingredient;
import xyz.vegaone.reciperepository.repo.IngredientRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    private final Mapper mapper;

    public IngredientService(IngredientRepository ingredientRepository,
                             Mapper mapper) {
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

    public Ingredient create(@NotNull Ingredient ingredient) {
        IngredientEntity ingredientEntity = ingredientRepository.save(mapper.map(ingredient, IngredientEntity.class));
        return mapper.map(ingredientEntity, Ingredient.class);
    }

    public Ingredient update(@NotNull Ingredient ingredient) {
        IngredientEntity ingredientEntity = ingredientRepository.save(mapper.map(ingredient, IngredientEntity.class));
        return mapper.map(ingredientEntity, Ingredient.class);
    }

    public Ingredient get(Integer ingredientId) {
        return mapper.map(
                ingredientRepository.findById(ingredientId).orElseThrow(EntityNotFoundException::new),
                Ingredient.class);
    }

    public void delete(Integer ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
