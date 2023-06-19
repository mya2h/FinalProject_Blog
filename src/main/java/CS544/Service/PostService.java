package CS544.Service;

import CS544.Dao.IPostDao;
import CS544.Model.Post;
import CS544.Model.User;
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
    public Post  update(Post post, Long id){
        Post p = iPostDao.getById(id);
        p.setDescription(post.getDescription());
        p.setTitle(post.getTitle());
        System.out.println(p+ "testing!");
        iPostDao.save(p);
        return p;
    }
    public void delete(Long id){
        iPostDao.deleteById(id);
    }


}
