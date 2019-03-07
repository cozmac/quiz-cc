package ro.ase.rocket.rocketquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

    private UserType uType;
    private String username;
    private String password;
    private String email;
    private int age;

    public User(UserType uType, String username, String password, String email, int age) {
        this.uType = uType;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public User() {
    }

    public UserType getuType() {
        return uType;
    }

    public void setuType(UserType uType) {
        this.uType = uType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString() {
        return username;

    }

    protected User(Parcel in) {
        uType = (UserType) in.readValue(UserType.class.getClassLoader());
        username = in.readString();
        password = in.readString();
        email = in.readString();
        age = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(uType);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeInt(age);
    }


    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}