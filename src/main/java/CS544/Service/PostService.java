package CS544.Service;

import CS544.Dao.IPostDao;
import CS544.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private IPostDao iPostDao;

    public void save(Post post){
        iPostDao.save(post);
    }
    public Post get(Long id){
       return iPostDao.findById(id).get();
    }
    public List<Post> getAll(){
       return iPostDao.findAll();
    }
    public void update(Post post){
        iPostDao.save(post);
    }
    public void delete(Post post){
        iPostDao.delete(post);
    }


}
