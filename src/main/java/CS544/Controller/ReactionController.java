package CS544.Controller;

import CS544.Dao.IReactionDao;
import CS544.Model.Post;
import CS544.Model.Reaction;
import CS544.Model.User;
import CS544.Service.PostService;
import CS544.Service.ReactionService;
import CS544.Service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/reaction")
public class ReactionController {

    @Autowired  // @Resource
    private ReactionService reactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public List<Reaction> getAll(){
        return reactionService.getAllReactions();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Reaction get(@PathVariable long id){
        return reactionService.getReaction(id);
    }

    @PostMapping(value = "/add/user/{user_id}/post/{post_id}")
    public ResponseEntity<?> addReaction(@Valid @RequestBody Reaction reaction, @PathVariable Long user_id, @PathVariable Long post_id) {

        User user = userService.findOne(user_id);
        Post post = postService.get(post_id);
        if (user == null || post == null) {
            throw new IllegalArgumentException("Invalid User ID or Post ID");
        }

        else {
            reaction.setPost(post);
            reaction.setReactor(user);
            reactionService.saveReaction(reaction);
            return ResponseEntity.ok(reaction);
        }
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
