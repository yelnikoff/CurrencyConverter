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

    // TODO: add validators
    @NotBlank
    private String currencyCode;

    // TODO: add validators
    @NotNull
    private Double amount;

}
