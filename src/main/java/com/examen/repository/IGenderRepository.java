package com.examen.repository;

import com.examen.model.Genders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGenderRepository extends JpaRepository<Genders, Long> {
    @Query(value = "SELECT * FROM PRUEBAS.GENDERS WHERE ID =:genderId", nativeQuery = true)
    Optional<Genders> buscaId(@Param("genderId") Long gender_id);
}
