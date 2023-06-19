package CS544.Controller;

import CS544.Helper.*;
import CS544.Model.User;
import CS544.Service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<User> register(@Valid @RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping(value = "/login",consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        if(userService.isAuthenticated(request)){
            String token = jwtUtil.generateToken(request.getUserName());
            return new ResponseEntity<>(token,HttpStatus.CREATED);
        }
       return new ResponseEntity<>("Username/Password Incorrect",HttpStatus.FORBIDDEN);
    }
    @PutMapping(value = "/changePassword/{userId}",consumes = "application/json")
    public ResponseEntity<String> changePassword(@PathVariable long userId,@RequestBody ChangePassword changePassword){
        userService.changePassword(changePassword,userId);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateProfile(
            @PathVariable Long userId,
            @RequestBody UpdateProfileRequest updateProfileRequest
    ) {
        try {
            userService.updateProfile(userId, updateProfileRequest);
            return ResponseEntity.ok("Profile updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update profile");
        }
    }
}
