package diplom;

import java.util.List;

public class IngredientList {
    private List<String> ingredients;

    public IngredientList() {
    }

    public IngredientList(List<String> ingredients) {
        this.ingredients = ingredients;
    }


    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
