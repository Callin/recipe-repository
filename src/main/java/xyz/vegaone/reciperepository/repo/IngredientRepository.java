package xyz.vegaone.reciperepository.repo;

import org.springframework.data.repository.CrudRepository;
import xyz.vegaone.reciperepository.domain.IngredientEntity;

public interface IngredientRepository extends CrudRepository<IngredientEntity, Integer> {
}
