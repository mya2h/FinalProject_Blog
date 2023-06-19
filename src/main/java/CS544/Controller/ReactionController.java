package CS544.Controller;

import CS544.Model.Post;
import CS544.Model.Reaction;
import CS544.Model.User;
import CS544.Service.PostService;
import CS544.Service.ReactionService;
import CS544.Service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/getPost/{post_id}", produces = "application/json")
    public List<Reaction> getPost(@PathVariable long post_id){
            return reactionService.getByPostId(post_id);
    }

    @PostMapping(value = "/add/post/{post_id}")
    public ResponseEntity<Reaction> addReaction(@Valid @RequestBody Reaction reaction, HttpServletRequest request, @PathVariable Long post_id) {

        Claims claims = (Claims) request.getAttribute("claims");
        String username = claims.getSubject();
        User user = userService.findByUserName(username);
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

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Reaction> put(@PathVariable long id, @Valid @RequestBody Reaction reaction){
        Reaction reaction1 = reactionService.getReaction(id);
        if(reaction1==null){
            return ResponseEntity.notFound().build();
        }
        reaction1.setReacted(reaction.getReacted());
        reactionService.updateReaction(reaction1);
        return ResponseEntity.ok(reaction1);
    }

    @DeleteMapping(value = "/delete/{id}")

    public ResponseEntity<String> delete(@PathVariable long id){
        Reaction reaction1 = reactionService.getReaction(id);
        if(reaction1==null){
            return ResponseEntity.notFound().build();
        }
        reactionService.deleteReaction(id);
        return ResponseEntity.ok("Reaction updated successfully!!");
    }

}
