package com.example.podcasts.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Podcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotEmpty(message = "Type is required")
    private String type;

    private String link;


    @ManyToOne
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "podcast")
    @JsonIgnore
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "podcast", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PlayList> playLists;

}
