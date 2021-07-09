package com.greymatter.gmrecipeapp.services;


import com.greymatter.gmrecipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
