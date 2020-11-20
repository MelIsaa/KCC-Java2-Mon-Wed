package edu.kcc.animal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static com.gargoylesoftware.htmlunit.html.InputElementFactory.instance;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author k0519415
 */
public class AnimalTest {
    
    private Animal animal;
    private static final String GOOD_NAME = "Snowball";
    private static final String GOOD_ID = "252";
    private static final String GOOD_SPECIES = "cat";
    private static final String GOOD_GENDER = "Female";
    private static final int GOOD_AGE = 1;
    private static final boolean GOOD_FIXED = false;
    private static final int GOOD_LEGS = 4;
    private static final BigDecimal GOOD_WEIGHT = new BigDecimal("12.00");
    private static final LocalDate GOOD_DATE_ADDED = LocalDate.now();
    private static final LocalDateTime GOOD_LAST_FEEDING_TIME = LocalDateTime.now().minusHours(2);
    
    public AnimalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        animal = new Animal(GOOD_ID, GOOD_NAME, GOOD_SPECIES, GOOD_GENDER, 
                GOOD_AGE, GOOD_FIXED, GOOD_LEGS, GOOD_WEIGHT, GOOD_DATE_ADDED, GOOD_LAST_FEEDING_TIME);
    }
    
    @After
    public void tearDown() {
    }

    // TODO
    @Test
    public void testGetId() {
        String expected = GOOD_ID;
        String actual = animal.getId();
        assertEquals(expected, actual);
    }

    // TODO - Set the ID to something not already set
    @Test
    public void testSetId() {
        animal = new Animal();
        animal.setId("2345");
        assertEquals("2345", animal.getId());
    }
    
    // TODO - Create a second Animal object with a unique id. Try to set the first animal's id equal to the second one
    @Test
    public void testSetIdBad() {
        try{
            animal.setId("345");
            fail("Cannot reset animal id once set.");
        } catch(IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetName() {
        String expected = GOOD_NAME;
        String actual = animal.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetName() {
        animal.setName(GOOD_NAME);
        assertEquals(GOOD_NAME, animal.getName());
    }

    // TODO
    @Test
    public void testGetSpecies() {
        String expected = GOOD_SPECIES;
        String actual = animal.getSpecies();
        assertEquals(expected, actual);
    }

    // TODO
    @Test
    public void testSetSpeciesCat() {
        animal = new Animal();
        animal.setSpecies("cat");
        assertEquals("cat", animal.getSpecies());
    }
    
    // TODO
    @Test
    public void testSetSpeciesDog() {
        animal = new Animal();
        animal.setSpecies("dog");
        assertEquals("dog", animal.getSpecies());
    }
    
    // TODO - Attempt to set a non Cat or Dog
    @Test
    public void testSetSpeciesBad() {
        try{
            animal.setSpecies("dolphin");
            fail("Species cannot be set to dolphin");
        } catch(IllegalArgumentException ex) {
            assertTrue(true);
        }
    }
    
    // TODO - Attempt to set a cat to a dog
    @Test
    public void testSetSpeciesBadCatToDog() {
        try{
            animal.setSpecies("cat");
            animal.setSpecies("dog");
            fail("Species cannot be switched once set");
        } catch(IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    // TODO
    @Test
    public void testGetGender(){
        String result = animal.getGender();
        assertEquals(GOOD_GENDER, result);
    }

    // TODO
    @Test
    public void testSetGender() {
        String gender = "Unknown";
        animal.setGender(gender);
        assertEquals(gender, animal.getGender());
    }
    
    // TODO - Attempt to set non male or female
    @Test
    public void testSetGenderBad() {
        String gender = "vegetable";
        String original = animal.getGender();
        try{
            animal.setGender(gender);
        }
        catch (IllegalArgumentException iae){
            assertEquals(original, animal.getGender());
        }
    }
    
     // TODO - Attempt to set a male to female
    @Test
    public void testSetGenderBadMaleToFemale() {
        animal.setGender("male");
        String original = animal.getGender();
                
        try{
            animal.setGender("female");
        }
        catch (IllegalArgumentException iae){
            assertEquals(original, animal.getGender());
        }
    }

    @Test
    public void testGetAge(){
        int result=animal.getAge();
        assertEquals(GOOD_AGE,result);
    }

    // TODO
    @Test
    public void testSetAge() {
        animal.setAge(3);
        assertEquals(3,animal.getAge());
    }
    
    // TODO
    @Test
    public void testSetAgeNegativeBad() {
        assertThrows(IllegalArgumentException.class,
                () -> animal.setAge(-5),
                "Invalid Animal age.");
    }
    
    // TODO
    @Test
    public void testSetAgeOver100Bad() {                
        try{
            animal.setAge(102);
            fail("Animal age cannot be over 100");
        }
        catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }

    @Test
    public void testGetFixed(){
        boolean expected = false;
        boolean actual = animal.getFixed();
        assertEquals(expected, actual);
    }

    // TODO
    @Test
    public void testSetFixed() {
        animal.setFixed(true);
        assertEquals(true, animal.getFixed());
    }

    // TODO - Attempt to set a fixed animal to not fixed
    @Test
    public void testSetFixedTruetoFalse() {
        animal.setFixed(true);
        try{
            animal.setFixed(false);
            fail("A fixed animal cannot be unfixed");
        } catch(IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetLegs(){
        int expected = 4;
        int actual = animal.getLegs();
        assertEquals(expected, actual);
    }

    // TODO
    @Test
    public void testSetLegs() {
        animal.setLegs(4);
        assertEquals(4,animal.getLegs());
    }
    
    // TODO
    @Test
    public void testSetLegsNegativeBad() {
        try{
            animal.setLegs(-1);
            fail("Legs cannot be set to a negative number.");
        } catch(IllegalArgumentException ex) {
            assertTrue(true);
        }
    }
    
    // TODO
    @Test
    public void testSetLegsGreaterThan4Bad() {
        try{
            animal.setLegs(5);
            fail("Legs cannot be greater than four.");
        } catch(IllegalArgumentException ex) {
            assertTrue(true);
        }
    }


    @Test
    public void testGetWeight() {
        BigDecimal result = animal.getWeight();
        assertEquals(GOOD_WEIGHT,result);
    }

    // TODO
    @Test
    public void testSetWeight() {
        BigDecimal newWeight = new BigDecimal("15.00");
        animal.setWeight(newWeight);
        assertEquals(newWeight, animal.getWeight());
    }
    
    // TODO
    @Test
    public void testSetWeightNegativeBad() {
        BigDecimal newWeight = new BigDecimal("-15.00");
        try{
            animal.setWeight(newWeight);
            fail("Cannot set an animal's weight to a negative number.");
        } catch(IllegalArgumentException ex) {
            assertTrue(true);
        }
    }
    
    // TODO
    @Test
    public void testSetWeightAbove1000Bad() {
        BigDecimal newWeight = new BigDecimal("1005.00");
        try{
            animal.setWeight(newWeight);
            fail("Cannot set an animal's weight above 1000.");
        } catch(IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetDateAdded() {
        LocalDate expResult = GOOD_DATE_ADDED; // The default date
        LocalDate result = animal.getDateAdded();
        assertEquals(expResult.toString(), result.toString());
    }

    // TODO
    @Test
    public void testSetDateAdded() {
        animal.setDateAdded(GOOD_DATE_ADDED);
        assertEquals(GOOD_DATE_ADDED, animal.getDateAdded());
    }
    
    @Test
    public void testSetDateAddedMoreThanAWeekAgoBad() {
        LocalDate badDate = LocalDate.now().minusWeeks(2);
        try{
            animal.setDateAdded(badDate);
            fail("The date added may not be longer than a week in the past.");           
        }
        catch(IllegalArgumentException iae){
            assertTrue(true);
        }
    }
    
    @Test
    public void testSetDateAddedFutureDateBad() {
        LocalDate badDate = LocalDate.now().plusDays(1);
        LocalDate original = animal.getDateAdded();
        try{
            animal.setDateAdded(badDate);
            fail("The date added may not be in the future.");           
        }
        catch(IllegalArgumentException iae){
            assertEquals(original, animal.getDateAdded());
        }
    }


    // TODO
    @Test
    public void testGetLastFeedingTime() {
        assertEquals(GOOD_LAST_FEEDING_TIME, animal.getLastFeedingTime());
    }

    // TODO
    @Test
    public void testSetLastFeedingTime() {
        LocalDateTime newFeedingTime = LocalDateTime.now().minusHours(3);
        animal.setLastFeedingTime(newFeedingTime);
        assertEquals(newFeedingTime, animal.getLastFeedingTime());
    }
    
    @Test
    public void testSetLastFeedingTimeMoreThan2DaysAgoBad() {
        LocalDateTime ldtThreeDays = LocalDateTime.now().minusDays(3);
        Animal instance = new Animal();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setLastFeedingTime(ldtThreeDays);
        });

    }
    
    @Test
    public void testSetLastFeedingTimeFutureDateBad() {
        LocalDateTime ldtFutureDate = LocalDateTime.now().plusDays(1);
        Animal instance = new Animal();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setLastFeedingTime(ldtFutureDate);
                });
        
    }


    // TODO
    @Test
    public void testToString() {
        String animalString = "Animal {id: " + GOOD_ID + ", name: " + 
                GOOD_NAME + ", species: " + GOOD_SPECIES
                + ", gender: " + GOOD_GENDER + ", age: " + GOOD_AGE
                + ", weight: " + GOOD_WEIGHT + ", date added: " + 
                GOOD_DATE_ADDED + ", last fed: " + GOOD_LAST_FEEDING_TIME + "}";
        
        assertEquals(animal.toString(), animalString);
        
    }

    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Animal other = new Animal(GOOD_ID,
                GOOD_NAME,
                GOOD_SPECIES,
                GOOD_GENDER,
                GOOD_AGE,
                GOOD_FIXED,
                GOOD_LEGS,
                GOOD_WEIGHT,
                GOOD_DATE_ADDED,
                GOOD_LAST_FEEDING_TIME);
        Animal instance = new Animal(GOOD_ID,
                GOOD_NAME,
                GOOD_SPECIES,
                GOOD_GENDER,
                GOOD_AGE,
                GOOD_FIXED,
                GOOD_LEGS,
                GOOD_WEIGHT,
                GOOD_DATE_ADDED,
                GOOD_LAST_FEEDING_TIME);
        int expResult = 0;
        int result = instance.compareTo(other);
        assertEquals(expResult, result);
    }
    
}
