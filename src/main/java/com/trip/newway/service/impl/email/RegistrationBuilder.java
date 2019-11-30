package com.trip.newway.service.impl.email;

import com.trip.newway.model.User;
import org.springframework.stereotype.Service;



@Service
public class RegistrationBuilder extends EmailBuilderImpl<User> {

    private static final String SUBJECT = "Welcome to New way";

    @Override
    String getMessageRecipient(User user) {
        return user.getEmail();
    }

    @Override
    String getMessageBody(User user) {
        return "<html>\n" +
                "<head>\n" +
                "\t<title> Welcome to New Trip</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<center><h1>Your journey get started!</h1></center>\n" +
                "Country: Hungary\n" +
                "City: Budapest\n" +
                "Team: " + user.getName() + " with his girlfriend\n" +
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
