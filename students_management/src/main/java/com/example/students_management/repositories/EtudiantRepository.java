package com.example.students_management.repositories;

import com.example.students_management.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    Etudiant findByNom(String nom);
    Page<Etudiant> findByNomContains(String kw, Pageable pageable);
}
