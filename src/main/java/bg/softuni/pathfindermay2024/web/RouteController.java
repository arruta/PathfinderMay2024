package bg.softuni.pathfindermay2024.web;

import bg.softuni.pathfindermay2024.model.CategoryType;
import bg.softuni.pathfindermay2024.model.Level;
import bg.softuni.pathfindermay2024.service.RouteService;
import bg.softuni.pathfindermay2024.service.dto.RouteShortInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Controller to handle all things route relates.
 */
@Controller
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    /**
     * Method to handle tke listing of all routes.
     */
    @GetMapping("/routes")
    public String routes(Model model) {

        List<RouteShortInfoDTO> routes = routeService.getAll();

        model.addAttribute("allRoutes", routes);

        return "routes";
    }

    @GetMapping("/routes/add-route")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("route", new RouteShortInfoDTO());
        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categoryTypes", CategoryType.values());
        return modelAndView;
    }

}
