package javatown.repository;

import javatown.modele.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>{
    @Query(value = "SELECT p FROM Client p " +
            "LEFT JOIN FETCH p.loans " +
            "WHERE p.id = :clientId")
    Optional<Client> findByIdWithLoans(@Param("clientId") Long clientId);
    @Query(value = "SELECT p FROM Client p " +
            "LEFT JOIN FETCH p.loans " +
            "WHERE p.firstName = :firstName AND p.lastName = :lastName AND p.password = :password")
    Optional<Client> findByIdWithLoans(@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("password") String password);

}
