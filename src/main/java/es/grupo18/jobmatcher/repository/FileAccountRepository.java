package es.grupo18.jobmatcher.repository;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FileAccountRepository {

    @Autowired
    private FileUserRepository fileUserRepository;

    @Autowired
    private FileCompanyRepository fileCompanyRepository;

    /**
     * Guarda una cuenta delegando en el repositorio correspondiente.
     * @param account Cuenta (User o Company)
     * @return La cuenta guardada
     */
    public Account save(Account account) {
        if (account instanceof User) {
            return fileUserRepository.save((User) account);
        } else if (account instanceof Company) {
            return fileCompanyRepository.save((Company) account);
        }
        throw new IllegalArgumentException("Tipo de cuenta no soportado.");
    }

    /**
     * Busca una cuenta por email consultando ambos repositorios.
     * @param email Email a buscar
     * @return La cuenta encontrada o null si no existe.
     */
    public Account findByEmail(String email) {
        Account account = fileUserRepository.findByEmail(email);
        if (account == null) {
            account = fileCompanyRepository.findByEmail(email);
        }
        return account;
    }

    /**
     * Devuelve todas las cuentas de usuarios y empresas.
     * @return Lista de cuentas
     */
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(fileUserRepository.findAll());
        accounts.addAll(fileCompanyRepository.findAll());
        return accounts;
    }
}
