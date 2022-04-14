package javatown.service;

import javatown.DTO.ClientFormDTO;
import javatown.modele.Client;
import javatown.repository.ClientRepository;
import javatown.repository.DebtRepository;
import javatown.repository.DocumentRepository;
import javatown.repository.LoanRepository;
import org.springframework.stereotype.Component;

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
}
