package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Article;
import org.example.rest.RestHelpers;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int limit = 3; // default
        System.out.println("args: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("arg " + i + ": " + args[i]);
        }
        if(args.length > 0) {
            limit = Integer.parseInt(args[0]);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            List<Article> articleList = new ArrayList<>();
            String rawPage = RestHelpers.getPageRaw(1);
            JsonNode rootNode = mapper.readTree(rawPage);
            Integer numPages = rootNode.get("total_pages").asInt();

            articleList.addAll(parseArticlesFromPage(rawPage));

            // TODO improve speed: write service to get multiple pages in parallel, and collect results into single list of Articles
            for(int i = 2; i <= numPages; i++) {
                String page = RestHelpers.getPageRaw(i);
                articleList.addAll(parseArticlesFromPage(page));
            }

            articleList = articleList.stream()
                    .sorted(Comparator.comparing(Article::getNum_comments).reversed())
                    .collect(Collectors.toList());

            // if numComments are equal, sort alphabetically. This requires rewriting the whole list with a bubble sort type of thing
            for(int a =0; a < articleList.size(); a++) {
                for(int b =1; b < (articleList.size()-2); b++) {
                    if(articleList.get(a).getNum_comments() == articleList.get(b).getNum_comments()) { // only care about titles if numComments are equal
                        if(articleList.get(a).getTitle().compareTo(articleList.get(b).getTitle()) > 0) { // the title of the next Article should come before the currently preceding article
                            Article temp = articleList.get(b);
                            Article temp2 = articleList.get(a);
                            articleList.set(a, temp);
                            articleList.set(b, temp2);
                        }
                    }
                }
            }

            List<Article> subList = articleList.subList(0,limit); // top N articles, where N is the limit sorted by number of comments
            System.out.println("limit articles to top: " + limit + " by most number of comments. ");
            System.out.printf(subList.toString());

        } catch(Exception e) {
            System.out.println("!! error: " + e);
        }

    }
    public static List<Article> parseArticlesFromPage(String pageData) throws JsonProcessingException {
        List<Article> articleList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode rootNode = mapper.readTree(pageData);
        JsonNode dataArray = rootNode.get("data");
        Iterator<JsonNode> articles = dataArray.iterator();
        articles.forEachRemaining(article -> articleList.add(mapper.convertValue(article, Article.class)));
        return articleList;
    }
}