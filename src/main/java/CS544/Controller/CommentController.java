package CS544.Controller;

import CS544.Model.Comment;
import CS544.Model.Post;
import CS544.Model.User;
import CS544.Service.CommentService;
import CS544.Service.PostService;
import CS544.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/add/{postId}/{authorId}", consumes = "application/json")
    public ResponseEntity<?> add(@Valid @RequestBody Comment comment, BindingResult result, @PathVariable Long postId, @PathVariable Long authorId) {
        if (result.hasErrors()) {
            // Handle validation errors
            List<String> errors = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }


        User user = userService.findOne(authorId);
        Post post=postService.get(postId);
       comment.setPost(post);
        comment.setAuthor(user);
        commentService.add(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping(value = "/{postId}", produces = "application/json")
    public ResponseEntity<Comment> get(@PathVariable Long postId) {
        Post post = postService.get(postId);
        Comment comment= commentService.getByPost(post);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Comment comment,BindingResult result,@PathVariable Long id ) {

        if (id != comment.getId()) {
            throw new IllegalArgumentException();
        }
        if (result.hasErrors()) {
            // Handle validation errors
            List<String> errors = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
        commentService.update(comment);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Comment> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


