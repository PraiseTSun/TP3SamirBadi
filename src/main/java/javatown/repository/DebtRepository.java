package javatown.repository;

import javatown.modele.Client;
import javatown.modele.Debt;
import javatown.modele.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    @Query(value = "SELECT p FROM Debt p " +
            "WHERE FUNCTION('MONTH', p.dateOfDebt) = :month")
    Optional<List<Debt>> findAllByDateOfDebt(@Param("month") Integer month);

    boolean existsByClient_Id (Long clientId);
}
