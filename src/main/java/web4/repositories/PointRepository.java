package web4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web4.models.Point;
import web4.models.User;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findPointsByAuthor(User user);
    void deleteAllByAuthor(User user);
}
