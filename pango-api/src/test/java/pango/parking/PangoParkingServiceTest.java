package pango.parking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import pango.parking.service.PangoParkingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PangoParkingServiceTest {

    @InjectMocks
    private PangoParkingServiceImpl pangoParkingService;

    @Mock
    private Random random;

    @BeforeEach
    void setUp() {
        pangoParkingService.probability = 20;
        pangoParkingService.secondsAdd = 720;
    }

    
    @Test
    void test1_CheckParkingWhenRandomNumberIsGreaterThanOrEqualToProbability() {
        Long carNumber = 123456L;
        when(random.nextInt(100)).thenReturn(40); 
        when(random.nextInt(pangoParkingService.secondsAdd)).thenReturn(300);

        LocalTime expectedTime = LocalTime.now().plusSeconds(300);

        Map<Long, String> result = pangoParkingService.checkParking(carNumber);

        assertNotNull(result.get(carNumber), "Test 1 failed: Expected non-null result");
        assertEquals(expectedTime.toString(), result.get(carNumber), "Test 1 failed: Expected time mismatch");
    }

    @Test
    void test2_CheckParkingWhenRandomNumberIsLessThanProbability() {
        
        Long carNumber = 1234567L;
        when(random.nextInt(100)).thenReturn(5); 

        Map<Long, String> result = pangoParkingService.checkParking(carNumber);

        assertNull(result.get(carNumber), "Test 2 failed: Expected null result");
    }
}
