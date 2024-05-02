/**
 * This class represent a RentNode.
 * 
 * @version (2023)
 * @author Orel Perez.
 */

public class RentNode {
    private Rent _rent;
    private RentNode _next;

    /**
     * A constructor of class RentNode.
     * 
     * @param r Rent element.
     */
    public RentNode(Rent r) {
        _rent = new Rent(r);
        _next = null;
    }

    /**
     * A constuctor of class RentNode.
     * 
     * @param r    Rent element.
     * @param next Pointing the next rent element.
     */
    public RentNode(Rent r, RentNode next) {
        this._rent = new Rent(r);
        this._next = next;
    }

    /**
     * A copy constuctor of the class RentNode.
     * 
     * @param RentNode Other rental.
     */
    public RentNode(RentNode other) {
        _rent = other._rent;
        _next = other._next;
    }

    /**
     * A method that returns the rental.
     * 
     * @return Rent.
     */
    public Rent getRent() {
        return new Rent(_rent);
    }

    /**
     * A method that points the next rental.
     * 
     * @return Next rental.
     */
    public RentNode getNext() {
        return _next;
    }

    /**
     * A method that set a rental with new attributes
     * 
     * @param r Rent to set.
     */
    public void setRent(Rent r) {
        _rent = new Rent(r);
    }

    /**
     * A method that gets new pointer and update it to the next element.
     * 
     * @param next Rent to update.
     */
    public void setNext(RentNode next) {
        _next = next;
    }
}