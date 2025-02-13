package es.grupo18.jobmatcher.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Optional;
import es.grupo18.jobmatcher.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository {

    // Usamos el email como clave
    private ConcurrentHashMap<String, Usuario> userMap = new ConcurrentHashMap<>();

    public Usuario save(Usuario user) {
        userMap.put(user.getEmail(), user);
        return user;
    }

    public Optional<Usuario> findByEmail(String email) {
        return Optional.ofNullable(userMap.get(email));
    }

    public boolean existsByEmail(String email) {
        return userMap.containsKey(email);
    }
}
