package fa.training.phonestore.Controller;

import fa.training.phonestore.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class RegisterController{
    @Autowired
AccountService accountService;


}
