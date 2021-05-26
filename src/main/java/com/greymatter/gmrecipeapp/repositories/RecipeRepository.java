package com.greymatter.gmrecipeapp.repositories;

import com.greymatter.gmrecipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
