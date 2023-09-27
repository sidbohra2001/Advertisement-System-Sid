package com.adservice.Advertisement.repo;

import com.adservice.Advertisement.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepo extends JpaRepository<Advertisement, Integer> {
    Optional<List<Advertisement>> findByCategory(int category);
}
