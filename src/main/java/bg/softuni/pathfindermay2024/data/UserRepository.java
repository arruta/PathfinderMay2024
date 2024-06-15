package bg.softuni.pathfindermay2024.data;

import bg.softuni.pathfindermay2024.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
