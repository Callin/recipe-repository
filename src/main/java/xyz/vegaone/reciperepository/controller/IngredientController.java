package xyz.vegaone.reciperepository.controller;

import org.springframework.web.bind.annotation.*;
import xyz.vegaone.reciperepository.dto.Ingredient;
import xyz.vegaone.reciperepository.service.IngredientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient create(@RequestBody @Valid Ingredient ingredient) {
        return ingredientService.create(ingredient);
    }

    @PutMapping
    public Ingredient update(@RequestBody @Valid Ingredient ingredient) {
        return ingredientService.update(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient get(@PathVariable Integer id) {
        return ingredientService.get(id);
    }

    @GetMapping("/all")
    public List<Ingredient> getAll() {
        return ingredientService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        ingredientService.delete(id);
    }
}
