package CS544.Controller;

import CS544.Model.Comment;
import CS544.Model.Post;
import CS544.Model.User;
import CS544.Service.CommentService;
import CS544.Service.PostService;
import CS544.Service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
@Validated
public class CommentController {
    @Autowired
    private UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/")
    public List<Comment> getAll(){
        return  commentService.getAll();
    }

    @PostMapping(value = "/add/{postId}", consumes = "application/json")
    public ResponseEntity<?> add(@Valid @RequestBody Comment comment, BindingResult result, @PathVariable Long postId, HttpServletRequest request) {
        if (result.hasErrors()) {
            // Handle validation errors
            List<String> errors = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();
        User user = userService.findByUserName(userName);
        Post post=postService.get(postId);
        comment.setPost(post);
        comment.setAuthor(user);
        commentService.add(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping(value = "/{postId}", produces = "application/json")
    public ResponseEntity<List<Comment>> get(@PathVariable Long postId) {
        Post post = postService.get(postId);
        List<Comment> comment= commentService.getByPost(post);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Comment comment,BindingResult result,@PathVariable Long id ) {
        Comment comment1 = commentService.get(id);
        if(comment1==null){
            return ResponseEntity.notFound().build();
        }
        comment1.setDescription(comment.getDescription());

        if (result.hasErrors()) {
            // Handle validation errors
            List<String> errors = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
        commentService.update(comment1);
        return ResponseEntity.ok(comment1);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Comment comment1 = commentService.get(id);
        if(comment1==null){
            return ResponseEntity.notFound().build();
        }
        commentService.delete(id);
        return ResponseEntity.ok("comment deleted successfully");
    }
}


