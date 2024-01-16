package by.itclass.controllers;

import by.itclass.model.entities.User;
import by.itclass.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static by.itclass.constants.AppConst.*;

@Controller
public class UserController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }


    @GetMapping(value = "/")
    public String root(){
        return INDEX_PAGE;

    }
    @GetMapping(value = ALL_USER_URL)
    public String userControllerMethodWhichReturnAllUsers(Model model) {
        var users = service.getAllUsers();
        model.addAttribute(USER_LIST_ATTR, users);
        return USERS_PAGE;
    }

    @GetMapping(value = ADD_USER_URL)
    public ModelAndView addUserFromForm(){
       return new ModelAndView(ADD_PAGE, USER_LIST_ATTR, new User());

    }
    public String save(@ModelAttribute(name = USER_ATTR) User user){
        service.add(user);
    }

}
