package xyz.vegaone.reciperepository.service;

import com.github.dozermapper.core.Mapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.reciperepository.domain.RecipeEntity;
import xyz.vegaone.reciperepository.dto.Recipe;
import xyz.vegaone.reciperepository.repo.RecipeRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final Mapper mapper;

    public RecipeService(RecipeRepository recipeRepository,
                         Mapper mapper) {
        this.recipeRepository = recipeRepository;
        this.mapper = mapper;
    }

    public Recipe create(Recipe recipe) {
        RecipeEntity recipeEntity = recipeRepository.save(mapper.map(recipe, RecipeEntity.class));
        return mapper.map(recipeEntity, Recipe.class);
    }

    public Recipe update(Recipe recipe) {
        RecipeEntity recipeEntity = recipeRepository.save(mapper.map(recipe, RecipeEntity.class));
        return mapper.map(recipeEntity, Recipe.class);
    }

    public Recipe get(Integer recipeId) {
        return mapper.map(
                recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new),
                Recipe.class);
    }

    public void delete(Integer id) {
        recipeRepository.deleteById(id);
    }
}
