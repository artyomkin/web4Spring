package web4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web4.models.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
}
