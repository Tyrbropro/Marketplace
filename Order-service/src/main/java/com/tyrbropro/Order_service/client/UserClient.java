package com.tyrbropro.Order_service.client;

import com.tyrbropro.Order_service.dto.UserResponseDTO;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserClient {

    @GetMapping("/user/{id}")
    UserResponseDTO getUserById(@PathVariable("id") @Positive Long id);
}
