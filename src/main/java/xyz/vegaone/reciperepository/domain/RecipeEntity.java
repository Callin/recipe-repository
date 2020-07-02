package xyz.vegaone.reciperepository.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient"))
    private List<IngredientEntity> ingredientList;

    private String cookingMethod;

    private String review;

    private Integer numberOfStars;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientEntity> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<IngredientEntity> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String ratingDescription) {
        this.review = ratingDescription;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }
}
