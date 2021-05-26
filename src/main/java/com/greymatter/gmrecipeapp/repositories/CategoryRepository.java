package com.greymatter.gmrecipeapp.repositories;

import com.greymatter.gmrecipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
