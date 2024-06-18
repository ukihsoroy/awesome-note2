package org.ko.controller;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@Validated
public class BookController {

    @Autowired TransportClient client;

    @GetMapping("/")
    public String index () {
        return "index";
    }

    @GetMapping("get/book/novel")
    @ResponseBody
    public ResponseEntity get (@RequestParam(name = "id", defaultValue = "") @NotBlank(message = "id不可以为空") String id) {
        GetResponse result = client.prepareGet("book", "novel", id).get();
        if (!result.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getSource(), HttpStatus.OK);
    }

    @PostMapping("add/book/novel")
    @ResponseBody
    public ResponseEntity add (
            @RequestParam(name = "title") @NotBlank(message = "标题不可以为空") String title,
            @RequestParam(name = "author") @NotBlank(message = "作者不可以为空") String author,
            @RequestParam(name = "word_count") @NotBlank(message = "字数不可以为空") String wordCount,
            @RequestParam(name = "publish_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate
            ) {

        try {
            XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                    .field("title", title)
                    .field("author", author)
                    .field("word_count", wordCount)
                    .field("publish_date", publishDate).endObject();
            IndexResponse response = client.prepareIndex("book", "novel").setSource(content).get();

            return new ResponseEntity(response.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("delete/book/novel")
    @ResponseBody
    public ResponseEntity delete (@RequestParam("id") String id) {

        DeleteResponse result = client.prepareDelete("book", "novel", id).get();

        return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
    }

    @PutMapping("update/book/novel")
    @ResponseBody
    public ResponseEntity update (
            @RequestParam("id") String id,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author
    ) {
        UpdateRequest update = new UpdateRequest("book", "novel", id);
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();

            if (title != null) {
                builder.field("title", title);
            }
            if (author != null) {
                builder.field("author", author);
            }
            builder.endObject();
            update.doc(builder);
            UpdateResponse result = client.update(update).get();
            return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("query/book/novel")
    @ResponseBody
    public ResponseEntity query (
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "gt_word_count", defaultValue = "0") String gtWordCount,
            @RequestParam(name = "lt_word_count", required = false) String ltWordCount
    ) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (author != null) {
            boolQuery.must(QueryBuilders.matchQuery("author", author));
        }

        if (title != null) {
            boolQuery.must(QueryBuilders.matchQuery("title", title));
        }

        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("word_count").from(gtWordCount);

        if (ltWordCount != null && new Integer(ltWordCount) > 0) {
            rangeQuery.to(ltWordCount);
        }

        SearchRequestBuilder builder = client.prepareSearch("book")
                .setTypes("novel")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(0)
                .setSize(10);

        System.out.println(builder);
        SearchResponse response = builder.get();

        ArrayList<Map<String , Object>> result = new ArrayList<Map<String, Object>>();

        for (SearchHit hit : response.getHits()) {
            result.add(hit.getSource());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
