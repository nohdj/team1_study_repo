package com.eazybytes.cards.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="TEAM1-LOAN-SERVICE", path="/api")
public interface LoanFeignClient {
    @GetMapping(value = "/getHostName", consumes = "application/json")
    public ResponseEntity<String> getLoanHostName();
}
