package org.example.app;

import org.example.app.controller.QueryEntryController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserQueryTests {
    @Autowired
    private QueryEntryController queryEntryController;

    @Test
    public void userQueryTests() {
        Map<String, Object> query1Result = queryEntryController.query("{users(page:2,size:5,name:\"john\") {id,sex,name,pic}}");
        System.out.println(query1Result);

        Map<String, Object> query2Result = queryEntryController.query("{user(id:6) {id,sex,name,pic}}");
        System.out.println(query2Result);

        Map<String, Object> query3Result = queryEntryController.query("{user(id:6) {id,sex,name,pic},users(page:2,size:5,name:\"john\") {id,sex,name,pic}}");
        System.out.println(query3Result);
    }
}
