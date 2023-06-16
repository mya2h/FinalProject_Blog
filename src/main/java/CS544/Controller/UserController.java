package CS544.Controller;

import CS544.Model.User;
import CS544.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping(value = "/{id}",produces = "application/json")
    public User get(@PathVariable long id){
       return userService.findOne(id);
    }
    @PostMapping(value = "/add",consumes = "application/json",produces = "application/json")
    public User register(User user){
        userService.addUser(user);
        return user;
    }
}
