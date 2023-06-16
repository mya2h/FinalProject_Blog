package CS544.Controller;

import CS544.Model.Comment;
import CS544.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping(value = "/add/",consumes = "application/json")
    public Comment add(Comment comment){
        commentService.add(comment);
        return comment;
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public Comment get(@PathVariable long id){
        return commentService.get(id);
    }

}
