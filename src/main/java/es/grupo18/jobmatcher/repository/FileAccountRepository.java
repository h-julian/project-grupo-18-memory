package es.grupo18.jobmatcher.repository;

import es.grupo18.jobmatcher.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileAccountRepository {

    private final ObjectMapper mapper;
    private final File file = new File("src/main/resources/static/data/user.json");
    private List<Account> accounts = new ArrayList<>();

    public FileAccountRepository() {
        // Configurar el ObjectMapper para manejar polimorfismo
        BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("es.grupo18.jobmatcher.model")
                .build();
        mapper = new ObjectMapper();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        loadAccounts();
    }

    private void loadAccounts() {
        if (file.exists()) {
            try {
                accounts = mapper.readValue(file, new TypeReference<List<Account>>() {});
            } catch (IOException e) {
                e.printStackTrace();
                accounts = new ArrayList<>();
            }
        }
    }

    private void saveAccounts() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda una cuenta en el repositorio. Si ya existe, puede actualizarla.
     * @param account Cuenta (User, Company o Admin)
     * @return La cuenta guardada
     */
    public Account save(Account account) {
        // Si se desea, se puede implementar una lógica de actualización
        accounts.add(account);
        saveAccounts();
        return account;
    }

    /**
     * Busca una cuenta por email.
     * @param email Email a buscar.
     * @return La cuenta encontrada o null si no existe.
     */
    public Account findByEmail(String email) {
        return accounts.stream()
                .filter(a -> a.getEmail().equalsIgnoreCase(email))
                .findFirst().orElse(null);
    }

    /**
     * Devuelve todas las cuentas.
     */
    public List<Account> findAll() {
        return accounts;
    }
}
