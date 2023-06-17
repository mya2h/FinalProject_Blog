package CS544.Controller;

import CS544.Model.Post;
import CS544.Service.PostService;
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
    @GetMapping("/")
    public List<Post> getAll(){
      return  postService.getAll();

    }
    @GetMapping("/{id}")
    public Post get(@PathVariable Long id){
        return postService.get(id);
    }

//    @PostMapping("/add")
//    public RedirectView addPost(@Valid @RequestBody Post post, BindingResult result, RedirectAttributes att){
//
//        if(result.hasErrors()){
//            att.addFlashAttribute("org.springframework.validation.BindingResult.Post", result);
//            att.addFlashAttribute("post", post);
//            return RedirectView();
//
//        }
//        postService.save(post);
//        return new RedirectView("/post/" + post.getId());
//    }
@PostMapping("/add")
public ResponseEntity<?> addPost(@Valid @RequestBody Post post, BindingResult result) {
    if (result.hasErrors()) {
        // Handle validation errors
        List<String> errors = result.getAllErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

   else {
        postService.save(post);
        return ResponseEntity.ok().body(post);
    }
}


    @PutMapping("/add/{id}")
    public ResponseEntity update(@Valid @RequestBody Post post, BindingResult result, @PathVariable Long id){
       if(result.hasErrors()){
           List<String> errors = result.getAllErrors().stream()
                   .map(e -> e.getDefaultMessage())
                   .collect(Collectors.toList());

           return ResponseEntity.badRequest().body(errors);
       }
        postService.update(post);
        return ResponseEntity.ok().body(post);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }
}
