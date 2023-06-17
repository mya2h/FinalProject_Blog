package CS544.Controller;

import CS544.Helper.JWTUtil;
import CS544.Helper.LoginRequest;
import CS544.Helper.Response;
import CS544.Model.User;
import CS544.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JWTUtil jwtUtil;
    @GetMapping(value = "/{id}",produces = "application/json")
    public User get(@PathVariable long id){
       return userService.findOne(id);
    }
    @PostMapping(value = "/add",consumes = "application/json",produces = "application/json")
    public ResponseEntity<User> register(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping(value = "/login",consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        if(userService.isAuthenticated(request)){
            String token = jwtUtil.generateToken(request.getUserName());
            return new ResponseEntity<>(token,HttpStatus.CREATED);
        }
//        Response response = new Response(HttpStatus.FORBIDDEN,false,"Username/Password Incorrect")
       return new ResponseEntity<>("Username/Password Incorrect",HttpStatus.FORBIDDEN);
    }
}
