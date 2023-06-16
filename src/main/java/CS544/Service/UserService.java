package CS544.Service;

import CS544.Dao.IUserDao;
import CS544.Helper.JWTUtil;
import CS544.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    IUserDao userDao;
    public void addUser(User user){
        userDao.save(user);
    }
    public User findOne(long id){
        return userDao.findById(id).get();
    }
    public Boolean isAuthenticated(JWTUtil.LoginRequest user){
       User loggedInUser = userDao.findByUserName(user.getUserName());
       if(loggedInUser.getPassword().equals(user.getPassword())){
           return true;
       }
       return false;
    }

}
