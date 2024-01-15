package eu.yelnikoff.curverter.entities.usercurrency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class UserCurrencyChangeAmountDto {

    @NotBlank
    private String currencyCode;

    @NotNull
    private Double amount;

}
