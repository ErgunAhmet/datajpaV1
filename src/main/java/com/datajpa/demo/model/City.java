package com.datajpa.demo.model;

import com.datajpa.demo.model.dto.request.CityDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "City")
public class City{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public City(String name) {
        this.name = name;
    }

    public static City from(CityDto cityDto) {
        City city = new City();
        city.setName(cityDto.getName());
        return city;
    }
}
