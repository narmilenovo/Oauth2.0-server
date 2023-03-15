package com.example.authorizationserver.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().forEach(permission -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
            });

        });
        return grantedAuthorities;    }
}
