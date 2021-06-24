package com.gdut.resource;

import java.util.HashMap;
import java.util.Map.Entry;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import com.gdut.entity.*;
import com.gdut.util.StudentManager;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * Root resource (exposed at "myresource" path)
 * 
 * The MyResource class contains implementation of a simple JAX-RS resource.
 */
@Path("student/")
public class StudentResource {
    /**
     * 学生信息管理器
     */
    public StudentManager manager = new StudentManager();

    /**
     * 请求student/时的默认调用方法
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getStudentResource() {
        return "This is Home Page of ResourceofQiuLS.";
    }

    /**
     * 根据ID获取学生信息
     */
    @QueryParam("id")
    String id;

    @GET
    @Path("get-stu-info/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStuInfo() {
        StudentEntity studentEntity = manager.searchById(id);

        return new StringBuilder()
                    .append("Student ID: ")
                    .append(studentEntity.getId())
                    .append('\n')
                    .append("Student Info: ")
                    .append(studentEntity.getName())
                    .append(", ")
                    .append(studentEntity.getBirthday())
                    .toString();
    }

    /**
     * 获取所有学生信息
     * 
     * @return
     */
    @GET
    @Path("get-all-info/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllInfo() {
        HashMap<String, StudentEntity> allStu = StudentManager.getAllStudent();

        StringBuilder builder = new StringBuilder();
        String str = null;
        String id;
        String name;
        String date;
        for (Entry<String, StudentEntity> entry : allStu.entrySet()) {
            id = entry.getValue().getId();
            name = entry.getValue().getName();
            date = entry.getValue().getBirthday();
            str = id + " : " + name + " " + date + '\n';
            builder.append(str);
        }
        return builder.toString();
    }

    /**
     * POST请求命令
     * curl http://localhost:8080/myapp/student/add-stu-info -H "Content-Type: text/plain" -d "{\"id\":\"7877\",\"name\":\"XXX\",\"birthday\":\"2000/1/1\"}"
     */

    /**
     * 增加学生信息
     */
    @POST
    @Path("add-stu-info")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String addStuInfo(String stuEntity) {
        String responseMsg = "Successfully add student info.";

        //将字符转换成JSON格式
        JSONObject jsonEntity = null;
        String id = "";
         try {
             jsonEntity = new JSONObject(stuEntity);
             id = jsonEntity.getString("id");
         } catch (JSONException e) {
             responseMsg = "Error: Your input data is amiss. Please check the format is JSON, or data is null.";
             return responseMsg;
         }

         //查询学生表当中是否有相应的学生信息
        Boolean isNewRecord = ( null == manager.searchById(id) );

        if (isNewRecord) {
            String name = jsonEntity.getString("name");
            String birthday = jsonEntity.getString("birthday");
            manager.add(id, name, birthday);
            return responseMsg;
        } else {
            responseMsg = "This student info already exists.";
            return responseMsg;
        }
    }
}
