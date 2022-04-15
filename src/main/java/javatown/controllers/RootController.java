package javatown.controllers;

import javatown.service.AdminService;
import javatown.service.ClientService;
import javatown.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class RootController {
    Logger logger = LoggerFactory.getLogger(RootController.class);

    private ClientService clientService;
    private EmployeeService employeeService;
    private AdminService adminService;

    public RootController(ClientService clientService, EmployeeService employeeService, AdminService adminService) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.adminService = adminService;
    }
}
