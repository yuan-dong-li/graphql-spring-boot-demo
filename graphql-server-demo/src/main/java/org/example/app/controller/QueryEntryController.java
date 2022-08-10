package org.example.app.controller;

import graphql.GraphQL;
import org.example.app.schema.UserGraphSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class QueryEntryController {
    @Autowired
    private UserGraphSchema userGraphSchema;

    @GetMapping("/graphql/users")
    public Map<String, Object> query(@RequestBody String query) {
        return  (Map<String, Object>)new GraphQL(userGraphSchema.getSchema()).execute(query).getData();
    }
}
