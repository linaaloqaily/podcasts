package com.example.podcasts.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
public class PlayList {

    @Id
    @NotNull(message = "Id is required")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is required")
    private String name;



    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Podcast> podcast;
}
