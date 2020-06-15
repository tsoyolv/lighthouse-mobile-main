package ru.lighthouse.mobile.logic.user.entity;

import lombok.Getter;
import lombok.Setter;
import ru.lighthouse.mobile.mapper.model.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity @Table(name = "AUTHORITY")
@Getter @Setter
public class Authority implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private User user;

    @Column(name="NAME")
    private String name;

    @Column(name="SYSTEM_NAME")
    private String systemName;
}
