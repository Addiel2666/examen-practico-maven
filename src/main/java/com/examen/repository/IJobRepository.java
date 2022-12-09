package com.examen.repository;

import com.examen.model.Jbs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJobRepository extends JpaRepository<Jbs, Long> {
    @Query(value = "SELECT * FROM PRUEBAS.JBS WHERE ID=:jobId ", nativeQuery = true)
    Optional<Jbs> buscaId(@Param("jobId") Long job_id);
}
