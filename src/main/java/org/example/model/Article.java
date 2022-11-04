package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {
    @JsonProperty("num_comments")
    private Integer num_comments;
    private String title;
    private String author;
    private Integer story_id;
    private String story_title;
    private String story_url;
    private Integer created_at;
    private Integer parent_id;

    public Article() {}

    public void setNum_comments(Integer num_comments) {
        this.num_comments = num_comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStory_id() {
        return story_id;
    }

    public void setStory_id(Integer story_id) {
        this.story_id = story_id;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public String getStory_url() {
        return story_url;
    }

    public void setStory_url(String story_url) {
        this.story_url = story_url;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Integer created_at) {
        this.created_at = created_at;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getNum_comments() {
        if(this.num_comments != null) {
            return this.num_comments;
        } else {
            return 0;
        }
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    public String getTitle() {
        if(title == null) {
            return "";
        } else {
            return title;

        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{" +
                "numComments=" + num_comments +
                ", title='" + title + '\'' +
                '}';
    }
}
