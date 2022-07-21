package web.service;

import org.springframework.stereotype.Service;
import web.models.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private List<Car> cars;

    public CarServiceImpl() {
        cars = new ArrayList<>();
        cars.add(new Car("c1", "f2", "f3"));
        cars.add(new Car("c2", "f2", "f3"));
        cars.add(new Car("c3", "f2", "f3"));
        cars.add(new Car("c4", "f2", "f3"));
        cars.add(new Car("c5", "f2", "f3"));
    }

    @Override
    public List<Car> getCars(int count) {
        return cars.subList(0, Math.min(cars.size(), count));
    }
}
