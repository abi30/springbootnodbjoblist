package com.rakibgrup.joblisting.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rakibgrup.joblisting.model.Post;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Component
public class SearchRepositoryImpl implements SearchRepository {

    @Autowired
    private MongoClient client;

    @Autowired
    private MongoConverter mongoConverter;
    @Override
    public List<Post> searchByText(String text) {
        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("rakib");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 5L)));

        result.forEach(document -> posts.add(mongoConverter.read(Post.class,document)));
        return posts;
    }

    @Override
    public List<Post> newSearchByText(String text, int page, int size) {
        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("rakib");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$skip", page * size),  // Calculate the number of documents to skip based on the page and size
                new Document("$limit", size)  // Set the maximum number of documents to retrieve
        ));

        result.forEach(document -> posts.add(mongoConverter.read(Post.class, document)));
        return posts;
    }
}
