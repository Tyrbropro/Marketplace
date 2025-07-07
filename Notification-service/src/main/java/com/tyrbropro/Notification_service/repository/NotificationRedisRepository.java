package com.tyrbropro.Notification_service.repository;

import com.tyrbropro.Notification_service.entity.NotificationRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRedisRepository extends CrudRepository<NotificationRedis, Long> {
    Iterable<NotificationRedis> findAllByUserIdAndIsReadFalse(Long userId);

}
