package eu.yelnikoff.curverter.entities.usercurrency;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import eu.yelnikoff.curverter.entities.user.User;
import eu.yelnikoff.curverter.entities.currency.Currency;

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
