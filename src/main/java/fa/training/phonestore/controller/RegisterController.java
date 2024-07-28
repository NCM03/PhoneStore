package fa.training.phonestore.controller;

import fa.training.phonestore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class RegisterController{
    @Autowired
AccountService accountService;


}
