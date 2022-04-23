package com.example.students_management.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {
    @Id
    private String userId;
    @Column(unique = true, length =20 )
    private String userName;
    private String password;
    private boolean active;
    @ManyToMany( fetch = FetchType.EAGER)
    private List<AppRole> roles=new ArrayList<>();
}