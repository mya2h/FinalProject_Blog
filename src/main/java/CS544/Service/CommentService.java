package CS544.Service;

import CS544.Dao.ICommentDao;
import CS544.Model.Comment;
import CS544.Model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private ICommentDao commentDao;
    public List<Comment> getAll(){
        return commentDao.findAll();
    }
    public void add(Comment comment){
        commentDao.save(comment);
    }
    public void update(Comment comment){
        commentDao.save(comment);
    }
    public void delete(long id){
        commentDao.deleteById(id);
    }
//    public Comment get(long id){
//       return commentDao.findById(id).get();
//    }
    public List<Comment> getByPost(Post post){
        return commentDao.getByPost(post);

    }

}
