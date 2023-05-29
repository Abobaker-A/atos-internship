package com.users.users.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.users.users.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
//TODO implements UserDetails
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(collection = "users")
public class User  {
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
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//       return  this.getRoles().stream()
//                .map(Role::getName)
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
