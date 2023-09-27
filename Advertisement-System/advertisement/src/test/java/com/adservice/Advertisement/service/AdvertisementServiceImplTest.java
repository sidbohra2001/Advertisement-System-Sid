package com.adservice.Advertisement.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.adservice.Advertisement.dto.CategoryDto;
import com.adservice.Advertisement.enums.Type;
import com.adservice.Advertisement.exceptions.InvalidIdException;
import com.adservice.Advertisement.exceptions.NoDataException;
import com.adservice.Advertisement.model.Advertisement;
import com.adservice.Advertisement.repo.AdvertisementRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {AdvertisementServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdvertisementServiceImplTest {
    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private AdvertisementRepo advertisementRepo;

    @Autowired
    private AdvertisementServiceImpl advertisementServiceImpl;

    /**
     * Method under test: {@link AdvertisementServiceImpl#update(Advertisement)}
     */
    @Test
    void testUpdate2() throws InvalidIdException {
        when(advertisementRepo.existsById(Mockito.<Integer>any())).thenReturn(false);

        Advertisement ad = new Advertisement();
        ad.setCategory(1);
        ad.setId(1);
        ad.setPrice(10.0d);
        ad.setProduct("Product");
        ad.setType(Type.BUY);
        ad.setUser(1);
        assertThrows(InvalidIdException.class, () -> advertisementServiceImpl.update(ad));
        verify(advertisementRepo).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdvertisementServiceImpl#get(int)}
     */
    @Test
    void testGet() throws InvalidIdException {
        Advertisement advertisement = new Advertisement();
        advertisement.setCategory(1);
        advertisement.setId(1);
        advertisement.setPrice(10.0d);
        advertisement.setProduct("Product");
        advertisement.setType(Type.BUY);
        advertisement.setUser(1);
        Optional<Advertisement> ofResult = Optional.of(advertisement);
        when(advertisementRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertSame(advertisement, advertisementServiceImpl.get(1));
        verify(advertisementRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdvertisementServiceImpl#get(int)}
     */
    @Test
    void testGet2() throws InvalidIdException {
        Optional<Advertisement> emptyResult = Optional.empty();
        when(advertisementRepo.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(InvalidIdException.class, () -> advertisementServiceImpl.get(1));
        verify(advertisementRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdvertisementServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<Advertisement> advertisementList = new ArrayList<>();
        when(advertisementRepo.findAll()).thenReturn(advertisementList);
        List<Advertisement> actualAll = advertisementServiceImpl.getAll();
        assertSame(advertisementList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(advertisementRepo).findAll();
    }

    /**
     * Method under test: {@link AdvertisementServiceImpl#getByCategory(int)}
     */
    @Test
    void testGetByCategory() throws NoDataException {
        ArrayList<Advertisement> advertisementList = new ArrayList<>();
        Optional<List<Advertisement>> ofResult = Optional.of(advertisementList);
        when(advertisementRepo.findByCategory(anyInt())).thenReturn(ofResult);
        List<Advertisement> actualByCategory = advertisementServiceImpl.getByCategory(1);
        assertSame(advertisementList, actualByCategory);
        assertTrue(actualByCategory.isEmpty());
        verify(advertisementRepo).findByCategory(anyInt());
    }

    /**
     * Method under test: {@link AdvertisementServiceImpl#getByCategory(int)}
     */
    @Test
    void testGetByCategory2() throws NoDataException {
        Optional<List<Advertisement>> emptyResult = Optional.empty();
        when(advertisementRepo.findByCategory(anyInt())).thenReturn(emptyResult);
        assertThrows(NoDataException.class, () -> advertisementServiceImpl.getByCategory(1));
        verify(advertisementRepo).findByCategory(anyInt());
    }

    /**
     * Method under test: {@link AdvertisementServiceImpl#delete(int)}
     */
    @Test
    void testDelete() throws InvalidIdException {
        doNothing().when(advertisementRepo).deleteById(Mockito.<Integer>any());
        when(advertisementRepo.existsById(Mockito.<Integer>any())).thenReturn(true);
        advertisementServiceImpl.delete(1);
        verify(advertisementRepo).existsById(Mockito.<Integer>any());
        verify(advertisementRepo).deleteById(Mockito.<Integer>any());
        assertTrue(advertisementServiceImpl.getAll().isEmpty());
    }

    /**
     * Method under test: {@link AdvertisementServiceImpl#delete(int)}
     */
    @Test
    void testDelete2() throws InvalidIdException {
        when(advertisementRepo.existsById(Mockito.<Integer>any())).thenReturn(false);
        assertThrows(InvalidIdException.class, () -> advertisementServiceImpl.delete(1));
        verify(advertisementRepo).existsById(Mockito.<Integer>any());
    }
}

