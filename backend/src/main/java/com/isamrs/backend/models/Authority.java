package com.isamrs.backend.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Authority implements GrantedAuthority {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    public Authority(String role) {
        this.name = role;
    }

    @Override
    public String getAuthority() {
        return name;
    }



}
