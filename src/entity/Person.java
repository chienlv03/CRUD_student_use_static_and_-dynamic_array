package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private Long id;
    private String name;
    private LocalDate dob;
    private String address;
    private double height;
    private double weight;

    public Person(Long id, String name, LocalDate dob, String address, double height, double weight) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.height = height;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dobFormatted = dob.format(formatter);
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dobFormatted +
                ", address='" + address + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
