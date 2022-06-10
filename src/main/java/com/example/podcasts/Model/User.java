package com.example.podcasts.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class User {
    @Id
    @NotNull(message = "Id is required")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username is required")
    @Size(min = 4,max = 12,message = "Username must be between 4-12 character ")
    private String username;

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
//    @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must be have uppercase and lowercase letters and symbols")
    private String password;

    @NotEmpty(message = "Role is required")
    @Pattern(regexp = "(admin|user)", message = "Role mus t be (admin or user)")
    private String role;


    @OneToMany(mappedBy = "user")
    private Set<Podcast> podcasts;

    @OneToMany(mappedBy = "user")
    private Set<PlayList> playLists;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;



}
