package com.zwm.springbootstudy.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "myProject")
@ConfigurationProperties(prefix = "project")
public class MyProject {
    private String name;
    private String website;

    public MyProject() {
    }

    public MyProject(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "MyProject{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
