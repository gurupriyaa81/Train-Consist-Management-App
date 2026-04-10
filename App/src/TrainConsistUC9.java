import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainConsistUC9 {
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

    class TrainConsistUC9Test {

        private List<Bogie> getSampleBogies() {
            List<Bogie> bogies = new ArrayList<>();
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 60));
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("First Class", 24));
            bogies.add(new Bogie("AC Chair", 60));
            return bogies;
        }

        @Test
        void testGrouping_BogiesGroupedByType() {

            Map<String, List<Bogie>> result = getSampleBogies().stream()
                    .collect(Collectors.groupingBy(b -> b.type));

            assertTrue(result.containsKey("Sleeper"));
            assertTrue(result.containsKey("AC Chair"));
        }

        @Test
        void testGrouping_MultipleBogiesInSameGroup() {

            Map<String, List<Bogie>> result = getSampleBogies().stream()
                    .collect(Collectors.groupingBy(b -> b.type));

            assertEquals(2, result.get("Sleeper").size());
        }

        @Test
        void testGrouping_DifferentBogieTypes() {

            Map<String, List<Bogie>> result = getSampleBogies().stream()
                    .collect(Collectors.groupingBy(b -> b.type));

            assertEquals(3, result.keySet().size());
        }

        @Test
        void testGrouping_EmptyBogieList() {

            List<Bogie> bogies = new ArrayList<>();

            Map<String, List<Bogie>> result = bogies.stream()
                    .collect(Collectors.groupingBy(b -> b.type));

            assertTrue(result.isEmpty());
        }

        @Test
        void testGrouping_SingleBogieCategory() {

            List<Bogie> bogies = Arrays.asList(
                    new Bogie("Sleeper", 72),
                    new Bogie("Sleeper", 72)
            );

            Map<String, List<Bogie>> result = bogies.stream()
                    .collect(Collectors.groupingBy(b -> b.type));

            assertEquals(1, result.keySet().size());
            assertEquals(2, result.get("Sleeper").size());
        }

        @Test
        void testGrouping_MapContainsCorrectKeys() {

            Map<String, List<Bogie>> result = getSampleBogies().stream()
                    .collect(Collectors.groupingBy(b -> b.type));

            assertTrue(result.containsKey("Sleeper"));
            assertTrue(result.containsKey("AC Chair"));
            assertTrue(result.containsKey("First Class"));
        }

        @Test
        void testGrouping_GroupSizeValidation() {

            Map<String, List<Bogie>> result = getSampleBogies().stream()
                    .collect(Collectors.groupingBy(b -> b.type));

            assertEquals(2, result.get("Sleeper").size());
            assertEquals(2, result.get("AC Chair").size());
            assertEquals(1, result.get("First Class").size());
        }

        @Test
        void testGrouping_OriginalListUnchanged() {

            List<Bogie> bogies = getSampleBogies();
            int originalSize = bogies.size();

            Map<String, List<Bogie>> result = bogies.stream()
                    .collect(Collectors.groupingBy(b -> b.type));

            assertEquals(originalSize, bogies.size());
            assertNotNull(result);
        }
    }

}