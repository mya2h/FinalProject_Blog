package CS544.Controller;

import CS544.Model.Post;
import CS544.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping("/")
    public List<Post> getAll(){
      return  postService.getAll();

    }



    @PostMapping("/add")
    public Post addPost(@RequestBody Post post){
        postService.save(post);
        return post;
    }

}
