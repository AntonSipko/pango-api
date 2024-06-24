package pango.parking.service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PangoParkingServiceImpl implements PangoParkingService {
    private final Random random;

    @Value("${pango.app.service.probability:35}")
    public int probability;

    @Value("${pango.app.service.secondsAdd:720}") 
    public int secondsAdd;

    @Override
    public Map<Long, String> checkParking(Long carNumber) {
        Map<Long, String> pangoAnswer = new HashMap<>();
        int randomValue = random.nextInt(100);
        System.out.println("Random Value: " + randomValue + ", Probability: " + probability); // Debug statement

        if (randomValue >= probability) {
            int generateNumberOfSeconds = random.nextInt(secondsAdd);
            System.out.println("Generate Number Of Seconds: " + generateNumberOfSeconds); // Debug statement
            String paidParkingTime = LocalTime.now().plusSeconds(generateNumberOfSeconds).toString();
            pangoAnswer.put(carNumber, paidParkingTime);
        } else {
            pangoAnswer.put(carNumber, null);
        }
        return pangoAnswer;
    }
}
