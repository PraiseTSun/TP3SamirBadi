package javatown;

import javatown.DTO.ClientFormDTO;
import javatown.service.AdminService;
import javatown.service.ClientService;
import javatown.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp3SamirBadiApplication implements CommandLineRunner {

    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeService employeService;
    @Autowired
    private AdminService adminService;

    public static void main(String[] args) {
        SpringApplication.run(Tp3SamirBadiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var client1 = clientService.createClient("Samir", "Badi", "PraiseTSun", "Javatown");
        var client2 = clientService.createClient("Hicham", "Badi", "fox", "Javatown");
        var employe1 = employeService.createEmploye("Dave", "Smith", "test");
        var admin1 = adminService.createAdmin("James", "Dalton", "admin");
        var book1 = employeService.createBook("Atlas", "House", "Jhone Smith", "2001", 256, "GEO");
        employeService.addExemplaires(book1.getId(), 1);
        var CD1 = employeService.createCD("Viking", "Amon Amarth", "Amon Amarth", "2015", "Heavy Metal");
        var DVD1 = employeService.createDVD("Avenger Test", "Steven Lee", "Marvel", "2017", "Action");
        var DVD2 = employeService.createDVD("Avenger The end game", "Steven Lee", "Marvel", "2019", "Action");


        System.out.println(client1);
        System.out.println(employe1);
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
        System.out.println(clientService.getDocumentsByTitle("iki"));

        var loan1 = employeService.createLoan(client1.getId(), book1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        var loan5 = employeService.createLoan(client1.getId(), book1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        var loan6 = employeService.createLoan(client1.getId(), book1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        var loan2 = employeService.createLoan(client1.getId(), DVD1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        var loan3 = employeService.createLoan(client1.getId(), DVD2.getId(), "2021-02-16");
        System.out.println(clientService.getClientById(client1.getId()));
        var debt1 = employeService.returnDocument(client1.getId(), loan2.getId());
        System.out.println(clientService.getClientById(client1.getId()));
        employeService.returnDocument(client1.getId(), loan1.getId());
        System.out.println(clientService.getClientById(client1.getId()));
        employeService.createLoan(client1.getId(), CD1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        //employeService.payeDebt(client1.getId(), debt1.getId());
        System.out.println(clientService.getClientById(client1.getId()));
        employeService.createLoan(client1.getId(), CD1.getId(), "2021-02-10");
        System.out.println(clientService.getClientById(client1.getId()));
        System.out.println(adminService.getLoansOfMonth(2));
        System.out.println(adminService.getDebtsOfMonth(2));
        System.out.println(clientService.getClientByPassword("Samir", "Badi", "PraiseTSun"));
        System.out.println(clientService.getClientByPassword("Samir", "Badi", "PraiseTSun"));
        System.out.println(employeService.getClients());
    }
}
