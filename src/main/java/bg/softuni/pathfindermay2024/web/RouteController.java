package bg.softuni.pathfindermay2024.web;

import bg.softuni.pathfindermay2024.service.RouteService;
import bg.softuni.pathfindermay2024.service.dto.RouteShortInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RouteController {

    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String routes(Model model) {

//        RouteShortInfoDTO randomRoute = routeService.getRandomRoute();

        List<RouteShortInfoDTO> routes = routeService.getAll();

        model.addAttribute("allRoutes", routes);

        return "routes";

    }

}
