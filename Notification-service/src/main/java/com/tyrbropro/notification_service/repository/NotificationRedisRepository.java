package com.tyrbropro.notification_service.repository;

import com.tyrbropro.notification_service.entity.NotificationRedis;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRedisRepository extends CrudRepository<NotificationRedis, Long> {
    List<NotificationRedis> findAllByUserIdAndIsReadFalse(Long userId);

}
