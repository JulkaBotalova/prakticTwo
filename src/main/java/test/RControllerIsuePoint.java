package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.IsuePoint;
import test.repositories.IsuePointRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ControllerAll")
public class RControllerIsuePoint {
    ////////ISUEPOINT////////////

    private final IsuePointRepository isuePointRepository;

    @Autowired
    public RControllerIsuePoint(IsuePointRepository isuePointRepository) {
        this.isuePointRepository = isuePointRepository;
    }

    @GetMapping("/isuepoints")
    Iterable<IsuePoint> getAllIsuePoints() {
        return (List<IsuePoint>) isuePointRepository.findAll();
    }

    @GetMapping("/isuepoints/{ipId}")
    Optional<IsuePoint> getIPId(@PathVariable Integer ipId){
        return isuePointRepository.findById(ipId);
    }


    @GetMapping("/isuepointsCreate")
    IsuePoint createIsuePoint( @RequestParam(name  = "name", defaultValue = "") String name,
                               @RequestParam(name  = "address", defaultValue = "") String address) {

        IsuePoint isuePointCreate = new IsuePoint();
        isuePointCreate.setIsuePointName(name);
        isuePointCreate.setAddress(address);

        return isuePointRepository.save(isuePointCreate);
    }


    @RequestMapping("/isuepointsUp")
    IsuePoint updateIsuePoint( @RequestParam(name = "id", defaultValue = "") Integer id,
                               @RequestParam(name  = "name", defaultValue = "") String name,
                               @RequestParam(name  = "address", defaultValue = "") String address) {
        Optional<IsuePoint> maybeIP = isuePointRepository.findById(id);
        IsuePoint isuePoint = maybeIP
                .orElseThrow(() -> new ExpressionException(String.valueOf(id)));

        if (getIPId(id)==null){
           // createIsuePoint(name, address);
            IsuePoint isuePointCreate = new IsuePoint();
            isuePointCreate.setIsuePointName(name);
            isuePointCreate.setAddress(address);

             return isuePointRepository.save(isuePointCreate);
        }

        isuePoint.setIsuePointName(name);
        isuePoint.setAddress(address);

        return isuePointRepository.save(isuePoint);
    }

    @GetMapping("/isuepointsDel/{ipId}")
    IsuePoint deleteIsuePoint(@PathVariable Integer ipId) throws Exception {
        IsuePoint isuePoint = isuePointRepository.findById(ipId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(ipId)));
        isuePointRepository.delete(isuePoint);
        return isuePoint;
    }
}
