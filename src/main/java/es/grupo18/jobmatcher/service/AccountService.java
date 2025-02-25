package es.grupo18.jobmatcher.service;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Company;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final List<Account> accounts = new ArrayList<>();
    private final UserService userService;

    public AccountService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initAccounts() {
        // Obtains existant users from AccountService
        Account user = userService.getUser();
        accounts.add(user);

        // Creates companies directly in memory
        accounts.add(new Company(2L, "Microsoft", "hr@microsoft.com", "pass123", "Microsoft Corporation", "/img/microsoft.png"));
        accounts.add(new Company(12L, "Google", "careers@google.com", "pass123", "Google LLC", "/img/google.png"));
        accounts.add(new Company(22L, "Apple", "jobs@apple.com", "pass123", "Apple Inc.", "/img/apple.png"));

        System.out.println("Accounts (users and companies) uploaded to memory");
    }

    public Account findAccountById(long id) {
        return accounts.stream()
            .filter(account -> account.getAccountId() == id)
            .findFirst()
            .orElse(null);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

}
