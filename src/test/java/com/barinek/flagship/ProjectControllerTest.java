package com.barinek.flagship;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ProjectControllerTest extends AppRunner {
    @Test
    public void listProjects() throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            String expectedJson = "[{\"id\":1,\"name\":\"Project 1\"},{\"id\":2,\"name\":\"Project 2\"}]";

            HttpGet httpget = new HttpGet("http://localhost:8080/projects");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            assertEquals(expectedJson, responseBody);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }
}
