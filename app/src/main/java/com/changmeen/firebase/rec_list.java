package com.changmeen.firebase;

public class rec_list {
    String image;
    String name;
    String ingredient;
    String recipe;
    String recUrl;

    public rec_list(){}

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
    public String getRecipe() {
        return recipe;
    }

    public void setRecurl(String recUrl) {
        this.recUrl = recUrl;
    }
    public String getRecurl() {
        return recUrl;
    }
}
