package fa.training.phonestore.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @GetMapping("/home")
    public String home() {
        return "index";
    }

}
