package web4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web4.models.User;
import web4.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }
}
