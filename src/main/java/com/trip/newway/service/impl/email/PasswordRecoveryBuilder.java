package com.trip.newway.service.impl.email;

import com.trip.newway.dto.verification.VerificationPasswordDTO;
import org.springframework.stereotype.Service;

@Service
public class PasswordRecoveryBuilder extends EmailBuilderImpl<VerificationPasswordDTO> {

    private static final String SUBJECT = "Password recovery";

    @Override
    String getMessageRecipient(VerificationPasswordDTO verificationPasswordDTO) {
        return verificationPasswordDTO.getEmail();
    }

    @Override
    String getMessageBody(VerificationPasswordDTO verificationPasswordDTO) {
        return "You requested recovery password and you can get your code " + verificationPasswordDTO.getCode();
    }

    @Override
    String getMessageSubject() {
        return SUBJECT;
    }
}
