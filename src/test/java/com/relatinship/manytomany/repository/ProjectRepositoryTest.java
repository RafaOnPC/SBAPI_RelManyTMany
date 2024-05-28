package com.relatinship.manytomany.repository;

import com.relatinship.manytomany.model.Developer;
import com.relatinship.manytomany.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void getAlldevelopers(){
        Project project = projectRepository.findById(1L).get();
        List<Developer> developerListBd = project.getDeveloperList();
        System.out.println("developerListBd = " + developerListBd);
    }
}