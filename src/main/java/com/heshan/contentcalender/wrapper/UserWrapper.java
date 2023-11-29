package com.heshan.contentcalender.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper {

    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
    private String status;
    private String password;


    public UserWrapper(int id, String contactNumber, String email,String name,String password,String status) {
        this.id = id;
        this.contactNumber = contactNumber;
        this.email = email;
        this.name = name;
        this.password = password;
        this.status = status;
    }


}
