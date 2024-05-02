/**
 * This class represents a Car object
 * 
 * @author Orel Perez
 * @ID 315278341
 * @version (2023a)
 */
public class Car {
    // instance variables
    private int _id;
    private char _type;
    private String _brand;
    private boolean _isManual;
    private final int DEFAULT_ID = 9999999, MIN_LENGTH = 999999, MAX_LENGTH = 10000000;
    private final char DEAFULT_TYPE = 'A';

    // Constructors:

    /**
     * Creates a new Car object
     * id should be a 7 digits number, otherwise set it to 9999999
     * type should be 'A','B','C' or 'D', otherwise set it to 'A'
     * 
     * @param id       the id of the car (7 digits number)
     * @param type     the type of the car ('A','B','C' or 'D')
     * @param brand    the car's brand
     * @param isManual flag indicating if the car is manual
     */
    public Car(int id, char type, String brand, boolean isManual) {
        // checks if the id is valid
        if (id > MIN_LENGTH && id < MAX_LENGTH) {
            _id = id;
        } else {
            _id = DEFAULT_ID;
        }
        // checks if the type is valid
        if (type == 'A' || type == 'B' || type == 'C' || type == 'D') {
            _type = type;
        } else {
            _type = DEAFULT_TYPE;
        }

        _brand = brand;
        _isManual = isManual;
    }

    /**
     * Copy constructor
     * 
     * @param other the car to be copied
     */
    public Car(Car other) {
        _id = other._id;
        _type = other._type;
        _brand = other._brand;
        _isManual = other._isManual;
    }

    // Getters:

    /**
     * Gets the id
     * 
     * @return the id
     */
    public int getId() {
        return _id;
    }

    /**
     * Gets the type
     * 
     * @return the type
     */
    public char getType() {
        return _type;
    }

    /**
     * Gets the brand
     * 
     * @return the brand
     */
    public String getBrand() {
        return _brand;
    }

    /**
     * Gets the isManual flag
     * 
     * @return the isManual flag
     */
    public boolean isManual() {
        return _isManual;
    }

    // Setters:

    /**
     * Sets the id (only if the given id is valid)
     * 
     * @param id the id value to be set
     */
    public void setId(int id) {
        // checks if the id is valid again
        if (id > MIN_LENGTH && id < MAX_LENGTH)
            _id = id;
    }

    /**
     * Sets the type (only if the given type is valid)
     * 
     * @param type the type value to be set
     */
    public void setType(char type) {
        // checks if the type is valid again
        if (type == 'A' || type == 'B' || type == 'C' || type == 'D')
            _type = type;
    }

    /**
     * Sets the brand of the car
     * 
     * @param brand the brand value to be set
     */
    public void setBrand(String brand) {
        _brand = brand;
    }

    /**
     * Sets the isManual flag of the car
     * 
     * @param isManual the isManual flag to be set
     */
    public void setIsManual(boolean isManual) {
        _isManual = isManual;
    }

    // Methods:

    /**
     * Returns a String object that represents this car
     * 
     * @return String that represents this car in the following format:
     *         id:1234567 type:B brand:Toyota gear:manual (or auto)
     */
    public String toString() {
        return ("id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + (this.isManual() ? "manual" : "auto"));
    }

    /**
     * Check if two cars are the same
     * Cars are considered the same if they have the same type, brand and gear
     * 
     * @param other the car to compare this car to
     * @return true if the cars are the same, otherwise false
     */
    public boolean equals(Car other) {
        return _type == other._type && _brand.equals(other._brand) && _isManual == other._isManual;
    }

    /**
     * Check if this car is better than the other car
     * A car is considered better than another car if its type is higher.
     * If both cars have the same type, an automated car is better than a manual
     * car.
     * 
     * @param other car to compare this car to
     * @return true if this car is better than the other car, otherwise false
     */
    public boolean better(Car other) {
        return (_type > other._type) || (_type == other._type && _isManual == false && other._isManual == true);
    }

    /**
     * Check if this car is worse than the other car
     * 
     * @param other car to compare this car to
     * @return true if this car is worse than the other car, otherwise false
     */
    public boolean worse(Car other) {
        return other.better(this);
    }

}// end of class Car