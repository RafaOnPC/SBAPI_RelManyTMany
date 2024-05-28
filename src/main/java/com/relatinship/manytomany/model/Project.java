package com.relatinship.manytomany.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
//@ToString(exclude = "developerList")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProject;

    private String name;

    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_developer",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    //@JsonIgnore - Ignora el elemento del json
    //@JsonBackReference
    private List<Developer> developerList;

}
