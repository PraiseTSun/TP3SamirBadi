package javatown.controllers;

import javatown.DTO.*;
import javatown.service.AdminService;
import javatown.service.ClientService;
import javatown.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/react")
public class ReactController {
    private AdminService adminService;
    private ClientService clientService;
    private EmployeeService employeService;

    public ReactController(AdminService adminService, ClientService clientService, EmployeeService employeeService) {
        this.adminService = adminService;
        this.clientService = clientService;
        this.employeService = employeService;
    }

    @GetMapping("/documents")
    public List<AbstractDocumentFormDTO> getDocuments(){
        return clientService.getDocuments();
    }
}
