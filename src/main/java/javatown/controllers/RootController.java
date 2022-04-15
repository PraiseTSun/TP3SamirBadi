package javatown.controllers;

import javatown.DTO.BookFormDTO;
import javatown.DTO.ClientFormDTO;
import javatown.service.AdminService;
import javatown.service.ClientService;
import javatown.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/client")
    public String getClient(Model model){
        model.addAttribute("clientTitle", "Client Section");
        return "client";
    }

    @GetMapping("/clientcreate")
    public String getClientCreate(@ModelAttribute ClientFormDTO clientForm, Model model, RedirectAttributes redirectAttributes){
        clientForm = new ClientFormDTO();
        model.addAttribute("clientForm", clientForm);
        return "clientedit";
    }

    @PostMapping("/clientcreate")
    public String clientPost(@ModelAttribute ClientFormDTO clientForm, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("client:" + clientForm);
        clientService.createClient(clientForm.toModel());
        redirectAttributes.addFlashAttribute("clientForm", clientForm);
        return "clientresult";
    }

    @GetMapping("/clientconnectloan")
    public String getClientConnectLoans(@ModelAttribute ClientFormDTO clientForm, Model model, RedirectAttributes redirectAttributes){
        clientForm = new ClientFormDTO();
        model.addAttribute("clientForm", clientForm);
        return "clientconnectloan";
    }

    @PostMapping("/clientconnectloan")
    public String clientConnectLoans(@ModelAttribute ClientFormDTO clientForm, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("client:" + clientForm);
        clientForm = clientService.getClientByPassword(clientForm);
        model.addAttribute("loans", clientForm.getLoans());
        return "clientloan";
    }

    @GetMapping("/clientconnectdebt")
    public String getClientConnectDebts(@ModelAttribute ClientFormDTO clientForm, Model model, RedirectAttributes redirectAttributes){
        clientForm = new ClientFormDTO();
        model.addAttribute("clientForm", clientForm);
        return "clientconnectdebt";
    }

    @PostMapping("/clientconnectdebt")
    public String clientConnectDebts(@ModelAttribute ClientFormDTO clientForm, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("client:" + clientForm);
        clientForm = clientService.getClientByPassword(clientForm);
        redirectAttributes.addFlashAttribute("clientForm", clientForm);
        return "clientdebt";
    }

    @GetMapping("/employee")
    public String getEmployee(Model model){
        model.addAttribute("employeeTitle", "Employee Section");
        return "employee";
    }

    @GetMapping("/bookcreate")
    public String getBookCreate (@ModelAttribute BookFormDTO bookForm, Model model, RedirectAttributes redirectAttributes) {
        bookForm = new BookFormDTO();
        model.addAttribute("bookForm", bookForm);
        return "bookedit";
    }
}
