package xyz.vegaone.reciperepository.repo;

import org.springframework.data.repository.CrudRepository;
import xyz.vegaone.reciperepository.domain.RecipeEntity;

public interface RecipeRepository extends CrudRepository<RecipeEntity, Integer> {
}
