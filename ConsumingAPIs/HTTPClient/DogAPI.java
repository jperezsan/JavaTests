package ConsumingAPIs.HTTPClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

// Implementing tutorial from https://zetcode.com/java/httpclient/

public class DogAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Testing Dog API using HTTPClient\n");

        // Instance of an http client
        HttpClient client = HttpClient.newHttpClient();

        // Configuring request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dog.ceo/api/breeds/image/random"))
                .GET()
                .build();

        // Sending request and getting a response. Body is expected to be a String.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("toString: " + response.toString());
        System.out.println("body: " + response.body() + "\n");

        // Reading body output and converting it to JSON using JSON simple
        // https://www.tutorialspoint.com/json/json_java_example.htm
        // https://code.google.com/archive/p/json-simple/
        // https://github.com/fangyidong/json-simple
        // https://www.geeksforgeeks.org/how-to-add-jar-file-to-classpath-in-java/
        // How to run: java -cp C:\Users\{user}\Downloads\json-simple-1.1.jar\json-simple-1.1.jar .\DogAPI.java

        JSONParser parser = new JSONParser();

        try {           
            
            Object obj  = parser.parse(response.body());
            JSONObject jobj = (JSONObject)obj;
            System.out.println(jobj.get("message"));            
            
        } catch(ParseException pe) {
		
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }
    }
}