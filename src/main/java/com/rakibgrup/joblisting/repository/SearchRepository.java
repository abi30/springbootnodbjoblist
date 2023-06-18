package com.rakibgrup.joblisting.repository;

import com.rakibgrup.joblisting.model.Post;

import java.util.List;

public interface SearchRepository{
    List<Post> searchByText(String text);
    List<Post> newSearchByText(String text,int page,int size);

}
