import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TrainConsistUC8Test {

    private List<Bogie> getSampleBogies() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Luxury", 80));
        return bogies;
    }

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        int threshold = 70;

        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());

        assertEquals(2, result.size());
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        int threshold = 60;

        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());

        assertTrue(result.stream().noneMatch(b -> b.capacity == 60));
    }

    @Test
    void testFilter_CapacityLessThanThreshold() {
        int threshold = 70;

        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());

        assertTrue(result.stream().allMatch(b -> b.capacity > 70));
    }

    @Test
    void testFilter_MultipleBogiesMatching() {
        int threshold = 50;

        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());

        assertEquals(3, result.size());
    }

    @Test
    void testFilter_NoBogiesMatching() {
        int threshold = 100;

        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        int threshold = 10;

        List<Bogie> bogies = getSampleBogies();

        List<Bogie> result = bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());

        assertEquals(bogies.size(), result.size());
    }

    @Test
    void testFilter_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();

        List<Bogie> result = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        List<Bogie> bogies = getSampleBogies();
        int originalSize = bogies.size();

        List<Bogie> result = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertEquals(originalSize, bogies.size());
        assertNotSame(bogies, result);
    }
}