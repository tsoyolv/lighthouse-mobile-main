package ru.lighthouse.mobile.main.service.flat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lighthouse.mobile.main.core.dao.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

import static ru.lighthouse.mobile.main.core.db.DataTypeConstants.MONEY_SCALING;


@Entity
@Table(name = "FLAT_PRICE_HISTORY")
@Getter
@Setter
@NoArgsConstructor
public class FlatPriceHistory implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /**
     * Цена квартиры
     */
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    /**
     * Дата изменения
     */
    @Column(name = "MODIFY_DATE", nullable = false)
    private Date modifyDate;

    /**
     * Url объявления на сайте-источнике
     */
    @Column(name = "URL")
    private String url;

    @ManyToOne
    @JoinColumn(name = "FLAT_ID", nullable = false)
    private Flat flat;

    public interface Columns {
        int PRICE_SCALING = MONEY_SCALING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlatPriceHistory that = (FlatPriceHistory) o;

        return price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return price.hashCode();
    }
}
