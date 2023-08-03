package com.dws.challenge.service;

//Whenever a transfer is made, a notification should be sent to both account holders, with a message
//containing id of the other account and amount transferred.
public interface NotificationService {
    void sendNotification(Long accountId, String message);
}
