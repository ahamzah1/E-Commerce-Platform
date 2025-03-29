package com.ahmad.notification_service.Repository;

import com.ahmad.notification_service.Entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
