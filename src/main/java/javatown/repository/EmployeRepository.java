package javatown.repository;

import javatown.modele.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employee, Long> {
}
