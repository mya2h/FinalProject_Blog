package CS544.Controller;

import CS544.Dto.*;
import CS544.Helper.*;
import CS544.Model.User;
import CS544.Service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
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
    UserLoginResponse userLoginResponse;
    @Autowired
    Response response;
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
    public ResponseEntity<Object> login(@RequestBody LoginRequest request){
        if(userService.isAuthenticated(request)){
            String token = jwtUtil.generateToken(request.getUserName());
            userLoginResponse.setSuccess(true);
            userLoginResponse.setToken(token);
            return new ResponseEntity<>(userLoginResponse,HttpStatus.CREATED);
        }
        response.setSuccess(false);
        response.setMessage("Username/Password Incorrect");
       return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }
    @PutMapping(value = "/changePassword",consumes = "application/json")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePassword changePassword,HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();
        userService.changePassword(changePassword,userName);
        response.setSuccess(true);
        response.setMessage("Password changed successfully");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateProfile(
            @RequestBody UpdateProfileRequest updateProfileRequest,HttpServletRequest request

    ) {
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            String userName = claims.getSubject();
            userService.updateProfile(userName, updateProfileRequest);
            response.setSuccess(true);
            response.setMessage("Successfully update user");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update profile");
        }
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> deleteProfile(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();
        User user = userService.findByUserName(userName);
        userService.delete(user);
        response.setSuccess(true);
        response.setMessage("Successfully deleted profile");
        return ResponseEntity.ok(response);
    }
}
