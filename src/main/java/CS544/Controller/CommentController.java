package CS544.Controller;

import CS544.Model.Comment;
import CS544.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/comment")
@Validated
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/add/postId/authorId", consumes = "application/json")
    public ResponseEntity<Comment> add(@Valid @RequestBody Comment comment) {
       // User user = userService
        //POST POST
//        comment.setPost();
//        comment.setAuthor();
        commentService.add(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Comment> get(@PathVariable Long id) {
        Comment comment= commentService.get(id);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Comment> update(@RequestBody Comment comment,@PathVariable Long id ) {

        if (id != comment.getId()) {
            throw new IllegalArgumentException();
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


