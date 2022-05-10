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

    @GetMapping("/documents_title_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByName(@PathVariable String value){
        return clientService.getDocumentsByTitle(value);
    }

    @GetMapping("/documents_author_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByAuthor(@PathVariable String value){
        return clientService.getDocumentsByAuthor(value);
    }

    @GetMapping("/documents_editor_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByEditor(@PathVariable String value){
        return clientService.getDocumentsByEditor(value);
    }

    @GetMapping("/documents_genre_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByGenre(@PathVariable String value){
        return clientService.getDocumentsByGenre(value);
    }

    @GetMapping("/documents_year_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByYear(@PathVariable String value){
        return clientService.getDocumentsByYear(value);
    }

    @GetMapping("/create_book_{title}_{author}_{editor}_{genre}_{year}_{page}")
    public AbstractDocumentFormDTO createBook(
            @PathVariable String title,
            @PathVariable String author,
            @PathVariable String editor,
            @PathVariable String genre,
            @PathVariable String year,
            @PathVariable Integer page
    ){
        return employeService.createBook(title, author, editor, year, page, genre);
    }

    @GetMapping("/create_cd_{title}_{author}_{editor}_{genre}_{year}")
    public AbstractDocumentFormDTO createCD(
            @PathVariable String title,
            @PathVariable String author,
            @PathVariable String editor,
            @PathVariable String genre,
            @PathVariable String year
    ){
        return employeService.createCD(title, author, editor, year, genre);
    }

    @GetMapping("/create_dvd_{title}_{author}_{editor}_{genre}_{year}")
    public AbstractDocumentFormDTO createDVD(
            @PathVariable String title,
            @PathVariable String author,
            @PathVariable String editor,
            @PathVariable String genre,
            @PathVariable String year
    ){
        return employeService.createDVD(title, author, editor, year, genre);
    }

    @GetMapping("/loans/{id}")
    public List<LoanFormDTO> getLoansOfMonth(@PathVariable int id){
        return adminService.getLoansOfMonth(id);
    }

}
