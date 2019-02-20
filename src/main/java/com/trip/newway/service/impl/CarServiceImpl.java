package com.trip.newway.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CarDTO save(SavedCarDTO carDTO) {
        Assert.notNull(carDTO, "Car is null");
        Car car = new Car();
        car.setName(carDTO.getName());

        Car savedCar = carRepository.save(car);
        return new CarDTO(savedCar.getId(), savedCar.getName());
    }

    @Override
    public ResponseCarDTO findAll(int page) {
        List<Car> car = carRepository
                .findAll(PageRequest.of(page, Constants.LIMIT)).getContent();
        List<CarDTO> carDTOS = new LinkedList<>();
        car.forEach(s -> {
            CarDTO carDTO = new CarDTO(s.getId(), s.getName());
            carDTOS.add(carDTO);
        });

        long count = carRepository.count();

        return new ResponseCarDTO(carDTOS, count);
    }

    public List<Car> findByUserId(Long userId) {
        Assert.notNull(userId, "User id is null");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WrongOperationException("User not found with id " + userId));

        return carRepository.findByUserId(user.id);
    }
}
