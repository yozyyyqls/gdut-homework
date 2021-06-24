package com.gdut;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gdut.entity.StudentEntity;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        // server.stop(); deprecated method
        server.shutdownNow();
    }

    @Test
    public void testAddStuinfo() throws Exception {
        StudentEntity stuEntity = new StudentEntity("7877", "QiuLS", "1999/11/1");
        JSONObject jsonStuInfo = new JSONObject()
                                .put("id", stuEntity.getId())
                                .put("name", stuEntity.getName())
                                .put("birehday", stuEntity.getBirthday());
        String strStuInfo = jsonStuInfo.toString();
        target.path("student/add-stu-info").request().post(Entity.entity(strStuInfo, MediaType.TEXT_PLAIN));
    }
}
