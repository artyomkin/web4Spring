package web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web4.models.Point;
import web4.repositories.PointRepository;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController /* @Controller + @ResponseBody || return json*/
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping()
public class PointController {
    @Autowired
    private PointRepository pointRepository;

    @GetMapping("/getPoints")
    public List<Point> getPoints() {
        return pointRepository.findAll();
    }

    @PostMapping("/addPoint")
    public Point createPoint(@RequestBody Point point) {
        point.setResult("Da");
        System.out.println(point);
        return pointRepository.save(point);
    }

    @GetMapping("/clearPoints")
    public List<Point> clearPoints() {
        pointRepository.deleteAll();
        return null;
    }
}
