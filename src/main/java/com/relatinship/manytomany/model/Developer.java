package com.relatinship.manytomany.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.relatinship.manytomany.model.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "projectList")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDeveloper;

    private String name;

    private String especialidad;

    @ManyToMany(mappedBy = "developerList", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Project> projectList;
}
