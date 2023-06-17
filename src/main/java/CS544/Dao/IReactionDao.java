package CS544.Dao;

import CS544.Model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReactionDao extends JpaRepository<Reaction, Long> {

}
