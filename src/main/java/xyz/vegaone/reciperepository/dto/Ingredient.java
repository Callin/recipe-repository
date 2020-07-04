package xyz.vegaone.reciperepository.dto;

import javax.validation.constraints.NotNull;

public class Ingredient {

    private Integer id;

    @NotNull(message = "name must not be null")
    private String name;

    private Integer weight;

    @NotNull(message = "measurementUnit must not be null")
    private String measurementUnit;

    private IngredientType ingredientType;

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
