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
        
    }
}
