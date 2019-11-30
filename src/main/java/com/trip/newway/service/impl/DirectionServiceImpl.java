package com.trip.newway.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import com.trip.newway.auth.service.SecurityContextService;
import com.trip.newway.dto.NewDayNewWayResponse;
import com.trip.newway.dto.ResponseDetail;
import com.trip.newway.dto.direction.DirectionDTO;
import com.trip.newway.dto.direction.ResponseDirectionDTO;
import com.trip.newway.dto.direction.SaveDirectionDTO;
import com.trip.newway.dto.direction.SavedDirectionDTO;
import com.trip.newway.dto.email.EmailTripDTO;
import com.trip.newway.exception.WrongOperationException;
import com.trip.newway.model.Direction;
import com.trip.newway.model.TripUsers;
import com.trip.newway.model.User;
import com.trip.newway.repository.DirectionRepository;
import com.trip.newway.repository.TripUsersRepository;
import com.trip.newway.repository.UserRepository;
import com.trip.newway.service.DirectionService;
import com.trip.newway.service.EmailService;
import com.trip.newway.service.impl.email.EmailBuilderImpl;
import com.trip.newway.util.Constants;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
public class DirectionServiceImpl implements DirectionService {

    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripUsersRepository tripUsersRepository;

    @Qualifier("tripReminderBuilder")
    @Autowired
    private EmailBuilderImpl<EmailTripDTO> emailBuilder;

    @Qualifier("cancelledTripBuilder")
    @Autowired
    private EmailBuilderImpl<EmailTripDTO> cancelledBuilder;
    @Autowired
    private EmailService emailService;

    @Autowired
    private SecurityContextService securityContextService;


    @Override
    public NewDayNewWayResponse save(SaveDirectionDTO saveDirectionDTO) {
        Assert.notNull(saveDirectionDTO, "direction is null");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);

        final User currentUser = securityContextService.currentUser();

        // find car
//        if (saveDirectionDTO.getCountPlaces() > car.getPlaces()) {
//            return new NewDayNewWayResponse(new ResponseDetail(409,"car more"));
//        }


        Direction direction = new Direction();
        direction.setCountPlaces(saveDirectionDTO.getCountPlaces());
        direction.setActive(true);
        direction.setDate(LocalDateTime.parse(saveDirectionDTO.getDate(),dateTimeFormatter));
        direction.setEstimateTime(saveDirectionDTO.getEstimateTime());
        direction.setMessage(saveDirectionDTO.getMessage());
        direction.setPlaceIdFrom(saveDirectionDTO.getPlaceIdFrom());
        direction.setPlaceIdTo(saveDirectionDTO.getPlaceIdTo());
        direction.setPrice(saveDirectionDTO.getPrice());
        direction.setStatusId(1);
        direction.setUserId(currentUser.getId());
        direction.setCreatedAt(LocalDateTime.now());
        directionRepository.save(direction);
        return new NewDayNewWayResponse(new ResponseDetail());

    }

    @Override
    public ResponseDirectionDTO findAll(int page) {
        if(page < 0){
            return new ResponseDirectionDTO(new LinkedList<>(), 0);
        }
        List<DirectionDTO> directions = directionRepository
                .findDirections(PageRequest.of(page, Constants.LIMIT)).getContent();

        long count = directionRepository.count();

        return new ResponseDirectionDTO(directions, count);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean applyUser(Long userId, Long id) {
        final Direction direction = directionRepository.findById(id)
                .orElseThrow(()-> new NoSuchEntityException("direction not found with id" + id));
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new WrongOperationException("User not found with id " + userId));

        final TripUsers tripUsers = new TripUsers();
        tripUsers.setDirectionId(direction.getId());
        tripUsers.setUserId(user.getId());

        final TripUsers savedTripUsers = tripUsersRepository.save(tripUsers);

        final EmailTripDTO emailTripDTO = new EmailTripDTO();
        emailTripDTO.setName(user.getName());
        emailTripDTO.setEmail(user.getEmail());
        //emailTripDTO.setDestination(direction.get);

         val message = emailBuilder.buildMessageWithAttachments(emailTripDTO);
        emailService.sendMessage(message);

        return savedTripUsers.getUserId().equals(user.getId());
    }

    public List<DirectionDTO> findWithUserId(Long userId, int page) {
        Assert.notNull(userId, "User id is null");
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new WrongOperationException("User not found with id " + userId));

        return directionRepository.findWithUserId(user.getId(), PageRequest.of(page, Constants.LIMIT));
    }

    @Override
    public boolean cancelTrip(Long id) {
        final Direction direction = directionRepository.findById(id)
                .orElseThrow(()-> new NoSuchEntityException("direction not found with id" + id));
        direction.setActive(false);
        final Direction canceledDirection = directionRepository.saveAndFlush(direction);
        List<EmailTripDTO> emailTripDTOList = tripUsersRepository.findUsersWithTripId(canceledDirection.getId());
        emailTripDTOList.forEach(s -> {
            val message = cancelledBuilder.buildMessageWithAttachments(s);
            emailService.sendMessage(message);
        });
        return true;
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id is null");
        final Direction direction = directionRepository.findById(id)
                .orElseThrow(()-> new NoSuchEntityException("direction not found with id" + id));
        directionRepository.deleteById(direction.getId());
    }

    @Override
    public DirectionDTO findByName(String name) {
        return directionRepository.findByName(name);
    }
}
