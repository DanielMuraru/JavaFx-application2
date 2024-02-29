package com.example.ofertev.domain;

import com.example.ofertev.utils.Hobbies;

public class Client {
    private Long clientId;
    private String name;
    private int fidelityGrade;
    private int age;
    private Hobbies hobbies;

    public Client(Long clientId, String name, int fidelityGrade, int age, Hobbies hobbies) {
        this.clientId = clientId;
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.age = age;
        this.hobbies = hobbies;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public int getFidelityGrade() {
        return fidelityGrade;
    }

    public int getAge() {
        return age;
    }

    public Hobbies getHobbies() {
        return hobbies;
    }
}
