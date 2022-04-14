package javatown.service;

import javatown.DTO.*;
import javatown.modele.*;
import javatown.repository.ClientRepository;
import javatown.repository.DebtRepository;
import javatown.repository.DocumentRepository;
import javatown.repository.LoanRepository;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientService extends AbstractCommunService{
    private DocumentRepository documentRepository;

    public ClientService(ClientRepository clientRepository, LoanRepository loanRepository, DebtRepository debtRepository, DocumentRepository documentRepository) {
        super(clientRepository, loanRepository, debtRepository);
        this.documentRepository = documentRepository;
    }

    public ClientFormDTO createClient(String firstName, String lastName, String password, String resident) {
        return createClient(new Client(firstName, lastName, password, resident));
    }

    public ClientFormDTO createClient(Client client) {
        clientRepository.save(client);
        return new ClientFormDTO(client);
    }

    public ClientFormDTO getClientById(String clientId) {
        long id = Long.parseLong(clientId);
        Client client = findCLientWithLoansAndDebts(id);
        return new ClientFormDTO(client);
    }

    public ClientFormDTO getClientByPassword(ClientFormDTO clientForm) {
        return getClientByPassword(clientForm.getFirstName(), clientForm.getLastName(), clientForm.getPassword());
    }

    public ClientFormDTO getClientByPassword(String firstName, String lastName, String password) {
        Client client = findCLientWithLoansAndDebts(firstName, lastName, password);
        return new ClientFormDTO(client);
    }

    public List<AbstractDocumentFormDTO> getDocumentsByAuthor(String author) {
        var documentsOpt = documentRepository.findAllByAuthor(author);
        return getDocumentsDTO(documentsOpt);
    }

    public List<AbstractDocumentFormDTO> getDocumentsByEditor(String editor) {
        var documentsOpt = documentRepository.findAllByEditor(editor);
        return getDocumentsDTO(documentsOpt);
    }

    public List<AbstractDocumentFormDTO> getDocumentsByYear(String year) {
        Year years = Year.parse(year);
        var documentsOpt = documentRepository.findAllByPublicationYear(years);
        return getDocumentsDTO(documentsOpt);
    }

    public List<AbstractDocumentFormDTO> getDocumentsByGenre(String genre) {
        var documentsOpt = documentRepository.findAllByGenre(genre);
        return getDocumentsDTO(documentsOpt);
    }

    public List<AbstractDocumentFormDTO> getDocumentsByTitle(String title) {
        var documentsOpt = documentRepository.findAllByTitle(title);
        return getDocumentsDTO(documentsOpt);
    }

    private List<AbstractDocumentFormDTO> getDocumentsDTO(Optional<List<AbstractDocument>> documentsOpt){
        List<AbstractDocument> documents = handleOptDocuments(documentsOpt);
        List<AbstractDocumentFormDTO> documentsDTO = new ArrayList<>();
        for (AbstractDocument document: documents) {
            if(document.getClass() == Book.class) documentsDTO.add(new BookFormDTO((Book) document));
            else if(document.getClass() == CD.class) documentsDTO.add(new CDFormDTO((CD) document));
            else if(document.getClass() == DVD.class) documentsDTO.add(new DVDFormDTO((DVD) document));
        }

        return documentsDTO;
    }

    private List<AbstractDocument> handleOptDocuments(Optional<List<AbstractDocument>> documentsOpt){
        if(documentsOpt.isEmpty()) return null;
        List<AbstractDocument> documents = documentsOpt.get();
        return documents;
    }
}
