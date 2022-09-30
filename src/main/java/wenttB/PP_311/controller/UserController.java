package wenttB.PP_311.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wenttB.PP_311.models.User;
import wenttB.PP_311.service.UserService;


@Controller
@RequestMapping(value = "/" )
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers (Model model) {
        model.addAttribute("users",userService.allUsers());
        return "index";
    }

    @GetMapping(value = "/new")
    public String newUser (Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new";
    }
    @GetMapping(value = "/{id}")
    public String show (Model model,@PathVariable(value = "id") long id) {
        model.addAttribute("user",userService.show(id));
        return "show";
    }
    @PostMapping()
    public String createUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";

    }
    @GetMapping(value = "/{id}/edit")
    public String edit (Model model, @PathVariable(value = "id") long id) {
        model.addAttribute("users",userService.show(id));
        return "edit";
    }
    @PatchMapping(value = "/{id}")
    public  String update (@ModelAttribute("users") User user, @PathVariable(value = "id") long id) {
        userService.update(id, user);
        return "redirect:/";
    }
    @DeleteMapping(value = "/{id}")
    public String delete ( @PathVariable(value = "id") long id) {
        userService.delete(id);
        return "redirect:/";
    }


}
