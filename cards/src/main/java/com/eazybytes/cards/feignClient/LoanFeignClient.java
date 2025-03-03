package com.eazybytes.cards.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "loans")
public interface LoanFeignClient {
    @GetMapping(value = "/api/getHostName", consumes = "application/json")
    public ResponseEntity<String> getLoanHostName();
}
