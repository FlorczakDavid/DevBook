package co.simplon.devbookapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "role_default", nullable = false)
    private boolean roleDefault;


    @SuppressWarnings("unused")
    public void setId(Long id) {
        // this.id = id; genareted by db
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRoleDefault() {
        return roleDefault;
    }

    public void setRoleDefault(boolean roleDefault) {
        this.roleDefault = roleDefault;
    }

    @Override
    public String toString() {
        return String.format("Role{name='%s'}", name);
    }

}
