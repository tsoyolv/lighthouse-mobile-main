package ru.lighthouse.mobile.logic.user.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.lighthouse.mobile.mapper.model.EntityModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "USERS")
@Getter @Setter @ToString
public class User implements EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="user", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Authority> authorities = new HashSet<>();

    @Column(name="PHONE_NUMBER", length=50, nullable=false, unique=true)
    private String phoneNumber;

    @Column(name="ENABLED", nullable=false)
    private Boolean enabled;

    @Column(name="ACCOUNT_NON_LOCKED", nullable=false)
    private Boolean accountNonLocked;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="SECOND_NAME")
    private String secondName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="BIRTH_DATE")
    private Date birthDate;

    @Column(name="REGISTRATION_DATE")
    private Date registrationDate;

    @Column(name="LAST_LOGIN")
    private Date lastLogin;
}
