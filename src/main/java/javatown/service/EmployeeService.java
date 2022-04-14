package javatown.service;

import javatown.DTO.EmployeeFormDTO;
import javatown.modele.Employee;
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
}
