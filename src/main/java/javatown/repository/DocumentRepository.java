package javatown.repository;

import javatown.modele.AbstractDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<AbstractDocument, Long> {

}
