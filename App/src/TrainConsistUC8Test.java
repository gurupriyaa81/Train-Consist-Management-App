impobort org.junit.Test;



class TrainConsistUC8Test {
    @Test
    List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

    int threshold = 70;

    // Act
    List<Bogie> result = bogies.stream()
            .filter(b -> b.capacity > threshold)
            .collect(Collectors.toList());

    // Assert
    assertEquals(1, result.size());
    assertEquals("Sleeper", result.get(0).name);
    assertTrue(result.get(0).capacity > threshold);
}