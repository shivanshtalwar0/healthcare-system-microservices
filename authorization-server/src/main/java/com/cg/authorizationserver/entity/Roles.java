package com.cg.authorizationserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue()
    Long id;
    Role role;

    public Roles(Role role) {
        this.role = role;
    }

}
