package web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/getPoints/{id}")
    public List<Point> getPoints(@PathVariable(value = "id") long id) {
        User user = userService.getUserById(id);
        return pointService.findPointsByAuthor(user);
    }

    @PostMapping("/addPoint/{id}")
    public Point createPoint(@PathVariable(value = "id") long id, @RequestBody Point point) {
        point.setResult("Da");
        point.setAuthor(userService.getUserById(id));
        System.out.println(point);
        return pointService.createPoint(point);
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
