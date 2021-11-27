package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {

    public Spaceship spaceship = new Spaceship("RAKETA", 5);

    @Before
    public void setInitialSpaceship() {
        spaceship.add(new Astronaut("Georgi Ivanov", 100.0));
        spaceship.add(new Astronaut("Georgi Ivanov1", 200.0));
        spaceship.add(new Astronaut("Georgi Ivanov2", 300.0));
        spaceship.add(new Astronaut("Georgi Ivanov3", 400.0));
    }


//    @Test
//    public void getCount() {
//
//    }

//    @Test
//    public void getName() {
//    }

//    @Test
//    public void getCapacity() {
//    }

    @Test(expected = IllegalArgumentException.class)
    public void reachFullCapacityWhenAddingAstronaut() {
        spaceship.add(new Astronaut("Pesho", 50.0));
        spaceship.add(new Astronaut("Pesho1", 50.0));


    }

    @Test(expected = IllegalArgumentException.class)
    public void checkForDuplicateNameWhenAdding() {
        spaceship.add(new Astronaut("Georgi Ivanov", 100.0));
    }

    @Test
    public void addCorrectAstronaut() {
        spaceship.add(new Astronaut("Pesho", 50.0));
        Assert.assertEquals(5, spaceship.getCount());
    }

    @Test
    public void removeAstronautByName() {
        Assert.assertTrue(spaceship.remove("Georgi Ivanov"));
        Assert.assertEquals(3, spaceship.getCount());
    }

    @Test
    public void doNotRemoveAstronautWhoseNameDoesNotExist() {
        Assert.assertFalse(spaceship.remove("Georgi"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwExceptionIfCapacityIsBelowZero() {
        Spaceship spaceship =  new Spaceship("korab", -5);
    }

    @Test (expected = NullPointerException.class)
    public void throwExceptionIfNameIsNull () {
            Spaceship spaceship =  new Spaceship(null, 5);
    }
    @Test (expected = NullPointerException.class)
    public void throwExceptionIfNameIsEmptySpaces () {
        Spaceship spaceship =  new Spaceship("   ", 5);
    }
}
