package xyz.vegaone.reciperepository.dto;

public enum IngredientType {
    GROUNDED("Grounded"), SOLID("Solid"), LIQUID("Liquid");

    private final String type;

    IngredientType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
