package com.trip.newway.service.impl.email;

import com.trip.newway.dto.email.EmailTripDTO;
import org.springframework.stereotype.Service;

@Service
public class TripReminderBuilder extends EmailBuilderImpl<EmailTripDTO> {

    private static final String SUBJECT = "Go Go";

    @Override
    String getMessageRecipient(EmailTripDTO emailTripDTO) {
        return emailTripDTO.getEmail();
    }

    @Override
    String getMessageBody(EmailTripDTO emailTripDTO) {
        return "<html>\n" +
                "<head>\n" +
                "\t<title> Welcome to New Trip</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<center><h1>Your journey get started!</h1></center>\n" +
                "Country: Hungary\n" +
                "City: " + emailTripDTO.getDestination() + "\n" +
                "Team: " + emailTripDTO.getName() + " with his girlfriend\n" +
                "Flight: Wed 26 Jun\n" +
                "<br/><br/>\n" +
                "<font style=\"color:black»>Have a nice trip!</font>\n" +
                "</body>\n" +
                "</html>";
    }

    @Override
    String getMessageSubject() {
        return SUBJECT;
    }
}
