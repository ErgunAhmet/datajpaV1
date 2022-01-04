package com.datajpa.demo.service;

import com.datajpa.demo.model.City;
import com.datajpa.demo.model.ZipCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZipcodeService {
    public ZipCode addZipCode(ZipCode zipCode);
    public List<ZipCode> getZipCodes();
    public ZipCode getZipCode(Long id);
    public ZipCode deleteZipCode(Long id);
    public ZipCode editZipCode(Long id, ZipCode zipCode);
    public ZipCode addCityToZipCode(Long zipCodeId, Long cityId);
    public ZipCode removeCityFromZipCode(Long zipCodeId);
}
