package com.datajpa.demo.model.dto;

import com.datajpa.demo.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class ZipCodeDto {
    private String code;
    private Long cityId;

    public ZipCodeDto(String code) {
        this.code = code;
    }
}
