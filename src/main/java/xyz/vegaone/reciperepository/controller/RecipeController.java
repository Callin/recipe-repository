package xyz.vegaone.reciperepository.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.reciperepository.dto.Recipe;
import xyz.vegaone.reciperepository.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe recipe) {
        return recipeService.create(recipe);
    }

    @PutMapping
    public Recipe update(@RequestBody Recipe recipe) {
        return recipeService.update(recipe);
    }

    @GetMapping("/{id}")
    public Recipe get(@PathVariable Integer id) {
        return recipeService.get(id);
    }

    @GetMapping("/all")
    public List<Recipe> getAll() {
        return recipeService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        recipeService.delete(id);
    }
}
