package com.tyrbropro.user_service.repository.redis;

import com.tyrbropro.user_service.entity.UserRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepository extends CrudRepository<UserRedis, Long> {
}
