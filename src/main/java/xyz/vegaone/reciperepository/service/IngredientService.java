package xyz.vegaone.reciperepository.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import xyz.vegaone.reciperepository.domain.IngredientEntity;
import xyz.vegaone.reciperepository.dto.Ingredient;
import xyz.vegaone.reciperepository.mapper.MapperUtil;
import xyz.vegaone.reciperepository.repo.IngredientRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class IngredientService {

    private final IngredientRepository ingredientRepository;


    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient create(@NotNull Ingredient ingredient) {
        IngredientEntity ingredientEntity = ingredientRepository.save(MapperUtil.map(ingredient, IngredientEntity.class));
        return MapperUtil.map(ingredientEntity, Ingredient.class);
    }

    public Ingredient update(@NotNull Ingredient ingredient) {
        IngredientEntity ingredientEntity = ingredientRepository.save(MapperUtil.map(ingredient, IngredientEntity.class));
        return MapperUtil.map(ingredientEntity, Ingredient.class);
    }

    public Ingredient get(Integer ingredientId) {
        return MapperUtil.map(
                ingredientRepository.findById(ingredientId).orElseThrow(EntityNotFoundException::new),
                Ingredient.class);
    }

    public List<Ingredient> getAll() {
        return MapperUtil.mapIterable(ingredientRepository.findAll(), Ingredient.class);
    }

    public void delete(Integer ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
