package CS544.Service;

import CS544.Dao.IUserDao;
import CS544.Dto.ChangePassword;
import CS544.Dto.LoginRequest;
import CS544.Dto.UpdateProfileRequest;
import CS544.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@Transactional
public class UserService {
    @Autowired
    IUserDao userDao;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void addUser(User user){
        if(isExistsUserName(user.getUserName())){
            throw new IllegalArgumentException("UserName already exists");
        }
        else{
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            userDao.save(user);
        }
    }
    public User findOne(long id){
        return userDao.findById(id).get();
    }
    public Boolean isAuthenticated(LoginRequest user){
       User loggedInUser = userDao.findByUserName(user.getUserName());
        if (loggedInUser != null) {
            String storedPassword = loggedInUser.getPassword();
            return passwordEncoder.matches(user.getPassword(), storedPassword);
        }
       return false;
    }
    public Boolean isExistsUserName(String username){
        if(userDao.findByUserName(username)!=null){
            return true;
        };
        return false;
    }
    public void changePassword(ChangePassword changePassword,String userName){
        User user = userDao.findByUserName(userName);
        if (user != null && changePassword.getPassword().equals(changePassword.getConfirmPassword())) {
            String password = passwordEncoder.encode(changePassword.getPassword());
            user.setPassword(password);
            userDao.save(user);
        } else {
            throw new IllegalArgumentException("Password Mismatch");
        }
    }
   public User findByUserName(String username){
        return userDao.findByUserName(username);
   }
   public void delete(User user){
        userDao.delete(user);
   }
    public void updateProfile(String userName, UpdateProfileRequest updateProfileRequest) {
        User user = userDao.findByUserName(userName);
        // Update the profile fields
        user.setFirstName(updateProfileRequest.getFirstName());
        user.setLastName(updateProfileRequest.getLastName());
        user.setUserName(updateProfileRequest.getUserName());

        userDao.save(user);
    }
}
