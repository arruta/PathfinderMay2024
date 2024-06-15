package bg.softuni.pathfindermay2024.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

/**
 * Controller handling the info pages - home, about, contact
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        double sofiaTemp = new Random().nextDouble();

        model.addAttribute("sofiaTemperature", sofiaTemp);

        return "index";
    }

    @GetMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }

}
