package com.example.bankcards.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    public Role() {}
    public Role(String name) { this.name = name; }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role r = (Role) o;
        return Objects.equals(id, r.id) && Objects.equals(name, r.name);
    }
    @Override
    public int hashCode() { return Objects.hash(id, name); }
    @Override
    public String toString() { return "Role{id="+id+", name='"+name+"'}"; }
}