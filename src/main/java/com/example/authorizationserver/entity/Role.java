package com.example.authorizationserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permissions",joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")})
    private List<Permission> permissions;

}
