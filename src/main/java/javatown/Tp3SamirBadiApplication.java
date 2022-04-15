package javatown;

import javatown.service.AdminService;
import javatown.service.ClientService;
import javatown.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp3SamirBadiApplication implements CommandLineRunner {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(Tp3SamirBadiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var client1 = clientService.createClient("Samir", "Badi", "PraiseTSun", "Javatown");
        var employee1 = employeeService.createEmployee("Dave", "Smith", "test");
        var admin1 = adminService.createAdmin("James", "Dalton", "admin");
        var book1 = employeeService.createBook("Atlas", "House", "Jhone Smith", "2001", 256, "GEO");
        var CD1 = employeeService.createCD("Viking", "Amon Amarth", "Amon Amarth", "2015", "Heavy Metal");
        var DVD1 = employeeService.createDVD("Avenger Test", "Steven Lee", "Marvel", "2017", "Action");
        var DVD2 = employeeService.createDVD("Avenger The end game", "Steven Lee", "Marvel", "2019", "Action");
        employeeService.addExemplaires(book1.getId(), 1);

        System.out.println(client1);
        System.out.println(employee1);
        System.out.println(admin1);
        System.out.println(book1);
        System.out.println(CD1);
        System.out.println(DVD1);
        System.out.println(DVD2);
        System.out.println(clientService.getClientById(client1.getId()));
        System.out.println(clientService.getDocumentsByAuthor("Steven Lee"));
        System.out.println(clientService.getDocumentsByEditor("Amon Amarth"));
        System.out.println(clientService.getDocumentsByYear("2001"));
        System.out.println(clientService.getDocumentsByGenre("Action"));
        System.out.println(clientService.getDocumentsByTitle("eng"));

        var loan1 = employeeService.createLoan(client1.getId(), book1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        var loan5 = employeeService.createLoan(client1.getId(), book1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        var loan6 = employeeService.createLoan(client1.getId(), book1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        var loan2 = employeeService.createLoan(client1.getId(), DVD1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        var loan3 = employeeService.createLoan(client1.getId(), DVD2.getId(), "2021-02-16");
        System.out.println(clientService.getClientById(client1.getId()));
        var debt1 = employeeService.returnDocument(client1.getId(), loan2.getId(), "2021-02-28");
        System.out.println(clientService.getClientById(client1.getId()));
        employeeService.returnDocument(client1.getId(), loan1.getId(), "2021-02-18");
        System.out.println(clientService.getClientById(client1.getId()));
        employeeService.createLoan(client1.getId(), CD1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        employeeService.payeDebt(client1.getId(), debt1.getId());
        System.out.println(clientService.getClientById(client1.getId()));
        employeeService.createLoan(client1.getId(), CD1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        System.out.println(adminService.getLoansOfMonth(2));

    }
}
