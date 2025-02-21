package com.eazybytes.cards.feignClient;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eazybytes.cards.dto.LoansDto;

import jakarta.validation.constraints.Pattern;

@FeignClient(name="TEAM1-LOAN-SERVICE", path="/api")
public interface LoanDetailsClient {

    @GetMapping("/fetch")
    public Optional<LoansDto> fetchLoanDetails(@RequestParam
                                              @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                              String mobileNumber);

}
