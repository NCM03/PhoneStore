package fa.training.phonestore.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {
    @GetMapping("/ValidAuthenticate")
    public String getError(){
        return "404";
    }
    @GetMapping("/AccessDenied")
    public String validAuthenticate(){
        return "403";}
    }

