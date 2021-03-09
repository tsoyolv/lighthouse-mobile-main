package ru.lighthouse.mobile.main.repository.flat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import ru.lighthouse.mobile.main.core.dao.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FLAT_IMAGE")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlatImage implements EntityModel {
    public FlatImage(Flat flat, String url) {
        this.flat = flat;
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @ToString.Exclude
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FLAT_ID", nullable = false)
    @ToString.Exclude
    private Flat flat;

    @Lob
    @Column(name = "CONTENT")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] content;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "FILE_PATH")
    private String filePath;
}
