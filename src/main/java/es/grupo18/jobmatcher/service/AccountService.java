package es.grupo18.jobmatcher.service;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.User;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final List<Account> accounts = new ArrayList<>();
    private final UserService userService;
    private final CompanyService companyService;

    public AccountService(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @PostConstruct
    public void initAccounts() { // Uploads users and companies to memory
        // Obtains existant users from AccountService
        Account user = userService.getUser();
        accounts.add(user);

        // Obtains existant companies from CompanyService
        List<Company> companies = companyService.getCompaniesList();
        for (Company company : companies) {
            accounts.add(company);
        }

        System.out.println("Accounts (users and companies) uploaded to memory");
    }

    public Account findAccountById(long id) { // Returns the account with the given id
        return accounts.stream()
                .filter(account -> account.getAccountId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Account> getAccounts() { // Returns all accounts
        return accounts;
    }

    public List<Account> getUsers() { // Returns all users
        List<Account> users = new ArrayList<>();
        for (Account account : accounts) {
            if (account instanceof User) {
                users.add(account);
            }
        }
        return users;
    }

    public List<Account> getCompanies() { // Returns all companies
        List<Account> companies = new ArrayList<>();
        for (Account account : accounts) {
            if (account instanceof Company) {
                companies.add(account);
            }
        }
        return companies;
    }

}
