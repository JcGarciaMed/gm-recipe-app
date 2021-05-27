package com.greymatter.gmrecipeapp.services;

import com.greymatter.gmrecipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
