package com.adservice.Advertisement.service;

import com.adservice.Advertisement.dto.CategoryDto;
import com.adservice.Advertisement.exceptions.InvalidIdException;
import com.adservice.Advertisement.exceptions.NoDataException;
import com.adservice.Advertisement.model.Advertisement;
import com.adservice.Advertisement.repo.AdvertisementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AdvertisementServiceImpl implements AdvertisementService{

    @Autowired private AdvertisementRepo repo;
    @Autowired private RestTemplate rest;

    /*
    * Add a new Advertisement to the database
    * returns : The details of newly created Advertisement.
    * */
    @Override
    public Advertisement add(Advertisement ad) throws InvalidIdException, RestClientResponseException {
        //TODO: rest call to check Category
        if(checkCategory(ad.getCategory())) throw new InvalidIdException("Category id "+ad.getCategory()+" is invalid");
        return repo.save(ad);
    }

    /*
     * Update an existing Advertisement in the database
     * returns : The details of updated Advertisement.
     * throws : InvalidIdException if Id is Invalid
     * */
    @Override
    public Advertisement update(Advertisement ad) throws InvalidIdException, RestClientResponseException {
        if(!repo.existsById(ad.getId())) throw new InvalidIdException("Invalid Id "+ad.getId());
        if(checkCategory(ad.getCategory())) throw new InvalidIdException("Category id "+ad.getCategory()+" is invalid");
        return repo.save(ad);
    }

    /*
     * Get an existing Advertisement in the database by Id
     * returns : The details of requested Advertisement.
     * throws : InvalidIdException if Id is Invalid
     * */
    @Override
    public Advertisement get(int id) throws InvalidIdException {
        return repo.findById(id).orElseThrow(()->new InvalidIdException("Invalid Id "+id));
    }

    /*
     * Get all the existing Advertisements in the database
     * returns : The details of all the Advertisements.
     * */
    @Override
    public List<Advertisement> getAll() {
        return repo.findAll();
    }

    /*
     * Get the details of all existing Advertisement in the database by Category.
     * returns : The details of requested Advertisement.
     * throws : NoDataException if No Advertisement is found.
     * */    @Override
    public List<Advertisement> getByCategory(int category) throws NoDataException {
        return repo.findByCategory(category).orElseThrow(()->new NoDataException("No advertisement(s) exists for category "+category));
    }

    /*
     * Delete an existing Advertisement in the database
     * throws : InvalidIdException if Id is Invalid
     * */
    @Override
    public void delete(int id) throws InvalidIdException {
        if(!repo.existsById(id)) throw new InvalidIdException("Invalid Id "+id);
        repo.deleteById(id);
    }

    private boolean checkCategory(int category) throws RestClientResponseException {
        HttpStatusCode code = rest.getForEntity("http://CATEGORY-SERVICE/category/"+category, CategoryDto.class).getStatusCode();
        return code.is4xxClientError() || code.is5xxServerError();
    }
}
