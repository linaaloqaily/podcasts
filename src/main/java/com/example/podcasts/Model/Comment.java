package com.example.podcasts.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
public class Comment {

    @Id
    @NotNull(message = "Id is required")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

    @Range(min = 1, max = 5, message = "Rate must be between 1-5")
    private Integer rate;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    private Podcast podcast;
}
