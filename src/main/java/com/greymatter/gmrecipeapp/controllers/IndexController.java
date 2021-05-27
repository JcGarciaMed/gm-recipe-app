package com.greymatter.gmrecipeapp.controllers;

import com.greymatter.gmrecipeapp.domain.Category;
import com.greymatter.gmrecipeapp.domain.UnitOfMeasure;
import com.greymatter.gmrecipeapp.repositories.CategoryRepository;
import com.greymatter.gmrecipeapp.repositories.UnitOfMeasureRepository;
import com.greymatter.gmrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLOutput;
import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/", "", "index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
