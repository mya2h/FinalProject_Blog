package CS544.Controller;

import CS544.Helper.FilterConfiguration;
import CS544.Model.Post;
import CS544.Model.User;
import CS544.Service.PostService;
import CS544.Service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public List<Post> getAll(){
      return  postService.getAll();

    }
    @GetMapping("/{id}")
    public Post get(@PathVariable Long id){
        return postService.get(id);
    }


@PostMapping("/add")
public ResponseEntity<?> addPost(@Valid @RequestBody Post post, BindingResult result, HttpServletRequest request) {
    Claims claims = (Claims) request.getAttribute("claims");
    String username = claims.getSubject();
    User user = userService.findByUserName(username);

     if (result.hasErrors()) {

        List<String> errors = result.getAllErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
     } else {
        post.setAuthor(user);
        postService.save(post);
        return ResponseEntity.ok().body(post);
    }
}

    @PutMapping("/add/{postId}")
    public ResponseEntity<?> updatePost(@Valid @RequestBody Post post, BindingResult result, HttpServletRequest request,
            @PathVariable Long postId  ) {
        Claims claims = (Claims) request.getAttribute("claims");
        String username = claims.getSubject();
        User user = userService.findByUserName(username);
        Post p = postService.get(postId);

        if ( p == null) {
            throw new IllegalArgumentException("Invalid post ID");
        }

        if (result.hasErrors()) {

            List<String> errors = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        } else {

           Post updatedPost =  postService.update(post, postId);
            return ResponseEntity.ok().body(updatedPost);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }
}
