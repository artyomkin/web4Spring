package web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web4.Validator.PointsValidator;
import web4.models.Point;
import web4.models.User;
import web4.services.PointService;
import web4.services.UserService;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController /* @Controller + @ResponseBody || return json*/
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping()
public class PointController {
    @Autowired
    private PointService pointService;
    @Autowired
    private UserService userService;
    @Autowired
    private PointsValidator pointsValidator;

    @GetMapping("/getPoints/{id}")
    public List<Point> getPoints(@PathVariable(value = "id") long id) {
        System.out.println("ya tyt");
        System.out.println(id);
        User user = userService.getUserById(id);
        List<Point> p= pointService.findPointsByAuthor(user);
        System.out.println(p);
        return pointService.findPointsByAuthor(user);
    }

    @PostMapping("/addPoint/{id}")
    public Point createPoint(@PathVariable(value = "id") long id, @RequestBody Point point) {
        try {
            if (pointsValidator.validate(point)) {
                point.setAuthor(userService.getUserById(id));
                System.out.println(point);
                return pointService.createPoint(point);
//                return new ResponseEntity<String>("Выстрел успешно добавлен",HttpStatus.OK);\
//                return point;
            }
        } catch (Exception e) {
            return null;
//            return new ResponseEntity<>("Возникла ошибка", HttpStatus.BAD_REQUEST);
        }
//        return new ResponseEntity<>("Данные не валидные, повторите ввод", HttpStatus.BAD_REQUEST);
        return null;
    }

    @GetMapping("/clearPoints/{id}")
    public List<Point> clearPoints(@PathVariable(value = "id") long id) {
//        pointService.clearPoints();
//        return new ArrayList<>();
        User user = userService.getUserById(id);
        pointService.clearPointsByAuthor(user);
        return new ArrayList<>();
    }
}
