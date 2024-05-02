/**
 * This class represents a Rent A Car Company.
 * 
 * @version (2023)
 * @author Orel Perez.
 */

public class Company {
    
    private RentNode _head;

    /**
     * Constractor, Creates a new car rental company
     */
    public Company() {
        _head = null;
    }

    private boolean earlierRent(Rent r1, Rent r2) { //Aesthetic and readable method of rentals comparison.
        if (r1.getPickDate().after(r2.getPickDate()))
            return false;

        if (r1.getPickDate().before(r2.getPickDate()))
            return true;

        return r1.howManyDays() > r2.howManyDays();
    }

    /**
     * Boolean method that check for valid rent object and adds it to a company.
     * 
     * @param name       The name of tenant.
     * @param car        The rented car.
     * @param pickDate   The date of rented car pickup.
     * @param returnDate The date of rented car return.
     * @return true if succeeded to add a new rental, and false if not.
     */
    public boolean addRent(String name, Car car, Date pickDate, Date returnDate) {
        Rent additionalRent = new Rent(name, car, pickDate, returnDate);
        RentNode curr = _head;
        while (curr != null) {
            if (curr.getRent().equals(additionalRent)) //same rent
                return false;

            curr = curr.getNext();
        }

        if (_head == null || earlierRent(additionalRent, _head.getRent())) { //empty list / added rent was before.
            _head = new RentNode(additionalRent, _head);
            return true;
        }

        curr = _head;
        while (curr.getNext() != null) { 
            if (earlierRent(additionalRent, curr.getNext().getRent())) { //which next rent was before.
                curr.setNext(new RentNode(additionalRent, curr.getNext()));
                return true;
            }

            curr = curr.getNext();
        }

        curr.setNext(new RentNode(additionalRent));
        return true;
    }

    /**
     * This boolean method gets a date and checks if there is another rent listed
     * with the same return date and removes it from the list.
     * 
     * @param d The date to compare
     * @return True if there was a duplicate and false if there wasn't.
     */
    public boolean removeRent(Date d) {
        if (_head == null)
            return false;

        if (_head.getRent().getReturnDate().equals(d)) {
            _head = _head.getNext();
            return true;
        }
        RentNode prev = null;
        RentNode curr = _head;
        while (curr != null) {
            if (curr.getRent().getReturnDate().equals(d)) {
                prev.setNext(curr.getNext());
                return true;
            }

            prev = curr;
            curr = curr.getNext();
        }
        return false;
    }

    /**
     * This method checks the number of rentals in the company.
     * 
     * @return The number of rentals in the company.
     */
    public int getNumOfRents() {
        RentNode curr = _head;
        int counter = 0;
        while (curr != null) {
            counter++;
            curr = curr.getNext();
        }

        return counter;
    }

    /**
     * This method sums up all the rentals that were in the company.
     * 
     * @return The sum of all rentals.
     */
    public int getSumOfPrices() {
        RentNode curr = _head;
        int sum = 0;
        while (curr != null) {
            sum = sum + curr.getRent().getPrice(); // Sum up
            curr = curr.getNext();
        }

        return sum;
    }

    /**
     * This method sums up all rental days of the entire rental list.
     * 
     * @return The sum of rental days in the entire rental list.
     */
    public int getSumOfDays() {
        RentNode curr = _head;
        int sum = 0;
        while (curr != null) {
            sum = sum + curr.getRent().howManyDays(); // Calculating
            curr = curr.getNext();
        }

        return sum;
    }

    /**
     * This method calculates the average rental days of entire rental list.
     * 
     * @return The average rental days in entire list.
     */
    public double averageRent() {
        if (getNumOfRents() == 0)
            return 0;

        return (double) getSumOfDays() / getNumOfRents(); // else
    }

    /**
     * This method checks the latest return date of rented car.
     * 
     * @return The rent car with latest return date.
     */
    public Car lastCarRent() {
        if (getNumOfRents() == 0)
            return null;

        Rent latestRent = _head.getRent();
        RentNode curr = _head;

        while (curr != null) {
            if (latestRent.getReturnDate().before(curr.getRent().getReturnDate()))
                latestRent = curr.getRent();

            curr = curr.getNext();
        }

        return latestRent.getCar();
    }

    /**
     * This method checks the longest rental period in entire list.
     * 
     * @return The rent with the longest rental period.
     */
    public Rent longestRent() {
        if (getNumOfRents() == 0)
            return null;

        Rent longestRent = _head.getRent();
        RentNode curr = _head;

        while (curr != null) {
            if (longestRent.howManyDays() < curr.getRent().howManyDays())
                longestRent = curr.getRent();

            curr = curr.getNext();
        }

        return longestRent;
    }

    /**
     * This method check what's the most popular rating among the existing ratings
     * in rental list.
     * 
     * @return The most common rating of rental car list.
     */
    public char mostCommonRate() {
        if (getNumOfRents() == 0)
            return 'N';

        RentNode curr = _head;
        int counterA = 0, counterB = 0, counterC = 0, counterD = 0;

        while (curr != null) {
            if (curr.getRent().getCar().getType() == 'A')
                counterA++;
            else if (curr.getRent().getCar().getType() == 'B')
                counterB++;
            else if (curr.getRent().getCar().getType() == 'C')
                counterC++;
            else
                counterD++;

            curr = curr.getNext();
        }

        // If there are rates with the same popularity, the better rate will be the most
        // common one.
        if (counterA == counterB || counterA == counterC || counterA == counterD)
            counterA = -1;

        if (counterB == counterC || counterB == counterD)
            counterB = -1;

        if (counterC == counterD)
            counterC = -1;

        int mostCommonRate = Math.max(Math.max(counterA, counterB), Math.max(counterC, counterD));

        // Return the symbol of most common rating.
        if (counterA == mostCommonRate)
            return 'A';

        if (counterB == mostCommonRate)
            return 'B';

        if (counterC == mostCommonRate)
            return 'C';

        else
            return 'D';
    }

    /**
     * This boolean method receives an additional list of rentals, and checks
     * whether the list received
     * as a parameter to the method is completely contained in the list on which the
     * method was invoked.
     * 
     * @param other The additional we check.
     * @return true if each of the additional rentals in list also appears in the
     *         same way
     *         in the list on which the method was invoked, otherwise return false.
     */
    public boolean includes(Company other) {
        RentNode regNode = _head;
        RentNode otherNode = other._head;

        while (otherNode != null && regNode != null) {
            if (otherNode.getRent().equals(regNode.getRent())) {
                regNode = regNode.getNext();
                otherNode = otherNode.getNext();
            } else
                regNode = regNode.getNext();
        }
        return otherNode == null;
    }

    /**
     * This method receives a list of rentals from type Company,
     * and inserts the whole rentals of it into the list which the method was
     * applied on.
     * 
     * @param nNode The new list.
     * @return The merged list.
     */
    public void merge(Company other) {
        RentNode nNode = other._head;

        while (nNode != null) {
            this.addRent(nNode.getRent().getName(), nNode.getRent().getCar(), nNode.getRent().getPickDate(),
                    nNode.getRent().getReturnDate());
            nNode = nNode.getNext();
        }
    }

    /**
     * The method returns a character string that describes of the rentals in
     * Company list according to the valid format.
     * 
     * @return A character string describing all existing rentals in the company.
     */
    public String toString() {
        RentNode curr = _head;

        if (curr == null)
            return ("The company has 0 rents.");

        String str = "";
        while (curr != null) {
            str = str + curr.getRent().toString() + "\n";
            curr = curr.getNext();
        }
        return ("The company has " + getNumOfRents() + " rents: " + "\n" + str);
    }
}
