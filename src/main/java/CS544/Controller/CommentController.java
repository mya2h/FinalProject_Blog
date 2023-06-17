package CS544.Controller;

import CS544.Model.Comment;
import CS544.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Comment> add(@RequestBody Comment comment) {
        commentService.add(comment);
        return ResponseEntity.ok(comment);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Comment> get(@PathVariable Long id) {
        Comment comment= commentService.get(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping("/add/{id}")
    public void update(@RequestBody Comment comment,@PathVariable Long id ) {

        if (id != comment.getId()) {
            throw new IllegalArgumentException();
        }

        commentService.update(comment);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        commentService.delete(id);
    }
}


