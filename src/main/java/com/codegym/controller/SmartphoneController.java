package com.codegym.controller;

import com.codegym.model.Smartphone;
import com.codegym.service.ISmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/smartphones")
@CrossOrigin("*")
public class SmartphoneController {
    @Autowired
    private ISmartphoneService smartphoneService;

    @PostMapping
    public ResponseEntity<Smartphone> createSmartphone(@RequestBody Smartphone smartphone) {
        return new ResponseEntity<>(smartphoneService.save(smartphone), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ModelAndView getAllSmartphonePage() {
        ModelAndView modelAndView = new ModelAndView("/phones/list");
        modelAndView.addObject("smartphones", smartphoneService.findAll());
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<Iterable<Smartphone>> allPhones() {
        return new ResponseEntity<>(smartphoneService.findAll(), HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<Smartphone> deleteSmartphone(@RequestBody Long id) {
        smartphoneService.remove(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Smartphone> phones(@RequestBody Long id) {
        Smartphone smartphone = smartphoneService.findById(id).get();
        return new ResponseEntity<>(smartphone, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Smartphone> update(@RequestBody Smartphone smartphone){
        smartphoneService.save(smartphone);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}