package com.datajpa.demo.service;

import com.datajpa.demo.model.City;
import com.datajpa.demo.model.dto.CityDto;
import com.datajpa.demo.model.exception.CityNotFoundException;
import com.datajpa.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public City addCity(CityDto cityDto) {
        City city = new City();
        city.setName(cityDto.getName());
        return cityRepository.save(city);

    }

    @Override
    public List<City> getCities() {
        List<City> list = new ArrayList<>();
        cityRepository.findAll().forEach(list::add);
        return list;
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
    public City editCity(Long id, CityDto cityDto) {
        City cityToEdit = getCity(id);
        cityToEdit.setName(cityDto.getName());
        return cityToEdit;
    }
}
