package server;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {
    
    @Test
    void usernameExistsLowercase() {
        String body = """
            {
                "username": "test",
                "password": "Test123"
            }
            """;

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/user")
                .body(body)
                .asJson();

        JsonNode json = response.getBody();
        JSONObject obj = json.getObject();
        assertEquals("Username already exists", obj.getString("error"));
    }

    @Test
    void usernameExistsUppercase() {
        String body = """
            {
                "username": "test",
                "password": "Test123"
            }
            """;

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/user")
                .body(body)
                .asJson();

        JsonNode json = response.getBody();
        JSONObject obj = json.getObject();
        assertEquals("Username already exists", obj.getString("error"));
    }

    @Test
    void postResults() {
        String body = """
                {
                    "category": {
                        "id": 1
                    },
                    "grade": "F",
                    "score": 0,
                    "maxScore": 10
                 }
                """;

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/results/1")
                .body(body)
                .asJson();

        assertEquals(response.getStatus(), 200);
    }

    @Test
    void postResultsFail() {
        String body = """
                {
                    "categoryId": 1
                    "grade": "F",
                    "score": 0,
                    "maxScore": 10
                 }
                """;

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/results/1")
                .body(body)
                .asJson();

        assertEquals(response.getStatus(), 500);
    }
}
