package com.relatinship.manytomany.service;

import com.relatinship.manytomany.model.Developer;

import java.util.List;

public interface DeveloperService {

    public List<Developer> getDeveloper();

    public Developer getDeveloper(Long idDeveloper);

    public Developer createDeveloper(Developer developer);

    public Developer updateDeveloper(Long idDeveloper, Developer developer);

    public Developer deleteDeveloper(Long idDeveloper);
}
