package controller;

import com.mkyong.error.CarNotFoundException;
import model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import repo.CarRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    // get cars
    @GetMapping("cars")
    public List<Car> getCars(){
        System.out.println("GET CALLED");
        return carRepository.findAll();
    }

    // save car
    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.CREATED)
    Car newCar(@RequestBody Car newCar) {
        System.out.println("POST CALLED");
        return carRepository.save(newCar);
    }

    // find and return the car or throw exception
    @GetMapping("/cars/{id}")
    Car findOne(@PathVariable Long id) throws Exception {
        System.out.println("GET CALLED");
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    // Update with given ID or save it
    @PutMapping("/cars/{id}")
    Car saveOrUpdate(@RequestBody Car newCar, @PathVariable Long id) {
        System.out.println("PUT CALLED");
        return carRepository.findById(id)
                .map(x -> {
                    x.setName(newCar.getName());
                    x.setColor(newCar.getColor());
                    x.setManYear(newCar.getManYear());
                    x.setNr(newCar.getNr());

                    return carRepository.save(x);
                })
                .orElseGet(() -> {
                    newCar.setId(id);
                    return carRepository.save(newCar);
                });
    }

    // update author only
    @PatchMapping("/cars/{id}")
    Car patch(@RequestBody String updatedName, @PathVariable Long id) {
        System.out.println("PATCH CALLED");
        return carRepository.findById(id)
                .map(x -> {
                    String name = updatedName;
                    if (!name.isEmpty()) {
                        x.setName(name);

                        return carRepository.save(x);
                    } else {
                        throw new CarUnSupportedFieldPatchException(name);
                    }

                })
                .orElseGet(() -> {
                    throw new CarNotFoundException(id);
                });

    }

    @DeleteMapping("/cars/{id}")
    void deleteCar(@PathVariable Long id) {
        System.out.println("DELETE CALLED");
        if(!carRepository.findById(id).isPresent()) {
            throw new CarNotFoundException(id);
        }
        else {
            carRepository.deleteById(id);
        }
    }

}
