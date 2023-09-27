package com.adservice.Category.controller;

import com.adservice.Category.dto.CategoryDto;
import com.adservice.Category.exceptions.InvalidIdException;
import com.adservice.Category.model.Category;
import com.adservice.Category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/")
    @Operation(summary = "Add a new Category")
    public ResponseEntity<Category> add(@RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        return new ResponseEntity<>(service.add(category), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "Update an Category")
    public ResponseEntity<Category> update(@RequestBody Category category) throws InvalidIdException {
        return new ResponseEntity<>(service.update(category), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Category By Id")
    public ResponseEntity<Category> get(@PathVariable("id") int id) throws InvalidIdException {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) throws InvalidIdException {
        service.delete(id);
    }
}
