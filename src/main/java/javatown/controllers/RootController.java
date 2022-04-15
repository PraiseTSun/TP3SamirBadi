package javatown.controllers;

import javatown.service.AdminService;
import javatown.service.ClientService;
import javatown.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/")
    public String getRootRequest(Model model){
        model.addAttribute("pageTitle", "TP3 Samir Badi");
        model.addAttribute("mainTitle", "Users Sections");
        return "index";
    }
}
