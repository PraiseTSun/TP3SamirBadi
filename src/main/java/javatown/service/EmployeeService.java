package javatown.service;

import javatown.DTO.*;
import javatown.modele.*;
import javatown.repository.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
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

    public LoanFormDTO createLoan(String clientId, String documentId){
        return createLoan(clientId, documentId, getDate());
    }

    private String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().format(formatter);
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

    public DebtFormDTO returnDocument(String clientId, String loanId, String dateOfReturn) {
        long clientIndex = Long.parseLong(clientId);
        long loanIndex = Long.parseLong(loanId);
        Debt debt = null;
        Client client = findClientByIdWithLoans(clientIndex);
        Loan loan = client.getLoanOfId(loanIndex);

        if(loan == null) return null;
        if(loan.isLate(dateOfReturn)) debt = createDebt(loan, dateOfReturn);

        loanRepository.delete(loan);

        if(debt != null) {
            debtRepository.save(debt);
            return new DebtFormDTO(debt);
        }

        return null;
    }

    private Debt createDebt(Loan loan, String dateOfReturn){
        return new Debt(loan.getClient(), dateOfReturn, loan.nbDaysLate(dateOfReturn));
    }

    public DebtFormDTO payeDebt(String clientId, String debtId) {
        long clientIndex = Long.parseLong(clientId);
        long debtIndex = Long.parseLong(debtId);

        Client client = findClientByIdWithDebts(clientIndex);
        if(client == null) return null;
        Debt debt = client.getDebtById(debtIndex);
        if(debt == null) return null;
        debtRepository.delete(debt);

        return new DebtFormDTO(debt);
    }

    public void addExemplaires(String documentId, int nb) {
        long id = Long.parseLong(documentId);
        var documentOpt = documentRepository.findByIdWithLoans(id);
        if(documentOpt.isEmpty())
            return;
        AbstractDocument document = documentOpt.get();
        document.addExempliares(nb);
        documentRepository.save(document);
    }
}
