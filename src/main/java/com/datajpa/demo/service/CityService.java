package com.datajpa.demo.service;

import com.datajpa.demo.model.City;
import com.datajpa.demo.model.dto.CityDto;
import com.datajpa.demo.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {

    public City addCity(CityDto cityDto);
    public List<City> getCities();
    public City getCity(Long id);
    public City deleteCity(Long id);
    public City editCity(Long id, CityDto cityDto);
}
