package com.examen.repository;

import com.examen.model.Employees;
import com.examen.model.Jbs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employees, Long> {
    @Query(value = "SELECT * FROM PRUEBAS.EMPLOYEES e WHERE e.NAME =:nombre AND e.LAST_NAME =:apellido ",nativeQuery = true)
    Employees getSearchForNameLastName(@Param("nombre") String name, @Param("apellido") String last_name);

    @Query(value = "SELECT * FROM PRUEBAS.EMPLOYEES e WHERE e.ID =:id",nativeQuery = true)
    Employees getSearchById(@Param("id") Long id);

    @Query("SELECT a FROM Employees a WHERE a.jobId = :job_id")
    List<Employees> findByPuesto(@Param("job_id") Jbs job_id);
}
