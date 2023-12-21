package org.example.enity;

/**
 * @author hongchuzhen
 * @date 12/20/2023
 */
public class Person {
    String id;
    String name;
    String city;
    String age;

    public Person() {
    }

    public Person(String id, String name, String city, String age) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
