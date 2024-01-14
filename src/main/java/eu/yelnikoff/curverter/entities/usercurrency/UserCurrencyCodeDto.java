package eu.yelnikoff.curverter.entities.usercurrency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class UserCurrencyCodeDto {

    // TODO: add validators
    @NotBlank
    private String currencyCode;

}
