package com.datajpa.demo.service;

import com.datajpa.demo.model.City;
import com.datajpa.demo.model.exception.CityNotFoundException;
import com.datajpa.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> getCities() {
        return  StreamSupport
                .stream(cityRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public City getCity(Long id) {
        return cityRepository.findById(id).orElseThrow(() ->
                new CityNotFoundException(id));
    }

    @Override
    public City deleteCity(Long id) {
        City city = getCity(id);
        cityRepository.delete(city);
        return city;
    }

    @Transactional
    @Override
    public City editCity(Long id, City city) {
        City cityToEdit = getCity(id);
        cityToEdit.setName(city.getName());
        return cityToEdit;
    }
}
