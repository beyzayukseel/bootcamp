package org.com.thy.bootcamp.service;

import org.com.thy.bootcamp.exception.ServiceOperationException;
import org.com.thy.bootcamp.model.ExchangeDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public record ExchangeModule(RestTemplate restTemplate) {

    public ResponseEntity<ExchangeDto> getExchange(String currency) {
        String api = "https://api.apilayer.com/exchangerates_data/latest?symbols=EUR,USD,TRY&base=" + currency;
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "8noh00RR5f6JUYtw2LC4t9NM4I33THGI");
        HttpEntity<String> httpEntity = new HttpEntity<>("apikey", headers);
        ResponseEntity<ExchangeDto> exchangeModelResponseEntity = restTemplate.exchange(api,
                HttpMethod.GET, httpEntity, ExchangeDto.class);
        ExchangeDto exchangeDto = exchangeModelResponseEntity.getBody();
        return ResponseEntity.ok(exchangeDto);
    }

    public static BigDecimal exchangeValueByCurrency(String currency) {

        if (currency.equals("USD")) {
            return BigDecimal.valueOf(1.0);
        }
        if (currency.equals("TRY")) {
            return BigDecimal.valueOf(0.1);
        }
        if (currency.equals("EUR")) {
            return BigDecimal.valueOf(0.1);
        }
        else {
            throw new ServiceOperationException.NotFoundException("Currency Not Found!");
        }
    }
}
