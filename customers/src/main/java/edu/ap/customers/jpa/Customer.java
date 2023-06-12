package edu.ap.customers.jpa;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private int age;
    @ElementCollection
    private List<String> projects;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @ElementCollection
    private List<String> paymentMethods;
    @ElementCollection
    private Map<String, String> profileInfo;

    public Customer() {
    }

    public Customer(Long id, String name, String email, String phone, int age, List<String> projects,
                    Address address, List<String> paymentMethods, Map<String, String> profileInfo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.projects = projects;
        this.address = address;
        this.paymentMethods = paymentMethods;
        this.profileInfo = profileInfo;
    }
    public Customer( String name, String email, String phone, int age, List<String> projects,
                    Address address, List<String> paymentMethods, Map<String, String> profileInfo) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.projects = projects;
        this.address = address;
        this.paymentMethods = paymentMethods;
        this.profileInfo = profileInfo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<String> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public Map<String, String> getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(Map<String, String> profileInfo) {
        this.profileInfo = profileInfo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", projects=" + projects +
                ", address=" + address +
                ", paymentMethods=" + paymentMethods +
                ", profileInfo=" + profileInfo +
                '}';
    }
}
