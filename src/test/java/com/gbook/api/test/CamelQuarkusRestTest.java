package com.gbook.api.test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@QuarkusTest
public class CamelQuarkusRestTest {
    private String loadJsonFromFile(String filePath) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        if (is == null) {
            throw new IOException("No se pudo encontrar el fichero: " + filePath);
        }
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }

    @Test
    public void testHealth() {
        given()
                .when().get("/gbook/v1/health")
                .then()
                .statusCode(200);
        System.out.println("test api health OK");
    }

    @Test
    public void testPostBook() throws IOException {

        //InputStream inStream = getClass().getClassLoader().getResourceAsStream("book.json");
        String jsonBody = loadJsonFromFile("book.json");

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when().post("/gbook/v1/book")
                .then()
                .statusCode(202);
        System.out.println("test api POST OK");
    }
}
