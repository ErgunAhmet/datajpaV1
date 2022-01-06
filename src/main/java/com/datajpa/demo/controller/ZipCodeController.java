package com.datajpa.demo.controller;
import com.datajpa.demo.model.ZipCode;
import com.datajpa.demo.model.dto.ZipCodeDto;
import com.datajpa.demo.service.ZipcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/zipcode")
public class ZipCodeController {

    private final ZipcodeService zipcodeService;

    @Autowired
    public ZipCodeController(ZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<ZipCode> addZipCode(@RequestBody final ZipCodeDto zipCodeDto) {
        ZipCode zipCode = zipcodeService.addZipCode(ZipCode.from(zipCodeDto));
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ZipCode> getZipCode(@PathVariable final Long id) {
        ZipCode zipCode = zipcodeService.getZipCode(id);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ZipCode>> getZipCodes() {
        List<ZipCode> zipCodes = zipcodeService.getZipCodes();
        return new ResponseEntity<>(zipCodes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ZipCode> deleteZipCode(@PathVariable final Long id) {
        ZipCode zipCode = zipcodeService.getZipCode(id);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ZipCode> editZipCode(@PathVariable final Long id,
                                               @RequestBody final ZipCodeDto zipCodeDto) {
        ZipCode zipCode = zipcodeService.editZipCode(id, ZipCode.from(zipCodeDto));
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @PostMapping("/addCity/{cityId}/toZipcode/{zipCodeId}")
    public ResponseEntity<ZipCode> addCity(@PathVariable final Long cityId,
                                           @PathVariable final Long zipCodeId) {
        ZipCode zipCode = zipcodeService.addCityToZipCode(zipCodeId, cityId);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @PostMapping("/removeCity/{zipCodeId}")
    public ResponseEntity<ZipCode> removeCity(@PathVariable final Long zipCodeId) {
        ZipCode zipCode = zipcodeService.removeCityFromZipCode(zipCodeId);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }
}
