package es.grupo18.jobmatcher.repository;

import es.grupo18.jobmatcher.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class FileUserRepository {

    private final ObjectMapper mapper;
    private final File file = new File("src/main/resources/static/data/users.json");
    private List<User> users;

    public FileUserRepository() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        if (file.exists()) {
            try {
                List<User> loadedUsers = mapper.readValue(file, new TypeReference<List<User>>() {});
                if (loadedUsers != null) {
                    users = loadedUsers;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void saveUsers() {
        try {
            file.getParentFile().mkdirs();
            List<User> currentUsers = new ArrayList<>(users); // Crear una copia de la lista actual
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, currentUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long generateId() {
        return users.stream()
                .map(User::getId)
                .filter(id -> id != null)
                .max(Comparator.naturalOrder())
                .orElse(-1L) + 1;
    }

    public synchronized User save(User user) {
        loadUsers(); // Recargar para tener la lista más reciente

        // Validar email único (opcional, según requisitos)
        if (users.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()))) {
            throw new IllegalArgumentException("El email ya existe");
        }

        // Asignar ID si no tiene
        if (user.getId() == null) {
            user.setId(generateId());
        }

        users.add(user);
        saveUsers();
        return user;
    }

    public User findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<User> findAll() {
        return new ArrayList<>(users); // Devolver una copia para evitar modificaciones externas
    }
}