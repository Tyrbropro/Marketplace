package com.tyrbropro.order_service.repository;

import com.tyrbropro.order_service.entity.OrderRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRedisRepository extends CrudRepository<OrderRedis, Long> {
}
