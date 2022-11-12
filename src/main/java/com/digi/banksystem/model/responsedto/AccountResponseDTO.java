package com.digi.banksystem.model.responsedto;

import com.digi.banksystem.model.enums.AccountName;
import com.digi.banksystem.model.enums.Currency;
import com.digi.banksystem.model.enums.StatusAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonPropertyOrder({
        "accountName",
        "currency",
        "accountNumber",
        "balance",
        "user"
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {

    @JsonIgnore
    private int id;
    @JsonProperty("accountName")
    private AccountName accountName;
    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("balance")
    private String balance;
    @JsonIgnore
    private StatusAccount status;
    @JsonProperty("user")
    private UserResponseDTO userResponseDTO;


}
