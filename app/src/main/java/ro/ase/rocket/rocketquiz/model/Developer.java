package ro.ase.rocket.rocketquiz.model;

import android.graphics.Bitmap;

public class Developer {

    private Bitmap image;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;

    public Developer(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Developer(Bitmap image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public Developer()
    {

    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    public String toString2()
    {
        return "name='" + name + '\'' +
                ", phone='" + phoneNumber + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
