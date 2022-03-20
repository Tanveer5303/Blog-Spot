package com.tanveer.blog.springbootblogrestapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users" ,uniqueConstraints ={
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
                            })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)  //fetch type is eager because we need to fetch both parent(users) and child(roles) together if fetch type is lazy then only parent is retrived we have to retrive child separatly.
    @JoinTable(name = "user_roles", //table name
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roles;
}
