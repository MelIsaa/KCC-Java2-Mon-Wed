package edu.kcc.animal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Animal implements Comparable<Animal>{
    private String id;
    private static List<String> idList = new ArrayList<>();
    private String name;
    private String species;
    private String gender;
    private int age;
    private boolean fixed;
    private int legs;
    private BigDecimal weight;
    private LocalDate dateAdded;
    private LocalDateTime lastFeedingTime;

    public Animal(String id, String name, String species, String gender, int age, boolean fixed,
            int legs, BigDecimal weight, LocalDate dateAdded, 
            LocalDateTime lastFeedingTime){
        setId(id);
        idList.add(id);
        setName(name);
        setSpecies(species);
        setGender(gender);
        setAge(age);
        setFixed(fixed);
        setLegs(legs);
        setWeight(weight);
        setDateAdded(dateAdded);
        setLastFeedingTime(lastFeedingTime);
    }
    
    public Animal() {
        id = "0";
        idList.add(id);
        name = "Unknown";
        species = "Unknown";
        gender = "Unknown";
        age = 0;
        fixed = false;
        legs = 4;
        weight = BigDecimal.valueOf(0);
        dateAdded = LocalDate.of(2020, 9, 1); // September 1, 2020
        lastFeedingTime = LocalDateTime.of(2020, 10, 1, 23, 59); // October 1, 2020 at 11:59pm
    }
    
    public String getId(){
        return id;
    }

    public void setId(String id) {
        idValidator(id);
        this.id = id;
    }
    
    // TODO: Need idValidator method - Only allow it to change if it's "0". Do not allow an id to be set if the id is already in the idList
    private void idValidator(String id) {
        if(this.getId() != null && !this.getId().equals("0")){
            throw new IllegalArgumentException("You can't change an id that has already been set.");
        }
//        else if (idList.contains(id)){
//            throw new IllegalArgumentException("Id already in idList.");
//        }
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies(){
        return species;
    }
    
    public void setSpecies(String species){
        speciesValidator(species);
        this.species = species;
    }
  
    private void speciesValidator(String species) {
        if(this.getSpecies() != null){
            if(!this.getSpecies().equalsIgnoreCase("Unknown")) {
                throw new IllegalArgumentException("The species cannot be changed.");
            }
        }
        else if(!species.equalsIgnoreCase("cat")){
            if(!species.equalsIgnoreCase("dog")) {
                throw new IllegalArgumentException("The species must be either cat or dog.");                
            }
        }
    }
    
    public String getGender(){
        return gender;
    }
    
    public void setGender(String gender) {
        genderValidator(gender);
        this.gender = gender;
    }
    
    // TODO: Need genderValidator method - Only allow male and female. Only allow it to change if it's "Unknown".
    private void genderValidator(String gender) {
        if(this.getGender() == null || this.getGender().equalsIgnoreCase("Unknown")){
            if(gender.equalsIgnoreCase("female")){
                    this.gender = gender;
            }
            else if(gender.equalsIgnoreCase("male")){
                    this.gender = gender;
            }
            else{
                System.out.println("Please enter 'male' or 'female' for "
                                        + "gender.");
            }
        }
        else{
            System.out.println("Gender has already been entered.");
        }
    }
    
    public int getAge(){
        return age;
    }
    
    public void setAge(int animalAge){
        ageValidator(animalAge);
        this.age = animalAge;
    }

    private void ageValidator(int age) {
        if (age > 100 || age < 0) {
            throw new IllegalArgumentException("Invalid Animal age.");
        }
        
    }
    
    public boolean getFixed() {    
        return fixed;
    }

    public void setFixed(boolean fixed) {
        fixedValidator(fixed);
        this.fixed = fixed;
    }
    
    private void fixedValidator(boolean fixed) {
      if((this.fixed == true && fixed == false) || (this.fixed == true && fixed == true)){
            throw new IllegalArgumentException("Animal is already fixed.");
        }
    }
    
    public int getLegs(){
        return legs;
    }
    
    public void setLegs(int legs) {
        legsValidator(legs);
        this.legs = legs;
    }
    
    public void legsValidator(int legs)
    {
        if (legs>4 || legs<0)
        {
            throw new IllegalArgumentException("Legs are only allowed to be between 0 and 4");
        }
    }
    
    public BigDecimal getWeight() {
        return weight;
    }
    
    public void  setWeight(BigDecimal lb){
        weightValidator(lb);
        this.weight = lb;
    }
    
    public void weightValidator(BigDecimal weight) {
        BigDecimal max = new BigDecimal("1000.0");
        BigDecimal min = new BigDecimal("0.0");
        
        if (weight.compareTo(max) == 1 || weight.compareTo(min) == -1) {
            throw new IllegalArgumentException("Invalid weight. Acceptable "
                    + "weight ranges include 0.0-1000.0.");
        }
    }

    public LocalDate getDateAdded(){
        return dateAdded;
    }

    public void setDateAdded(LocalDate ldt){
        dateValidator(ldt);
        this.dateAdded = ldt;
    }
    
    public void dateValidator(LocalDate dateAdded) {
        LocalDate oneWeekAgo = LocalDate.now().minusDays(7);
        if(dateAdded.isBefore(oneWeekAgo)){
            throw new IllegalArgumentException(dateAdded + " is more than"
                    + " one week in the past");
        }
        else if( dateAdded.isAfter(LocalDate.now())){
            throw new IllegalArgumentException(dateAdded + " is a date in the "
                    + "future");
        }
    }
    
    public LocalDateTime getLastFeedingTime(){
        return lastFeedingTime;
    }

    public void setLastFeedingTime(LocalDateTime ldt) {
        feedingValidator(ldt);
        this.lastFeedingTime = ldt;
    }
    
    public void feedingValidator(LocalDateTime ldt){
        LocalDateTime twoDaysAgo = LocalDateTime.now().minusDays(2);
        if(ldt.isBefore(twoDaysAgo)){
            throw new IllegalArgumentException(ldt + " is more than"
                    + " two days in the past");
        } else if (LocalDateTime.now().isBefore(ldt)){
            throw new IllegalArgumentException(ldt + " is in the future.");
        }
    }

    @Override
    public String toString() {
        return "Animal {id: " + id + ", name: " + name + ", species: " + species
                + ", gender: " + gender + ", age: " + age + ", weight: " + weight
                + ", date added: " + dateAdded + ", last fed: " + lastFeedingTime
                + "}";
    }
    
    @Override
    public int compareTo(Animal other) {
        if (this.species.compareTo(other.species) != 0) {
            return this.species.compareTo(other.species);
        }
        else {
            return this.name.compareTo(other.name);
        }
    }
    
}