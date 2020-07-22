package xyz.vegaone.reciperepository.service;

import org.springframework.stereotype.Service;
import xyz.vegaone.reciperepository.domain.RecipeEntity;
import xyz.vegaone.reciperepository.dto.Recipe;
import xyz.vegaone.reciperepository.mapper.MapperUtil;
import xyz.vegaone.reciperepository.repo.RecipeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe create(Recipe recipe) {
        RecipeEntity recipeEntity = recipeRepository.save(MapperUtil.map(recipe, RecipeEntity.class));
        return MapperUtil.map(recipeEntity, Recipe.class);
    }

    public Recipe update(Recipe recipe) {
        RecipeEntity recipeEntity = recipeRepository.save(MapperUtil.map(recipe, RecipeEntity.class));
        return MapperUtil.map(recipeEntity, Recipe.class);
    }

    public Recipe get(Integer recipeId) {
        return MapperUtil.map(
                recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new),
                Recipe.class);
    }

    public List<Recipe> getAll() {
        return MapperUtil.mapIterable(recipeRepository.findAll(), Recipe.class);
    }

    public void delete(Integer id) {
        recipeRepository.deleteById(id);
    }
}
