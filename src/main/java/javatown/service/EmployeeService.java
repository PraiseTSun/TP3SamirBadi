package javatown.service;

import javatown.DTO.*;
import javatown.modele.*;
import javatown.repository.*;

public class EmployeeService extends AbstractCommunService{
    private EmployeRepository employeRepository;
    private DocumentRepository documentRepository;

    public EmployeeService(ClientRepository clientRepository, LoanRepository loanRepository, DebtRepository debtRepository, EmployeRepository employeRepository, DocumentRepository documentRepository) {
        super(clientRepository, loanRepository, debtRepository);
        this.employeRepository = employeRepository;
        this.documentRepository = documentRepository;
    }

    public EmployeeFormDTO createEmployee(String firstName, String lastName, String password) {
        return createEmployee(new Employee(firstName, lastName, password));
    }

    public EmployeeFormDTO createEmployee(Employee employee){
        employeRepository.save(employee);
        return new EmployeeFormDTO(employee);
    }

    public BookFormDTO createBook(String title, String author, String editor, String publicationYear, int pages, String genre) {
        return createBook(new Book(title, author, editor, publicationYear, pages, genre));
    }

    public BookFormDTO createBook(Book book) {
        documentRepository.save(book);
        return new BookFormDTO(book);
    }

    public CDFormDTO createCD(String title, String author, String editor, String publicationYear, String genre) {
        return createCD(new CD(title, author, editor, publicationYear, genre));
    }

    public CDFormDTO createCD(CD cd) {
        documentRepository.save(cd);
        return new CDFormDTO(cd);
    }

    public DVDFormDTO createDVD(String title, String author, String editor, String publicationYear, String genre) {
        return createDVD(new DVD(title, author, editor, publicationYear, genre));
    }

    public DVDFormDTO createDVD(DVD dvd) {
        documentRepository.save(dvd);
        return new DVDFormDTO(dvd);
    }

    public LoanFormDTO createLoan(CreateLoanFormDTO dto) {
        return createLoan(dto.getClientId(), dto.getDocumentId(), dto.getDateOfLoan());
    }
    public LoanFormDTO createLoan(String clientId, String documentId, String dateOfLoan) {
        long documentIndex = Long.parseLong(documentId);
        long clientIndex = Long.parseLong(clientId);
        var documentOpt = documentRepository.findByIdWithLoans(documentIndex);
        Client client = findClientByIdWithLoans(clientIndex);
        if(documentOpt.isEmpty() || client == null)
            return null;

        var document = documentOpt.get();

        if(!debtRepository.existsByClient_Id(clientIndex) && document.canBeLoan()){
            Loan loan = saveLoan(client, document, dateOfLoan);
            return new LoanFormDTO(loan);
        }
        return null;
    }

    private Loan saveLoan(Client client, AbstractDocument document, String dateOfLoan){
        Loan loan = new Loan(client, document, dateOfLoan);
        client.addLoan(loan);

        loanRepository.save(loan);
        clientRepository.save(client);

        return loan;
    }
}
