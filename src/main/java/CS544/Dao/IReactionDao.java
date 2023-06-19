package CS544.Dao;

import CS544.Model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReactionDao extends JpaRepository<Reaction, Long> {

    public abstract List<Reaction> getByPostId(Long postId);

}
