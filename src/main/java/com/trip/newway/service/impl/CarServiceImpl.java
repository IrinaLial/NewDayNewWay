package com.trip.newway.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import com.trip.newway.auth.service.SecurityContextService;
import com.trip.newway.dto.car.CarDTO;
import com.trip.newway.dto.car.ResponseCarDTO;
import com.trip.newway.dto.car.SavedCarDTO;
import com.trip.newway.exception.WrongOperationException;
import com.trip.newway.model.Car;
import com.trip.newway.model.User;
import com.trip.newway.repository.CarRepository;
import com.trip.newway.repository.UserRepository;
import com.trip.newway.service.CarService;
import com.trip.newway.util.Constants;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityContextService securityContextService;

    @Override
    public CarDTO save(SavedCarDTO carDTO) {
        Assert.notNull(carDTO, "Car is null");
        val user = securityContextService.currentUser();
        Car car = new Car();
        car.setName(carDTO.getName());
        car.setUserId(user.getId());
        car.setCreatedAt(LocalDateTime.now());
        Car savedCar = carRepository.save(car);
        return new CarDTO(savedCar.getId(), savedCar.getName());
    }

    @Override
    public ResponseCarDTO findAll(int page) {
        if(page < 0){
            return new ResponseCarDTO(new LinkedList<>(),0);
        }
        final List<CarDTO> cars = carRepository
                .findCars(PageRequest.of(page, Constants.LIMIT)).getContent();
        final long count = carRepository.count();

        return new ResponseCarDTO(cars, count);
    }

    public List<CarDTO> findWithUserId(Long userId) {
        Assert.notNull(userId, "User id is null");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WrongOperationException("User not found with id " + userId));

        return carRepository.findWithUserId(user.getId());
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id is null");
        final Car car = carRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("car not found with id" + id));
        final User user = securityContextService.currentUser();
        if (!car.getUserId().equals(user.getId())) {
            throw new WrongOperationException("Car can't be deleted");
        }
        carRepository.deleteById(car.getId());
    }
}
