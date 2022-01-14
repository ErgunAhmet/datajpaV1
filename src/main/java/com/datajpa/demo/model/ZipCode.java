package com.datajpa.demo.model;

import com.datajpa.demo.model.dto.ZipCodeDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ZipCode")
public class ZipCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    public ZipCode(String code, City city) {
        this.code = code;
        this.city = city;
    }

    public static ZipCode from(ZipCodeDto zipCodeDto) {
        ZipCode zipCode = new ZipCode();
        zipCode.setCode(zipCodeDto.getCode());

        return zipCode;
    }
}
