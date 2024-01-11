package eu.yelnikoff.curverter.entities.currency;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
@Table(name="currency")
public class Currency {

    @Id
    @Column(length=3)
    private String code;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String countryName;

}
