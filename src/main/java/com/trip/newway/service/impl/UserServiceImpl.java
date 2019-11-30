package com.trip.newway.service.impl;

import com.trip.newway.dto.user.ResponseUserDTO;
import com.trip.newway.dto.user.SaveUsersDTO;
import com.trip.newway.dto.user.UserDTO;
import com.trip.newway.dto.verification.VerificationCodeDTO;
import com.trip.newway.dto.verification.VerificationPasswordDTO;
import com.trip.newway.exception.EmailNotUniqueException;
import com.trip.newway.exception.NoSuchEntityException;
import com.trip.newway.exception.WrongOperationException;
import com.trip.newway.model.PasswordRecovery;
import com.trip.newway.model.Role;
import com.trip.newway.model.User;
import com.trip.newway.model.UserCode;
import com.trip.newway.repository.PasswordRecoveryRepository;
import com.trip.newway.repository.RoleRepository;
import com.trip.newway.repository.UserCodeRepository;
import com.trip.newway.repository.UserRepository;
import com.trip.newway.service.EmailService;
import com.trip.newway.service.UserService;
import com.trip.newway.service.impl.email.EmailBuilderImpl;
import com.trip.newway.util.Constants;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.springframework.util.Assert.notNull;

@Transactional
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserCodeRepository userCodeRepository;

    @Autowired
    private PasswordRecoveryRepository passwordRecoveryRepository;

    @Qualifier("registrationBuilder")
    @Autowired
    private EmailBuilderImpl<User> emailBuilder;
    @Autowired
    private EmailService emailService;

    @Qualifier("codeVerificationBuilder")
    @Autowired
    private EmailBuilderImpl<VerificationCodeDTO> codeVerificationBuilder;

    @Qualifier("passwordRecoveryBuilder")
    @Autowired
    private EmailBuilderImpl<VerificationPasswordDTO> passwordRecoveryBuilder;

    @Override
    public boolean sendCode(String email) {
        Assert.notNull(email, "email is null");
        val presentByEmail = userRepository.findByEmail(email);
        if (presentByEmail != null) {
            throw new EmailNotUniqueException("email is already exist");
        }

        val userCode = userCodeRepository.findByEmail(email).orElse(new UserCode());
        final int code = getCode();
        userCode.setEmail(email);
        userCode.setCode(code);
        userCodeRepository.saveAndFlush(userCode);
        val verificationCodeDTO = new VerificationCodeDTO(email,code);
        final MimeMessage message = codeVerificationBuilder.buildMessageWithAttachments(verificationCodeDTO);
        emailService.sendMessage(message);
        return true;
    }


    @Override
    public UserDTO save(SaveUsersDTO userDTO) {
        Assert.notNull(userDTO, "user is null");

        val presentByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (presentByEmail != null) {
            throw new EmailNotUniqueException("email is already exist");
        }

       final UserCode userCode = userCodeRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new NoSuchEntityException("no request found"));
        if (userCode.getCode() != userDTO.getCode()) {
            throw new WrongOperationException("code is wrong");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        val passwordEncoder = new BCryptPasswordEncoder();
        final String encodePassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodePassword);
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        final Role role = roleRepository.findByName(Constants.ROLE_USER);
        user.setRole(role);
        user.setLevelId(1L);
        user.setActive(false);
        user.setStatusId(Constants.EMPTY);

        final User savedUser = userRepository.save(user);

        val message = emailBuilder.buildMessageWithAttachments(savedUser);
        emailService.sendMessage(message);

        return new UserDTO(savedUser.getId(), savedUser.getName(),
                savedUser.getEmail(), savedUser.isActive());
    }

    @Override
    public boolean emailConfirmation(String email) {
        Assert.notNull(email, "email is null");
        val presentByEmail = userRepository.findByEmail(email);
        if (presentByEmail == null) {
            throw new EmailNotUniqueException("email is not exist");
        }

        val passwordRecovery = passwordRecoveryRepository.findByEmail(email).orElse(new PasswordRecovery());
        final int recoveryCode = getCode();
        passwordRecovery.setEmail(email);
        passwordRecovery.setCode(recoveryCode);
        passwordRecoveryRepository.saveAndFlush(passwordRecovery);
        val verificationPasswordDTO = new VerificationPasswordDTO(email,recoveryCode);
        final MimeMessage message = passwordRecoveryBuilder.buildMessageWithAttachments(verificationPasswordDTO);
        emailService.sendMessage(message);
        return true;
    }

    @Override
    public boolean emailCodeConfirm(String email, int code) {
        val passwordRecovery = passwordRecoveryRepository.findByEmail(email).orElse(new PasswordRecovery());
        return passwordRecovery.getCode()==code;
    }

    @Override
    public boolean passwordConfirm(String email, int code, String password) {
        val passwordRecovery = passwordRecoveryRepository.findByEmail(email).orElse(new PasswordRecovery());
        if(passwordRecovery.getCode()!= code){
            return false;
        }
        User user = userRepository.findByEmail(email);
        val passwordEncoder = new BCryptPasswordEncoder();
        final String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
        userRepository.saveAndFlush(user);
        return true;
    }


    @Override
    public ResponseUserDTO findAll(int page) {
        if (page < 0) {
            return new ResponseUserDTO(new LinkedList<>(), 0);
        }
        List<UserDTO> users = userRepository
                .findUsers(PageRequest.of(page, Constants.LIMIT)).getContent();
        long count = userRepository.count();

        return new ResponseUserDTO(users, count);
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id is null");
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("User no found with id: " + id));
        userRepository.deleteById(user.getId());
    }

    private int getCode() {
        val random = new Random();
        return 111111 + random.nextInt(888888);
    }
}
