package CS544.Service;

import CS544.Dao.IReactionDao;
import CS544.Model.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReactionService {

    @Autowired
    private IReactionDao reactionDao;

    public void saveReaction(Reaction reaction){
        reactionDao.save(reaction);
    }

    public void deleteReaction(Long id){
        reactionDao.deleteById(id);
    }

    public Reaction getReaction(Long id){
        return reactionDao.findById(id).get();
    }

    public void updateReaction(Reaction reaction){
        reactionDao.save(reaction);
    }

    public List<Reaction> getAllReactions(){
        return reactionDao.findAll();
    }

}
