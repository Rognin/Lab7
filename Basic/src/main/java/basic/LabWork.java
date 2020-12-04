package basic;

import java.io.Serializable;
import java.time.LocalDate;

public class LabWork implements Comparable<LabWork>, Serializable {
    /**can't be a null, must be greater than 0, is unique and automatically generated*/
    private Long id;
    /**can't be a null, can't be empty*/
    private String name;
    /**can't be a null*/
    private Coordinates coordinates;
    /**can't be a null, is automatically generated*/
    private LocalDate creationDate;
    /**must be greater than 0*/
    private double minimalPoint;
    /**can't be a null, must be shorter than 843*/
    private String description;
    /**can't be a null*/
    private Difficulty difficulty;
    /**can't be a null*/
    private Person author;

    private String username;


    public LabWork(Long id, String name, Coordinates coordinates, LocalDate creationDate, double minimalPoint, String description, Difficulty difficulty, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.description = description;
        this.difficulty = difficulty;
        this.author = author;
    }

    public LabWork(String name, Coordinates coordinates, double minimalPoint, String description, Difficulty difficulty, Person author) {
        this.id = 0L;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.minimalPoint = minimalPoint;
        this.description = description;
        this.difficulty = difficulty;
        this.author = author;
    }

    public LabWork(Long id, String name, Coordinates coordinates, double minimalPoint, String description, Difficulty difficulty, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.minimalPoint = minimalPoint;
        this.description = description;
        this.difficulty = difficulty;
        this.author = author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public double getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(double minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "LabWork (created by: " + username + ") {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", coordinates = " + coordinates +
                ", creationDate = " + creationDate +
                ", minimalPoint = " + minimalPoint +
                ", description = '" + description + '\'' +
                ", difficulty = " + difficulty +
                ", author = " + author +
                "}";
    }

    /**a comparator based on the MinimalPoint value*/
    @Override
    public int compareTo(LabWork lw) {

        if (this.getMinimalPoint() == lw.getMinimalPoint()) {
            return 0;
        }

        if (this.getMinimalPoint() < lw.getMinimalPoint()) {
            return -1;
        } else {
            return 1;
        }
    }
}