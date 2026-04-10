import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TrainConsistUC10Test {

    private List<Bogie> getSampleBogies() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));
        return bogies;
    }

    @Test
    void testReduce_TotalSeatCalculation() {

        int total = getSampleBogies().stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(156, total);
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {

        int total = getSampleBogies().stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertTrue(total > 0);
        assertEquals(72 + 60 + 24, total);
    }

    @Test
    void testReduce_SingleBogieCapacity() {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72)
        );

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(72, total);
    }

    @Test
    void testReduce_EmptyBogieList() {

        List<Bogie> bogies = new ArrayList<>();

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(0, total);
    }

    @Test
    void testReduce_CorrectCapacityExtraction() {

        List<Bogie> bogies = getSampleBogies();

        List<Integer> capacities = bogies.stream()
                .map(b -> b.capacity)
                .collect(Collectors.toList());

        assertTrue(capacities.contains(72));
        assertTrue(capacities.contains(60));
        assertTrue(capacities.contains(24));
    }

    @Test
    void testReduce_AllBogiesIncluded() {

        List<Bogie> bogies = getSampleBogies();

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        int expected = bogies.get(0).capacity +
                bogies.get(1).capacity +
                bogies.get(2).capacity;

        assertEquals(expected, total);
    }

    @Test
    void testReduce_OriginalListUnchanged() {

        List<Bogie> bogies = getSampleBogies();
        int originalSize = bogies.size();

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(originalSize, bogies.size());
        assertTrue(total > 0);
    }
}