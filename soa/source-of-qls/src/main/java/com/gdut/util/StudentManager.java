package com.gdut.util;

import com.gdut.entity.StudentEntity;

import java.util.HashMap;

public class StudentManager {
    // private static HashMap<Integer, StudentEntity> studentEntities = new HashMap<>();

    private static HashMap<String, StudentEntity> studentEntities = new HashMap<>();

    public StudentManager(){}

    /**
     * 获取所有学生信息
     */
    public static HashMap<String, StudentEntity> getAllStudent(){
        return studentEntities;
    }

    /**
     * 添加学生信息
     */
    public void add(String id, String name, String birthday) {

        // 检查学号的正确性
        if (null==id || null==name || null==birthday) {
            return;
        }
        if (studentEntities.containsKey(id)) {
            return;
        }
        StudentEntity stu = new StudentEntity(id, name, birthday);
        studentEntities.put(id, stu);
    }

    /**
     * 根据学号查询学生信息
     */
    public StudentEntity searchById(String id) {
        if (studentEntities.containsKey(id)) {
            return studentEntities.get(id);
        }
       return null;
    }
}