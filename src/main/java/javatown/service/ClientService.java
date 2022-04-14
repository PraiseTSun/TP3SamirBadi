package javatown.service;

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
}
