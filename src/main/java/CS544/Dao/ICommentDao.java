package CS544.Dao;

import CS544.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentDao extends JpaRepository<Comment,Long> {
}
