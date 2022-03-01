package server;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    @Test
    void userStatusCode200() {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/user/1").queryString("format", "json").asJson();
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    void userFound() {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/user/1").queryString("format", "json").asJson();
        assertNotNull(response);

        JsonNode json = response.getBody();
        JSONObject obj = json.getObject();
        assertEquals(1, obj.getInt("id"));
    }

    @Test
    void userNotFound() {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/user/99999").queryString("format", "json").asJson();
        JsonNode json = response.getBody();
        JSONObject obj = json.getObject();
        assertEquals("User ID not found", obj.get("error"));
    }

    @Test
    void invalidUserId() {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/user/123!").queryString("format", "json").asJson();
        assertNotNull(response);

        assertEquals(400, response.getStatus());
    }
}
