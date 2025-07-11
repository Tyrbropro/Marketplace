package com.tyrbropro.order_service.client;

import com.tyrbropro.order_service.dto.UserResponseDto;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserClient {

    @GetMapping("/user/{id}")
    UserResponseDto getUserById(@PathVariable("id") @Positive Long id);
}
