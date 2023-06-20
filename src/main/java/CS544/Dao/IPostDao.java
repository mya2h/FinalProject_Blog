package CS544.Dao;

import CS544.Model.Post;
import CS544.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostDao extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User author);
}
