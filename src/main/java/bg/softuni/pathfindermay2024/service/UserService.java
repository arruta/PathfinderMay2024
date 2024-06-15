package bg.softuni.pathfindermay2024.service;

import bg.softuni.pathfindermay2024.data.UserRepository;
import bg.softuni.pathfindermay2024.model.User;
import bg.softuni.pathfindermay2024.service.dto.UserProfileDTO;
import bg.softuni.pathfindermay2024.web.dto.UserLoginDTO;
import bg.softuni.pathfindermay2024.web.dto.UserRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        User user = modelMapper.map(userRegisterDTO, User.class);

        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));


        userRepository.save(user);
    }

    public void login(UserLoginDTO loginData) {

        User user = userRepository.findByUsername(loginData.getUsername());

        if (user == null) {
            // todo throw error
            throw new RuntimeException("Username not found");
        }

        if (passwordEncoder.matches(loginData.getPassword(), user.getPassword()) && !currentUser.isLoggedIn()) {
            currentUser.setUser(user);
        } else {
            throw new RuntimeException("Password not valid");
        }
    }

    public void logout() {
        currentUser.setUser(null);
    }

    public UserProfileDTO getProfileData(){
        return modelMapper.map(currentUser.getUser(), UserProfileDTO.class);
    }
}
