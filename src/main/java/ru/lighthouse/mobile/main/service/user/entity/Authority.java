package ru.lighthouse.mobile.main.service.user.entity;

import lombok.Getter;
import lombok.Setter;
import ru.lighthouse.mobile.main.core.mapper.model.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "AUTHORITY")
@Getter
@Setter
public class Authority implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "AUTHORITY_TO_USER",
            joinColumns = @JoinColumn(name = "AUTHORITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "USERS_ID"))
    private Set<User> users = new HashSet<>();

    @Column(name = "NAME")
    private String name;

    @Column(name = "SYSTEM_NAME")
    private String systemName;
}
