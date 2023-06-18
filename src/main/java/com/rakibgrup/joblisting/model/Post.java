package com.rakibgrup.joblisting.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection="JobPost")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    @Id
    private String id;
    private int exp;
    private String profile;
    private String desc;

    private String techs[];

}



