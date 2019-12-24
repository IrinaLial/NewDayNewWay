package com.trip.newway.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import com.trip.newway.auth.service.SecurityContextService;
import com.trip.newway.dto.NewDayNewWayResponse;
import com.trip.newway.dto.ResponseDetail;
import com.trip.newway.dto.email.EmailTripDTO;
import com.trip.newway.dto.trips.FullTripDTO;
import com.trip.newway.dto.trips.ResponseTripDTO;
import com.trip.newway.dto.trips.SaveTripDTO;
import com.trip.newway.dto.trips.TripDTO;
import com.trip.newway.exception.WrongOperationException;
import com.trip.newway.model.Trip;
import com.trip.newway.model.TripUsers;
import com.trip.newway.model.User;
import com.trip.newway.repository.TripRepository;
import com.trip.newway.repository.TripUsersRepository;
import com.trip.newway.repository.UserRepository;
import com.trip.newway.service.EmailService;
import com.trip.newway.service.TripService;
import com.trip.newway.service.impl.email.EmailBuilderImpl;
import com.trip.newway.util.Constants;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;
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
    public NewDayNewWayResponse save(SaveTripDTO saveTripDTO) {
        Assert.notNull(saveTripDTO, "direction is null");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);

        final User currentUser = securityContextService.currentUser();

        // find car
//        if (saveDirectionDTO.getCountPlaces() > car.getPlaces()) {
//            return new NewDayNewWayResponse(new ResponseDetail(409,"car more"));
//        }

        Trip trip = new Trip();
        trip.setPlaceFromCoordinate(saveTripDTO.getPlaceFromCoordinate());
        trip.setPlaceToCoordinate(saveTripDTO.getPlaceToCoordinate());
        trip.setPlaceFrom(saveTripDTO.getPlaceFromName());
        trip.setPlaceTo(saveTripDTO.getPlaceToName());
        trip.setCountPlaces(saveTripDTO.getCountPlaces());
        trip.setDate(LocalDateTime.parse(saveTripDTO.getDate(), dateTimeFormatter));
        trip.setEstimateTime(saveTripDTO.getEstimateTime());
        trip.setMessage(saveTripDTO.getMessage());
        trip.setPrice(saveTripDTO.getPrice());
        trip.setStatus(Constants.TRIP_OPEN);
        trip.setUserId(currentUser.getId());
        trip.setCreatedAt(LocalDateTime.now());
        tripRepository.save(trip);
        return new NewDayNewWayResponse(new ResponseDetail());
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean applyUser(Long userId, Long id) {
        final Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new NoSuchEntityException("direction not found with id" + id));
        final User user = userRepository.findById(userId)
            .orElseThrow(() -> new WrongOperationException("User not found with id " + userId));

        final TripUsers tripUsers = new TripUsers();
        tripUsers.setTripId(trip.getId());
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

    @Override
    public boolean cancelTrip(Long id) {
        final Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new NoSuchEntityException("trip not found with id" + id));
        trip.setStatus(Constants.TRIP_CANCELED);
        final Trip canceledTrip = tripRepository.saveAndFlush(trip);
        List<EmailTripDTO> emailTripDTOList = tripUsersRepository.findUsersWithTripId(canceledTrip.getId());
        emailTripDTOList.forEach(s -> {
            val message = cancelledBuilder.buildMessageWithAttachments(s);
            emailService.sendMessage(message);
        });
        return true;
    }


    @Override
    public FullTripDTO findWithId(Long id) {
        return tripRepository.findWithId(id);
    }

    public List<TripDTO> findWithUserId(Long userId, int page) {
        Assert.notNull(userId, "User id is null");
        final User user = userRepository.findById(userId)
            .orElseThrow(() -> new WrongOperationException("User not found with id " + userId));

        return tripRepository.findWithUserId(user.getId(), PageRequest.of(page, Constants.LIMIT, Sort.by(Sort.Direction.DESC, Constants.CREATED_AT))).getContent();
    }

    @Override
    public ResponseTripDTO findAll(int page) {
        if (page < 0) {
            return new ResponseTripDTO(new LinkedList<>(), 0);
        }
        List<TripDTO> trips = tripRepository.findActiveTrips(PageRequest.of(page, Constants.LIMIT, Sort.by(Sort.Direction.DESC, Constants.CREATED_AT))).getContent();
        long count = tripRepository.count();
        return new ResponseTripDTO(trips, count);
    }

    @Override
    public NewDayNewWayResponse deleteById(Long id) {
        notNull(id, "id is null");
        final Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new NoSuchEntityException("trip not found with id" + id));
        trip.setStatus(Constants.TRIP_DELETED);
        tripRepository.saveAndFlush(trip);
        return new NewDayNewWayResponse(new ResponseDetail());
    }
}
