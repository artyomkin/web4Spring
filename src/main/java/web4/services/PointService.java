package web4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web4.models.Point;
import web4.models.User;
import web4.repositories.PointRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointService {
    @Autowired
    PointRepository pointRepository;

    @Transactional
    public List<Point> getPoints() {
        return pointRepository.findAll();
    }

    @Transactional
    public Point createPoint(Point point) {
        return pointRepository.save(point);
    }

    @Transactional
    public void clearPoints() {
        pointRepository.deleteAll();
    }

    @Transactional
    public List<Point> findPointsByAuthor(User user) {
        return pointRepository.findPointsByAuthor(user);
    }
    @Transactional
    public void clearPointsByAuthor(User user){
        pointRepository.deleteAllByAuthor(user);
    }
}
