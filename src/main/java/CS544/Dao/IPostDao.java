package CS544.Dao;

import CS544.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostDao extends JpaRepository<Post, Long> {
}
