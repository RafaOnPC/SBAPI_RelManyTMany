package com.relatinship.manytomany.controller;

import com.relatinship.manytomany.model.Project;
import com.relatinship.manytomany.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projecto")
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity<>(projectService.getProjects(), HttpStatus.OK);
    }

    @GetMapping("/projecto/{projectId}")
    public ResponseEntity<Object> getAProject(@PathVariable Long projectId){
        return new ResponseEntity<>(projectService.getProject(projectId), HttpStatus.OK);
    }

    @PostMapping("/projecto")
    public ResponseEntity<Object> createProject(@RequestBody Project project){
        return new ResponseEntity<>(projectService.createProject(project), HttpStatus.OK);
    }

    @PutMapping("/projecto/{projectId}")
    public ResponseEntity<Object> updateProject(@PathVariable Long projectId, @RequestBody Project project){
        return new ResponseEntity<>(projectService.updateProject(projectId, project), HttpStatus.OK);
    }

    @DeleteMapping("/projecto/{projectId}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long projectId){
        return new ResponseEntity<>(projectService.deleteProject(projectId), HttpStatus.OK);
    }
}
