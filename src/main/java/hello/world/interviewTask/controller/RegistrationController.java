package hello.world.interviewTask.controller;

import hello.world.interviewTask.entity.User;
import hello.world.interviewTask.model.PasswordValidation;
import hello.world.interviewTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Model model, User user) {
        User userFromDb = userService.getUser(user.getUsername());

        if (userFromDb != null){
            model.addAttribute("errorMessage", "User already exists");
            return "registration";
        }

        userService.saveUser(user);

        return "redirect:/login";
    }


    @PostMapping("/validation")
    @ResponseBody
    public String checkPassword(@RequestBody PasswordValidation password){
        String pass = password.getPassword();
        if (pass.length() < 6){
            return "Your password is weak, plaese make it at least 6 symbols long.";
        }
        Pattern p = Pattern.compile( "[0-9]" );
        Matcher m = p.matcher( pass );
        if (!m.find()){
            return "Your password is almost good, please include digits.";
        }
        return "Your password is secure";
    }

}
