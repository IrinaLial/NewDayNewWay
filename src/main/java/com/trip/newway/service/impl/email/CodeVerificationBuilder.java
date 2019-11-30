package com.trip.newway.service.impl.email;

import com.trip.newway.dto.verification.VerificationCodeDTO;
import org.springframework.stereotype.Service;

@Service
public class CodeVerificationBuilder extends EmailBuilderImpl<VerificationCodeDTO> {

    private static final String SUBJECT = "Verification of email";

    @Override
    String getMessageRecipient(VerificationCodeDTO verificationCodeDTO) {
        return verificationCodeDTO.getEmail();
    }

    @Override
    String getMessageBody(VerificationCodeDTO verificationCodeDTO) {
        return "Your code: " + verificationCodeDTO.getCode();
    }

    @Override
    String getMessageSubject() {
        return SUBJECT;
    }
}
