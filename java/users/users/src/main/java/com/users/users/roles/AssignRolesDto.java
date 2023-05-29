package com.users.users.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignRolesDto {
    private ObjectId userId;
    private List<String> roleNames;

}
