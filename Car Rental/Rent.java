/**
 * This class represents a Rent object
 * 
 * @author Orel Perez
 * @ID 315278341
 * @version (2023a)
 */
public class Rent {
    // instance variables
    private String _name;
    private Car _car;
    private Date _pickDate;
    private Date _returnDate;
    private final int DAYS_IN_WEEK = 7, PRICE_A = 100, PRICE_B = 150, PRICE_C = 180, PRICE_D = 240;
    private final int DISCOUNT_A = 630, DISCOUNT_B = 945, DISCOUNT_C = 1134, DISCOUNT_D = 1512;

    // Constructors:

    /**
     * Creates a new Rent object
     * The return date must be at least one day after the pickup date, otherwise set
     * it to one day after the pick up date.
     * 
     * @param name the client's name
     * @param car  the rented car
     * @param pick the pickup date
     * @param ret  the return date
     */
    public Rent(String name, Car car, Date pick, Date ret) {
        _name = name;
        _car = new Car(car);
        _pickDate = new Date(pick);

        if (ret.before(pick) || ret.equals(pick)) {
            _returnDate = new Date(pick.tomorrow());
        }

        else {
            _returnDate = new Date(ret);
        }
    }

    /**
     * Copy constructor
     * 
     * @param other the rent to be copied
     */
    public Rent(Rent other) {
        _name = other._name;
        _car = new Car(other._car);
        _pickDate = new Date(other._pickDate);
        _returnDate = new Date(other._returnDate);
    }

    // Getters:

    /**
     * Gets the name
     * 
     * @return the name
     */
    public String getName() {
        return _name;
    }

    /**
     * Gets the car
     * 
     * @return the car
     */
    public Car getCar() {
        return new Car(_car);
    }

    /**
     * Gets the pick up date
     * 
     * @return the pick up date
     */
    public Date getPickDate() {
        return new Date(_pickDate);
    }

    /**
     * Gets the retun date
     * 
     * @return the return date
     */
    public Date getReturnDate() {
        return new Date(_returnDate);
    }

    // Setters:

    /**
     * Sets the client name
     * 
     * @param name the client name
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * Sets the rented car
     * 
     * @param car the rented car
     */
    public void setCar(Car car) {
        _car = new Car(car);
    }

    /**
     * Sets the pick up date
     * The pick up date must be at least one day before the return date, otherwise -
     * don't change the pick up date
     * 
     * @param pickDate the pick up date
     */
    public void setPickDate(Date pickDate) {

        if (pickDate.before(_returnDate))
            _pickDate = new Date(pickDate);
    }

    /**
     * Sets the return date
     * The return date must be at least one day after the pick up date, otherwise -
     * don't change the return date
     * 
     * @param returnDate the return date
     */
    public void setReturnDate(Date returnDate) {

        if (returnDate.after(_pickDate))
            _returnDate = new Date(returnDate);
    }

    // Methods:

    /**
     * Check if 2 rents are the same
     * 
     * @param other the rent to compare this rent to
     * @return true if the rents are the same
     */
    public boolean equals(Rent other) {
        return ((_name.equals(other._name)) && (_car.equals(other._car)) &&
                (_pickDate.equals(other._pickDate)) && (_returnDate.equals(other._returnDate)));
    }

    /**
     * Returns the number of rent days
     * 
     * @return the number of rent days
     */
    public int howManyDays() {
        return (_returnDate.difference(_pickDate));
    }

    /**
     * Returns the rent total price
     * 
     * @return the rent total price
     */
    public int getPrice() {
        // variables the represents the initialized price, discounts days, non discount
        // days
        int rentDays = this.howManyDays();
        int discountDays = rentDays / DAYS_IN_WEEK;
        int noDiscountDays = rentDays % DAYS_IN_WEEK;
        int price = 0;

        if (_car.getType() == 'A')
            price = ((noDiscountDays * PRICE_A) + (discountDays * DISCOUNT_A));

        else if (_car.getType() == 'B')
            price = ((noDiscountDays * PRICE_B) + (discountDays * DISCOUNT_B));

        else if (_car.getType() == 'C')
            price = ((noDiscountDays * PRICE_C) + (discountDays * DISCOUNT_C));

        else if (_car.getType() == 'D')
            price = ((noDiscountDays * PRICE_D) + (discountDays * DISCOUNT_D));

        return price;
    }

    /**
     * Try to upgrade the car to a better car
     * If the given car is better than the current car of the rent, upgrade it and
     * return the upgrade additional cost, otherwise - don't upgrade
     * 
     * @param newCar the car to upgrade to
     * @return the upgrade cost
     */
    public int upgrade(Car newCar) {

        if (newCar.better(this._car)) {

            int carBeforeUpgrade = this.getPrice();
            _car = new Car(newCar);
            int upgradePrice = this.getPrice() - carBeforeUpgrade;
            return upgradePrice;
        } else { // return 0 if the new car isn't better
            return 0;
        }

    }

    /**
     * Check if there is a double listing of a rent for the same person and car with
     * an overlap in the rental days
     * If there is - return a new rent object with the unified dates, otherwise -
     * return null.
     * 
     * @param other the other rent
     * @return the unified rent or null
     */
    public Rent overlap(Rent other) { // checking if the name and car are equals

        if (this._name != other._name || !this._car.equals(other._car))
            return null;

        else if (this._name == other._name && this._car.equals(other._car))

            if (this._returnDate.before(other._pickDate) || other._returnDate.before(this._pickDate))
                return null;

            else { // checking which pick date was the first and which return date was the last

                if (this._pickDate.equals(other._pickDate) || this._pickDate.before(other._pickDate))

                    if (this._returnDate.after(other._returnDate))
                        return new Rent(this._name, this._car, this._pickDate, this._returnDate);

                    else
                        return new Rent(this._name, this._car, this._pickDate, other._returnDate);

                else if (this._pickDate.after(other._pickDate))

                    if (this._returnDate.after(other._returnDate))
                        return new Rent(this._name, this._car, other._pickDate, this._returnDate);

                    else
                        return new Rent(this._name, this._car, other._pickDate, other._returnDate);
            }

        return new Rent(this._name, this._car, this._pickDate, this._returnDate);
    }

    /**
     * Returns a String that represents this rent
     * 
     * @return String that represents this rent in the following format:
     *         Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString() {
        return ("Name:" + _name + " From:" + _pickDate + " To:" + _returnDate + " Type:" + _car.getType() + " Days:"
                + howManyDays() + " Price:" + getPrice());
    }

}//end of class Rent
