package com.visa.vttp.server.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Contact {
    private String name;
    private String email;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Contact [email=" + email + ", mobile=" + mobile + ", name=" + name + "]";
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", getName())
                .add("email", getEmail())
                .add("mobile", getMobile())
                .build();
    }

    public static Contact create(SqlRowSet rs) {
        Contact c = new Contact();
        c.setName(rs.getString("name"));
        c.setMobile(rs.getString("mobile"));
        c.setEmail(rs.getString("email"));
        return c;
    }

    public static Contact create(JsonObject reqBody) {
        Contact c = new Contact();
        c.setName(reqBody.getString("name"));
        c.setMobile(reqBody.getString("mobile"));
        c.setEmail(reqBody.getString("email"));
        return c;
    }

}
