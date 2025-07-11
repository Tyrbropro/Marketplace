package com.tyrbropro.notification_service.repository;

import com.tyrbropro.notification_service.entity.Notification;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    List<Notification> findAllByUserIdAndIsReadFalse(Long userId);
}
