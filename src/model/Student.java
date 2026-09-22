package model;

public class Student extends Person {

    private String phone;
    private String address;

    public Student(int id, String name, String email,
                   String phone, String address) {

        super(id, name, email);

        this.phone = phone;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    }
}