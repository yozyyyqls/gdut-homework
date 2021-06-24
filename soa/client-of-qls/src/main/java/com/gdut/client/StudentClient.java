package com.gdut.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.*;

public class StudentClient {
    public static String ROOT = "http://localhost:8080/myapp/";
    public static String URI_GET_ALL_INFO = "student/get-all-info/";
    public static String URI_GET_STU_INFO = "student/get-stu-info?id=";
    public static String URI_ADD_STU_INFO = "student/add-stu-info/";

    public Client client = ClientBuilder.newClient();

    public void testGetAllInfo(){
        WebTarget target = client.target(ROOT+URI_GET_ALL_INFO);
        Response rsp = target.request(MediaType.TEXT_PLAIN_TYPE)
                                .get();
        printMsg(rsp);
    }

    public void testGetStuInfo(String id) {
        WebTarget target = client.target(ROOT+URI_GET_STU_INFO+id);
        Response rsp = target.request(MediaType.TEXT_PLAIN_TYPE)
                            .get();
        printMsg(rsp);
    }

    public void testAddStuInfo(String strEntity) {
        WebTarget target = client.target(ROOT+URI_ADD_STU_INFO);
        Response rsp = target.request(MediaType.TEXT_PLAIN_TYPE)
                            .post(Entity.entity(strEntity, MediaType.TEXT_PLAIN));
        printMsg(rsp);
    }

    private void printMsg(Response rsp){
        System.out.println(rsp.getStatus());
        System.out.println(rsp.readEntity(String.class));
    }

    public static void main(String[] args) {
        //test data
        JSONObject jObject = new JSONObject();
        jObject.put("id", "3218007877")
                .put("name", "QiuLS")
                .put("birthday", "2000/1/1");

        StudentClient client = new StudentClient();
        System.out.println("=======================Test add student info========================");
        client.testAddStuInfo(jObject.toString());
        System.out.println("====================Test get student info by ID=====================");
        client.testGetStuInfo("3218007877");
        System.out.println("=====================Test get all student info======================");
        client.testGetAllInfo();
        System.out.println("====================================================================");
    }

}