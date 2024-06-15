package bg.softuni.pathfindermay2024.service;

import bg.softuni.pathfindermay2024.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private User user;

    public boolean isLoggedIn(){
        return this.user != null;
    }

    public boolean isAdmin(){
        return this.user.getRoles()
                .stream()
                .anyMatch(role -> role.getName().toString().equals("ADMIN"));
    }

    public User getUser() {
        return user;
    }

    public CurrentUser setUser(User user) {
        this.user = user;
        return this;
    }
}
