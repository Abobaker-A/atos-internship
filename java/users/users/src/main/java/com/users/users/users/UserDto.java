package com.users.users.users;

import com.users.users.roles.Role;
import com.users.users.roles.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private String mobileNumber;
    private AddressDto address;
    private String password;
    private String email;
    private List<Role> roles;

}
