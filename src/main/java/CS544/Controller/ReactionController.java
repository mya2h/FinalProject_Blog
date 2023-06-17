package CS544.Controller;

import CS544.Dao.IReactionDao;
import CS544.Model.Reaction;
import CS544.Service.ReactionService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping( "/reaction")
public class ReactionController {

    @Autowired  // @Resource
    private ReactionService reactionService;

    @GetMapping("/")
    public List<Reaction> getAll(){
        return reactionService.getAllReactions();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Reaction get(@PathVariable long id){
        return reactionService.getReaction(id);
    }

    @PostMapping(value = "/add")             // add reaction
    public RedirectView addReaction(@Valid @RequestBody Reaction reaction){
        reactionService.saveReaction(reaction);
        return new RedirectView("/reaction/" + reaction.getId());
    }

    @PutMapping(value = "/add/{id}")
    public void put(@PathVariable long id, @Valid @RequestBody Reaction reaction){
        if (id != reaction.getId()) {
            throw new IllegalArgumentException();
        }
        reactionService.updateReaction(reaction);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable long id){
        reactionService.deleteReaction(id);
    }
}
