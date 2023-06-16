package CS544.Service;

import CS544.Dao.ICommentDao;
import CS544.Model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommentService {
    @Autowired
    private ICommentDao commentDao;
    public void add(Comment comment){
        commentDao.save(comment);
    }
    public void edit(Comment comment){
        commentDao.save(comment);
    }
    public void delete(long id){
        commentDao.deleteById(id);
    }
    public Comment get(long id){
       return commentDao.findById(id).get();
    }
}
