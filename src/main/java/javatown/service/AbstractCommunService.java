package javatown.service;

import javatown.modele.Client;
import javatown.repository.ClientRepository;
import javatown.repository.DebtRepository;
import javatown.repository.LoanRepository;

public class AbstractCommunService {
    protected ClientRepository clientRepository;
    protected LoanRepository loanRepository;
    protected DebtRepository debtRepository;

    public AbstractCommunService(ClientRepository clientRepository, LoanRepository loanRepository, DebtRepository debtRepository) {
        this.clientRepository = clientRepository;
        this.loanRepository = loanRepository;
        this.debtRepository = debtRepository;
    }

    protected Client findClientByIdWithLoans(long clientId){
        var clientOpt = clientRepository.findByIdWithLoans(clientId);
        if(clientOpt.isEmpty())
            return null;
        return clientOpt.get();
    }


    protected Client findClientByPasswordWithLoans(String firstName, String lastName, String password){
        var clientOpt = clientRepository.findByIdWithLoans(firstName, lastName, password);
        if(clientOpt.isEmpty())
            return null;
        return clientOpt.get();
    }

    protected Client findClientByIdWithDebts(long clientId){
        var clientOpt = clientRepository.findByIdWithDebts(clientId);
        if(clientOpt.isEmpty())
            return null;
        return clientOpt.get();
    }
}
