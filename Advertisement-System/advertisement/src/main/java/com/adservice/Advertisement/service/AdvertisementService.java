package com.adservice.Advertisement.service;

import com.adservice.Advertisement.exceptions.InvalidIdException;
import com.adservice.Advertisement.exceptions.NoDataException;
import com.adservice.Advertisement.model.Advertisement;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

public interface AdvertisementService {
    public Advertisement add(Advertisement ad) throws InvalidIdException, RestClientResponseException;
    public Advertisement update(Advertisement ad) throws InvalidIdException, RestClientResponseException;
    public Advertisement get(int id) throws InvalidIdException;
    public List<Advertisement> getAll();
    public List<Advertisement> getByCategory(int category) throws NoDataException;
    public void delete(int id) throws InvalidIdException;
}
