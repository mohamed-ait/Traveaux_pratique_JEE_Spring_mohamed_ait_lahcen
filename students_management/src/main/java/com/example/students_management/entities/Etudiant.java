package com.example.students_management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "saisi le nom !")
    @Size(min = 3 ,max = 35 , message = "la longueur du nom doit etre entre 3 et 35")
    private String nom;
    @NotBlank(message = "saisi le prenom !")
    @Size(min = 3 ,max = 35 , message = "la longueur du prenom doit etre entre 3 et 35")
    private String prenom;
    @Email(message = "email invalid!")
    @NotNull(message = "saisi une adresse mail!")
    private String email;
    @NotNull(message = "La date est obligatoire!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @NotNull(message = "choisi le genre !")
    private Genre genre;
    private boolean regle;

}
