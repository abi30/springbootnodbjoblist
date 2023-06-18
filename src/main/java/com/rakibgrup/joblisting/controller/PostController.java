package com.rakibgrup.joblisting.controller;



import com.rakibgrup.joblisting.repository.PostRepository;
import com.rakibgrup.joblisting.model.Post;
import com.rakibgrup.joblisting.repository.SearchRepository;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchRepository searchRepository;
    @Hidden
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse post) throws IOException {
    post.sendRedirect("/swagger-ui/index.html");

    }

    @GetMapping("/posts")
    @CrossOrigin
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

//    @GetMapping("/posts/{text}")
//    @CrossOrigin
//    public List<Post> search(@PathVariable String text){
//    return searchRepository.searchByText(text);
//    }

    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<Post> newSearch(@PathVariable String text,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size){
    return searchRepository.newSearchByText(text,page,size);
    }


    @PostMapping("/post")
    @CrossOrigin
    public Post addPost(@RequestBody Post post){

      return  postRepository.save(post);

    }




    @GetMapping("/posts/post/{id}")
    @CrossOrigin
    public Post getPostById(@PathVariable String id){
      return postRepository.findById(id).orElse(null);
    }

//
//    @GetMapping("/postss/{id}")
//    @CrossOrigin
//    public Optional<Post> getPostById(@PathVariable String id){
//        System.out.println(id);
//      return postRepository.findById(id);
//    }

//    @DeleteMapping("/posts/{id}")
//    @CrossOrigin
//    public void deletePost(@PathVariable String id){
//      postRepository.deleteById(id);
//    }


    @PutMapping("/posts/{id}")
    @CrossOrigin
    public ResponseEntity<Post> updatePost(@PathVariable("id") String id, @RequestBody Post updatedPost) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            // Update the fields of the existing post with the values from the updated post
            existingPost.setProfile(updatedPost.getProfile());
            existingPost.setDesc(updatedPost.getDesc());
            existingPost.setTechs(updatedPost.getTechs());
            existingPost.setExp(updatedPost.getExp());

            Post savedPost = postRepository.save(existingPost);

            return ResponseEntity.ok(savedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
