package ru.lighthouse.mobile.main.logic.flat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import ru.lighthouse.mobile.main.mapper.model.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity @Table(name = "FLAT_IMAGE")
@Getter @Setter @NoArgsConstructor @ToString
public class FlatImage implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @ToString.Exclude
    private Long id;

    /** Идентификатор квартиры */
    @Column(name = "FLAT_ID")
    private Long flatId;

    @Lob
    @Column(name="CONTENT")
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] content;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "FILE_PATH")
    private String filePath;
}
