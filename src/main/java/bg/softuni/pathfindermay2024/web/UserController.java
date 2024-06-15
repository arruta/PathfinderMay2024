package bg.softuni.pathfindermay2024.web;

import bg.softuni.pathfindermay2024.model.Level;
import bg.softuni.pathfindermay2024.service.UserService;
import bg.softuni.pathfindermay2024.web.dto.UserLoginDTO;
import bg.softuni.pathfindermay2024.web.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("registerData", new UserRegisterDTO());
        model.addAttribute("levels", Level.values());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO data, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterDTO", bindingResult);
            //handle errors
            return "redirect:/users/register";
        }

        userService.register(data);

        //register user
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {

        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("loginData", new UserLoginDTO());

        return modelAndView;
    }

    @PostMapping("/login")
    public String login(UserLoginDTO loginData) {

        userService.login(loginData);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout() {

       userService.logout();

        return "redirect:/";
    }

    @GetMapping("/profile")
    public ModelAndView profile(){

        ModelAndView modelAndView = new ModelAndView("profile");

        modelAndView.addObject("profileData", userService.getProfileData());

        return modelAndView;
    }
}
