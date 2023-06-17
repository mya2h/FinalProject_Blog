package CS544.Dao;

import CS544.Model.Comment;
import CS544.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentDao extends JpaRepository<Comment,Long> {
    abstract List<Comment> getByPost(Post post);
}
