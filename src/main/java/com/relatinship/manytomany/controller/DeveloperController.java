package com.relatinship.manytomany.controller;

import com.relatinship.manytomany.model.Developer;
import com.relatinship.manytomany.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @GetMapping("/developer")
    public ResponseEntity<List<Developer>> getAllDevelopers(){
        return new ResponseEntity<>(developerService.getDeveloper(), HttpStatus.OK);
    }

    @GetMapping("/developer/{idDeveloper}")
    public ResponseEntity<Developer> getADeveloper(@PathVariable Long idDeveloper){
        return new ResponseEntity<>(developerService.getDeveloper(idDeveloper), HttpStatus.OK);
    }

    @PostMapping("/developer")
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer){
        return new ResponseEntity<>(developerService.createDeveloper(developer), HttpStatus.CREATED);
    }

    @PutMapping("/developer/{idDeveloper}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Long idDeveloper, @RequestBody Developer developer){
        return new ResponseEntity<>(developerService.updateDeveloper(idDeveloper, developer), HttpStatus.OK);
    }

    @DeleteMapping("/developer/{idDeveloper}")
    public ResponseEntity<Developer> deleteDeveloper(@PathVariable Long idDeveloper){
        return new ResponseEntity<>(developerService.deleteDeveloper(idDeveloper), HttpStatus.OK);
    }
}
