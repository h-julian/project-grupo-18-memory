package es.grupo18.jobmatcher.service;

import org.springframework.stereotype.Service;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.repository.InMemoryUserRepository;

@Service
public class UserService {

    private final InMemoryUserRepository userRepository;

    public UserService(InMemoryUserRepository userRepository){
         this.userRepository = userRepository;
    }

    public User registerUser(User user) {
         if(userRepository.existsByEmail(user.getEmail())){
              throw new RuntimeException("El usuario ya existe");
         }
         return userRepository.save(user);
    }

    public User getUserByEmail(String email){
         return userRepository.findByEmail(email).orElse(null);
    }
}
