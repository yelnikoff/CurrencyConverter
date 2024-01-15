package eu.yelnikoff.curverter.entities.usercurrency;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCurrencyCodeDto {

    @NotBlank
    private String currencyCode;

}
