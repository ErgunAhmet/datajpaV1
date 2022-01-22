package com.datajpa.demo.model.dto.request;

import com.datajpa.demo.model.City;
import lombok.Data;

@Data
public class CityDto {
    private String name;

    public static CityDto from(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setName(city.getName());
        return cityDto;
    }
}
