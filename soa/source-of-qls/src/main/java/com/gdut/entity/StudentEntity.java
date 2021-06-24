package com.gdut.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "STU")
@NamedQueries({
        @NamedQuery(name = "StudentEntity.findByStuID", query = "SELECT u FROM StudentEntity u WHERE u.stuid = :stuid") })
public class StudentEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STUID")
    private String id;

    @Column(name = "NAME")
    private String name = "";

    @Column(name = "BIRTHDAY")
    private Date birthday;

    public StudentEntity() {}

    public StudentEntity(String id, String name, String birthday) {
        this.id = id;
        this.name = name;
        try {
            this.birthday = new SimpleDateFormat("yyyy/MM/dd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday.toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.birthday = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null) ? this.id.hashCode() : 0;
        return hash;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        try {
            object.put("stuid", this.id);
            object.put("name", this.name);
            object.put("birthday", this.birthday.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StudentEntity) {
            StudentEntity stuObj = (StudentEntity) obj;
            if (stuObj.getId().equals(this.id)) {
                return true;
            }
        }
        return false;
    }
}