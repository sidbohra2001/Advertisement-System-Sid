package com.adservice.Advertisement.controller;

import com.adservice.Advertisement.dto.AdvertisementDto;
import com.adservice.Advertisement.exceptions.InvalidIdException;
import com.adservice.Advertisement.exceptions.NoDataException;
import com.adservice.Advertisement.model.Advertisement;
import com.adservice.Advertisement.service.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@RestController
public class AdvertisementController {
    @Autowired private AdvertisementService service;

    @PostMapping("/")
    @Operation(summary = "Add a new Advertisement")
    public ResponseEntity<Advertisement> add(@RequestBody AdvertisementDto adDto) throws InvalidIdException, RestClientResponseException {
        Advertisement ad = new Advertisement();
        BeanUtils.copyProperties(adDto, ad);
        return new ResponseEntity<>(service.add(ad), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "Update an Advertisement")
    public ResponseEntity<Advertisement> update(@RequestBody Advertisement ad) throws InvalidIdException, RestClientResponseException {
        return new ResponseEntity<>(service.update(ad), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Advertisement By Id")
    public ResponseEntity<Advertisement> get(@PathVariable("id") int id) throws InvalidIdException {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Get All Advertisements")
    public ResponseEntity<List<Advertisement>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("?category={category}")
    @Operation(summary = "Get Advertisement By Id")
    public ResponseEntity<List<Advertisement>> getByCategory(@RequestParam("category") int category) throws NoDataException {
        return new ResponseEntity<>(service.getByCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    // Be consistent in the approach, you can use ResponseEntity<Void> 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) throws InvalidIdException {
        service.delete(id);
    }

}
