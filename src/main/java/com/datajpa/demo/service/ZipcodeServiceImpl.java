package com.datajpa.demo.service;

import com.datajpa.demo.model.City;
import com.datajpa.demo.model.ZipCode;
import com.datajpa.demo.model.exception.ZipCodeIsAlreadyAssignedException;
import com.datajpa.demo.model.exception.ZipCodeNotFoundException;
import com.datajpa.demo.repository.CityRepository;
import com.datajpa.demo.repository.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {

    private final ZipCodeRepository zipCodeRepository;
    private final CityService cityService;

    @Autowired
    public ZipcodeServiceImpl(ZipCodeRepository zipCodeRepository, CityService cityService) {
        this.zipCodeRepository = zipCodeRepository;
        this.cityService = cityService;
    }

    @Override
    public ZipCode addZipCode(ZipCode zipCode) {
        return zipCodeRepository.save(zipCode);
    }

    @Override
    public List<ZipCode> getZipCodes() {
        return  StreamSupport
                .stream(zipCodeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public ZipCode getZipCode(Long id) {
        return zipCodeRepository.findById(id).orElseThrow(() ->
                new ZipCodeNotFoundException(id));
    }

    @Override
    public ZipCode deleteZipCode(Long id) {
        ZipCode zipCode = getZipCode(id);
        zipCodeRepository.delete(zipCode);
        return zipCode;
    }

    @Transactional
    @Override
    public ZipCode editZipCode(Long id, ZipCode zipCode) {
        ZipCode zipCodeToEdit = getZipCode(id);
        zipCodeToEdit.setCode(zipCode.getCode());
        zipCodeToEdit.setCity(zipCode.getCity());
        return zipCodeToEdit;
    }

    @Transactional
    @Override
    public ZipCode addCityToZipCode(Long zipCodeId, Long cityId) {
        ZipCode zipCode = getZipCode(zipCodeId);
        City city = cityService.getCity(cityId);
        if(Objects.nonNull(zipCode.getCity())){
            throw new ZipCodeIsAlreadyAssignedException(zipCodeId,
                    zipCode.getCity().getId());
        }
        zipCode.setCity(city);
        return zipCode;
    }

    @Transactional
    @Override
    public ZipCode removeCityFromZipCode(Long zipCodeId) {
        ZipCode zipCode = getZipCode(zipCodeId);
        zipCode.setCity(null);
        return zipCode;
    }
}
