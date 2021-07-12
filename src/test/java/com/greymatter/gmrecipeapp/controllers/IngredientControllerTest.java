package com.greymatter.gmrecipeapp.controllers;

import com.greymatter.gmrecipeapp.commands.IngredientCommand;
import com.greymatter.gmrecipeapp.commands.RecipeCommand;
import com.greymatter.gmrecipeapp.services.IngredientService;
import com.greymatter.gmrecipeapp.services.RecipeService;
import com.greymatter.gmrecipeapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class IngredientControllerTest {


    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;


    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(ingredientService, recipeService,unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void testShowIngredient() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void testUpdateIngredientForm() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        //when
        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        //then
        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/2/ingredient/3/show"));

    }
}