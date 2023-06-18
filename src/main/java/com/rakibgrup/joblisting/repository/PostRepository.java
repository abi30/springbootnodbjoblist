package com.rakibgrup.joblisting.repository;

import com.rakibgrup.joblisting.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post,String> {
//    List<Post> searchByText(String text);
}
