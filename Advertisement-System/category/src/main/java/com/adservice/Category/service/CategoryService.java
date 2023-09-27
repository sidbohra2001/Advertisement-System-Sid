package com.adservice.Category.service;

import com.adservice.Category.exceptions.InvalidIdException;
import com.adservice.Category.exceptions.NoDataException;
import com.adservice.Category.model.Category;

import java.util.List;

public interface CategoryService {
    public Category add(Category category);
    public Category update(Category category) throws InvalidIdException;
    public Category get(int id) throws InvalidIdException;
    public void delete(int id) throws InvalidIdException;
}
