package com.datajpa.demo.model.dto;

import com.datajpa.demo.model.City;
import lombok.Data;

import java.util.Objects;

@Data
public class ZipCodeDto {
    private String code;
    private City city;

    public ZipCodeDto(String code, City city) {
        this.code = code;
        if (Objects.nonNull(city)) {
            this.city =  city;
        }else {
            this.city = null;
        }
    }
}
