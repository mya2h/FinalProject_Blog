package CS544.Controller;

import CS544.Dao.IReactionDao;
import CS544.Model.Reaction;
import CS544.Service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reaction")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/add")             // add reaction
    public String addReaction(@RequestBody Reaction reaction){
        return "addReaction";
    }

    @GetMapping("/delete")
    public String deleteReaction(){
        return "deleteReaction";
    }

    @GetMapping("/update")
    public String updateReaction(){
        return "updateReaction";
    }
}
