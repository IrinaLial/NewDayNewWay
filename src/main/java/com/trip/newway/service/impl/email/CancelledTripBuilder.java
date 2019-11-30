package com.trip.newway.service.impl.email;


import com.trip.newway.dto.email.EmailTripDTO;
import org.springframework.stereotype.Service;

@Service
public class CancelledTripBuilder extends EmailBuilderImpl<EmailTripDTO> {

    @Override
    String getMessageRecipient(EmailTripDTO emailTripDTO) {
        return emailTripDTO.getEmail();
    }

    @Override
    String getMessageBody(EmailTripDTO emailTripDTO) {
        return "Dear " + emailTripDTO.getName() + " your trip "
                + emailTripDTO.getDestination() + " is cancelled";
    }

    @Override
    String getMessageSubject() {
        return "Cancelled trip(";
    }
}