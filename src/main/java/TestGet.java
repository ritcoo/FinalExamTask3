import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestGet {

    private Gson gson;
    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://swapi.dev/api/people/1";
        gson = new Gson();
    }

    @Test
    public void validateResponseAndGetList() {
        Response response = given()
                .when()
                .get("")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());

        ListDTO dto = gson.fromJson(response.body().asString(), ListDTO.class);

        String expectedName = "Luke Skywalker";
        String expectedHeight = "172";
        String expectedMass = "77";
        String expectedHairColor = "blond";
        String expectedSkinColor = "fair";

        Assertions.assertEquals(expectedName, dto.name);
        Assertions.assertEquals(expectedHeight, dto.height);
        Assertions.assertEquals(expectedMass, dto.mass);
        Assertions.assertEquals(expectedHairColor, dto.hair_color);
        Assertions.assertEquals(expectedSkinColor, dto.skin_color);
        }
    }

