package javatown.repository;

import javatown.modele.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface LoanRepository extends JpaRepository<Loan, Long> {

}
