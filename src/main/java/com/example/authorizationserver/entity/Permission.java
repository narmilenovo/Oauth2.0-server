package com.example.authorizationserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permissions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

}
