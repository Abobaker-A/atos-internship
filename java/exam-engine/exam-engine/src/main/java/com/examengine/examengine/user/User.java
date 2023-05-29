package com.examengine.examengine.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Field(value = "first_name")
    private String firstName;
    @Field(value = "last_name")
    private String lastName;
    @Field(value = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDate;
    private Gender gender;
    @Field(value = "mobile_number")
    private String mobileNumber;
    private String password;
    private String email;
    private List<Role> roles;
    private Address address;

    public User(String firstName, String lastName, Date birthDate, Gender gender, String mobileNumber, String password, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.email = email;
        this.address = address;
    }
}

