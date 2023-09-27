package com.adservice.Category.service;

import com.adservice.Category.exceptions.InvalidIdException;
import com.adservice.Category.exceptions.NoDataException;
import com.adservice.Category.model.Category;
import com.adservice.Category.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired private CategoryRepo repo;

    /*
    * Add a new Category to the database
    * returns : The details of newly created Category.
    * */
    @Override
    public Category add(Category category) {
        return repo.save(category);
    }

    /*
     * Update an existing Category in the database
     * returns : The details of updated Category.
     * throws : InvalidIdException if Id is Invalid
     * */
    @Override
    public Category update(Category category) throws InvalidIdException {
        if(!repo.existsById(category.getId())) throw new InvalidIdException("Invalid Id "+category.getId());
        return repo.save(category);
    }

    /*
     * Get an existing Category in the database by Id
     * returns : The details of requested Category.
     * throws : InvalidIdException if Id is Invalid
     * */
    @Override
    public Category get(int id) throws InvalidIdException {
        return repo.findById(id).orElseThrow(()->new InvalidIdException("Invalid Id "+id));
    }

    /*
     * Delete an existing Category in the database
     * throws : InvalidIdException if Id is Invalid
     * */
    @Override
    public void delete(int id) throws InvalidIdException {
        if(!repo.existsById(id)) throw new InvalidIdException("Invalid Id "+id);
        repo.deleteById(id);
    }
}
