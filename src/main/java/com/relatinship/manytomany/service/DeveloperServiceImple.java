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
public class DeveloperServiceImple implements DeveloperService{

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Developer> getDeveloper() {
        //Obtiene todos los developers de la BD
        return developerRepository.findAll();
    }

    @Override
    public Developer getDeveloper(Long idDeveloper) {
        //Obtiene el developer solicitado
        return developerRepository.findById(idDeveloper).get();
    }

    @Override
    public Developer createDeveloper(Developer developer) {
        //Funcionalidad: Crea un registro de developer & asocia a un proyecto existente
        /*System.out.println("developer.getProjectList() = " + developer.getProjectList());
        System.exit(0);*/
        /*
        * 1. Setear datos de developer a dev
        * 2. Persistir developer
        * 3. Recuperar projecto
        * 4. Setear relacion de developer a projecto
        * 5. Persistir projecto
        * */

        Developer dvi = developerRepository.save(developer);

        List<Long> idProjects = new ArrayList<>(developer.getProjectList().size());
        for(Project pj: developer.getProjectList()){
            idProjects.add(pj.getIdProject());
        }

        //Buscar proyectos por id y asignar el proyecto a un array de projectos
        List<Project> projectList = new ArrayList<>(idProjects.size());
        for(Long id: idProjects){
            projectList.add(projectRepository.findById(id).get());
        }

//        System.out.println("projectList = " + projectList);
//        System.exit(0);
        //Recuperar la lista de developers asignados a ese proyecto
        //Crear una lista con los elementos de la lista de developers recuperado y agregar al nuevo developer creado
        //Asignar lista al projecto
        //Persistir projecto
        List<Developer> developerListBD = new ArrayList<>();
        for(Project pjs: projectList){
            developerListBD = pjs.getDeveloperList();
        }

//        System.out.println("developerList = " + developerListBD);
//        System.exit(0);

        developerListBD.add(dvi);

        for(Project pjs: projectList){
            pjs.setDeveloperList(developerListBD);
            projectRepository.save(pjs);
        }

        return dvi;
    }

    @Override
    public Developer updateDeveloper(Long idDeveloper, Developer developer) {
        //Funcionalidad: Asignar developer a un proyecto existente
        //1.Setear valores al developer
        //2.Persistir developer
        //3.Buscar projecto por id
        //4.Recuperado el projecto, obtener la lista de developers
        //5.Agregar a la lista de developers, el nuevo developer
        //6.Persistir projecto
       Developer dev = developerRepository.findById(idDeveloper).get();
       dev.setIdDeveloper(developer.getIdDeveloper());
       dev.setName(developer.getName());
       dev.setEspecialidad(developer.getEspecialidad());
       Developer dvi = developerRepository.save(dev);

       List<Project> projectListPost = developer.getProjectList();

       List<Long> ids = new ArrayList<>(projectListPost.size());
       for(Project pj: projectListPost){
           ids.add(pj.getIdProject());
       }

       // System.out.println("ids = " + ids);
       
       List<Project> projectArrayList = new ArrayList<>();
       for(Long id: ids){
           projectArrayList.add(projectRepository.findById(id).get());
       }

       List<Developer> developerList = new ArrayList<>();
       for(Project pjs: projectArrayList){
           developerList = pjs.getDeveloperList();
       }

       developerList.add(dvi);

        for(Project pjs: projectArrayList){
            pjs.setDeveloperList(developerList);
            projectRepository.save(pjs);
        }

       return dvi;
    }

    @Override
    public Developer deleteDeveloper(Long idDeveloper) {
        //Elimina un developer que no este asociado a ningun projecto
        Developer dev = developerRepository.findById(idDeveloper).get();
        developerRepository.deleteById(idDeveloper);
        return dev;

        //Si se deseara eliminar un developer asociado a un projecto
        //1.Recuperar el projeto al que pertence el developer
        //2.Recuperar la lista de developers del projecto
        //3.Remover de la lista al developer a eliminar buscandolo por ID
        //4.Guardar cambios del proyecto
        //5.Eliminacion del developer solicitado.
    }
}
