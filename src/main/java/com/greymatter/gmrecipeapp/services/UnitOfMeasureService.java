package com.greymatter.gmrecipeapp.services;

import com.greymatter.gmrecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}