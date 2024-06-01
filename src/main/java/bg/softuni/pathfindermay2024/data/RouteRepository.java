package bg.softuni.pathfindermay2024.data;

import bg.softuni.pathfindermay2024.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
}
