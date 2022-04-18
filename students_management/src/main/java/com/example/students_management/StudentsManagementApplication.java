package com.example.students_management;

import com.example.students_management.entities.Etudiant;
import com.example.students_management.entities.Genre;
import com.example.students_management.repositories.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class StudentsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsManagementApplication.class, args);
	}
	@Bean
  CommandLineRunner	start(EtudiantRepository etudiantRepository){
		return args->{
			etudiantRepository.save(new Etudiant(null,"nom1","prenom1","prenom1nom1@gmail.com",new Date(), Genre.MASCULIN,true));
			etudiantRepository.save(new Etudiant(null,"nom2","prenom2","prenom2nom2@gmail.com",new Date(), Genre.FEMININ,true));
			etudiantRepository.save(new Etudiant(null,"nom3","prenom3","prenom3nom3@gmail.com",new Date(), Genre.MASCULIN,false));
			etudiantRepository.save(new Etudiant(null,"nom4","prenom4","prenom4nom4@gmail.com",new Date(), Genre.FEMININ,true));

		};
  }
}
