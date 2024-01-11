package eu.yelnikoff.curverter.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name="userCurrency", uniqueConstraints = { @UniqueConstraint(columnNames={"userId", "currencyCode"}) })
public class UserCurrency {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private User user;

    @ManyToOne()
    @JoinColumn(name="currencyCode", nullable=false)
    private Currency currency;

    private Integer indexNumber;

}
