package com.example.lab3pp.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.lang.model.util.Types;
import javax.persistence.*;
import java.util.Set;

@Data
@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Types type;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    public Role(Types type){
        this.type = type;
    }

    public Role() {

    }

    @Override
    public String getAuthority() {
        return type.toString();
    }

    public static enum Types{
        ROLE_ADMIN,
        ROLE_USER
    }

}
