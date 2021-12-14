package web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web4.models.User;
import web4.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
    @Autowired
    UserService userService;
//
//    @GetMapping("/registration")
//    public String have() {
//        return "tyt";
//    }

    @PostMapping("/registration")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        user.setActive(true);
        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            return new ResponseEntity<>("Поля не могут быть пустыми", HttpStatus.BAD_REQUEST);

        }
        if (userService.loadUserByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("Пользователь с таким логином уже существует", HttpStatus.BAD_REQUEST);
        }
        System.out.println(user);
        userService.addUser(user);
        return new ResponseEntity<>("Пользователь успешно добавлен", HttpStatus.OK);
    }

    @PostMapping("/authorization")
    public ResponseEntity<String> authorizationUser(@RequestBody User user) {
        user.setActive(true);
        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            return new ResponseEntity<>("Поля не могут быть пустыми", HttpStatus.BAD_REQUEST);
        }
        System.out.println(user);
        User userfromDB = userService.loadUserByUsername(user.getUsername());
        if (userfromDB == null) {
            return new ResponseEntity<>("Пользователь не найдён", HttpStatus.BAD_REQUEST);
        } else if (!userfromDB.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>("Пароль указан неверно", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Пользователь успешно авторизовался", HttpStatus.OK);
    }
}
