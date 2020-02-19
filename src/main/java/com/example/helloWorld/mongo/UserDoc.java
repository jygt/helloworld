package com.example.helloWorld.mongo;

import javax.persistence.Id;

public class UserDoc {
    @Id
    private String id;
    private String name;
    private String age;
    private String file;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
