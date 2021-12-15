package web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web4.models.User;
import web4.services.UserService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            return new ResponseEntity<>("Поля не могут быть пустыми", HttpStatus.BAD_REQUEST);
        }
        if (userService.loadUserByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("Пользователь с таким логином уже существует", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        String message = "Пользователь успешно добавлен, id=" + userService.loadUserByUsername(user.getUsername()).getId();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/authorization")
    public ResponseEntity<String> authorizationUser(@RequestBody User user) {
        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            return new ResponseEntity<>("Поля не могут быть пустыми", HttpStatus.BAD_REQUEST);
        }
        User userFromDB = userService.loadUserByUsername(user.getUsername());
        if (userFromDB == null) {
            return new ResponseEntity<>("Пользователь не найдён", HttpStatus.BAD_REQUEST);
        } else if (!checkUser(user)) {
            return new ResponseEntity<>("Пароль указан неверно", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Пользователь успешно авторизовался, id=" + userFromDB.getId(), HttpStatus.OK);
    }
    public boolean checkUser(User user) {
        User userByLogin = userService.loadUserByUsername(user.getUsername());
        if (userByLogin == null) {
            return false;
        }
        return passwordEncoder.matches(user.getPassword(), userByLogin.getPassword());
    }
}
