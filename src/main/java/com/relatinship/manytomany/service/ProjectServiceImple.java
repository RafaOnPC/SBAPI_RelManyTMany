package com.relatinship.manytomany.service;

import com.relatinship.manytomany.model.Developer;
import com.relatinship.manytomany.model.Project;
import com.relatinship.manytomany.repository.DeveloperRepository;
import com.relatinship.manytomany.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImple implements ProjectService{

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getProjects() {
        //Funcionalidad: Obtiene todos los projectos de la BD
        return projectRepository.findAll();
    }

    @Override
    public Project getProject(Long projectId) {
        //Funcionalidad: Obtiene el projecto solicitado
        return projectRepository.findById(projectId).get();
    }

    @Override
    public Project createProject(Project project) {
        //Funcionalidad: Crea un projecto y crea un nuevo developer asociandolo
        Project py = new Project();
        py.setIdProject(project.getIdProject());
        py.setName(project.getName());
        py.setDescription(project.getDescription());
        py.setDeveloperList(null);
        //Persistir proyecto
        projectRepository.save(py);
        //Crear un developer
        List<Developer> developerList = new ArrayList<>();
        for(Developer dev: project.getDeveloperList()){
            developerList.add(developerRepository.save(dev));
        }
        //Agregar la lista de developers a un proyecto
        py.setDeveloperList(developerList);
        Project pj = projectRepository.save(py);

        return pj;
    }

    @Override
    public Project updateProject(Long projectId, Project project) {
        //Actualiza los datos del proyecto como la lista de developers
        //Pasar: todo el registro de developer con id incluido, tambien del nuevo developer
        //Buscar un proyecto
        Project py = projectRepository.findById(projectId).get();
        //Setear valores del proyecto
        py.setIdProject(project.getIdProject());
        py.setName(project.getName());
        py.setDescription(project.getDescription());

        //Actualizar la lista de developers
        List<Developer> developerList = project.getDeveloperList();
        for(Developer dev: developerList){
            developerRepository.save(dev);
        }

        //Actualizar lista en el registro proyecto
        py.setDeveloperList(developerList);

        //Persistir proyecto
        Project pj = projectRepository.save(py);

        return pj;
    }

    @Override
    public Project deleteProject(Long projectId) {
        //Funcionalidad: Elimina el projecto, a pesar de estar asociado con developer, mas no elimina a los developer
        Project pj = projectRepository.findById(projectId).get();
        projectRepository.deleteById(projectId);
        return pj;
    }
}
