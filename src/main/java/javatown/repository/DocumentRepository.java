package javatown.repository;

import javatown.modele.AbstractDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<AbstractDocument, Long> {
    @Query(value = "SELECT p FROM AbstractDocument p " +
            "WHERE p.title LIKE %:title%")
    Optional<List<AbstractDocument>> findAllByTitle(@Param("title") String title);
    Optional<List<AbstractDocument>> findAllByAuthor(String author);
    Optional<List<AbstractDocument>> findAllByEditor(String editor);
    Optional<List<AbstractDocument>> findAllByPublicationYear(Year year);
    Optional<List<AbstractDocument>> findAllByGenre(String genre);
    @Query(value = "SELECT p FROM AbstractDocument p " +
            "LEFT JOIN FETCH p.loans " +
            "WHERE p.id = :documentId")
    Optional<AbstractDocument> findByIdWithLoans(@Param("documentId") Long documentId);

}
