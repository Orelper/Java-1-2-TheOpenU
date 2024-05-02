/**
 * This class represents a Date object
 * 
 * @author Orel Perez
 * @ID 315278341
 * @version (2023a)
 */
public class Date {
    // instance variables
    private int _day;
    private int _month;
    private int _year;
    private final int MAX_DAY = 31, MAX_MONTH = 12, MAX_YEAR = 9999, FEB_LEAP_MAX = 29, REG_MONTH_MAX_DAYS = 30;
    private final int MIN_DAY = 01, MIN_MONTH = 01, MIN_YEAR = 1000, FEB_NO_LEAP_MAX = 28;
    private final int DEFAULT_DAY = 01, DEFAULT_MONTH = 01, DEFAULT_YEAR = 2000, TWO_DIGITS = 10;
    private final int FEBRUARY = 2, APRIL = 4, JUNE = 6, SEPTEMBER = 9, NOVEMBER = 11, DECEMBER = 12;

    // Constructors:

    /**
     * If the given date is valid - creates a new Date object, otherwise creates the
     * date 1/1/2000
     * 
     * @param day   the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year  the year (4 digits)
     */
    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            // if the date is valid
            _day = day;
            _month = month;
            _year = year;
        } else {
            // setting a default date for not valid date
            _day = DEFAULT_DAY;
            _month = DEFAULT_MONTH;
            _year = DEFAULT_YEAR;
        }
    }

    /**
     * Copy constructor
     * 
     * @param other the date to be copied
     */
    public Date(Date other) {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    // Getters:

    /**
     * Gets the day
     * 
     * @return the day
     */
    public int getDay() {
        return _day;
    }

    /**
     * Gets the month
     * 
     * @return the month
     */
    public int getMonth() {
        return _month;
    }

    /**
     * Gets the year
     * 
     * @return the year
     */
    public int getYear() {
        return _year;
    }

    // Setters:

    /**
     * Set the day (only if date remains valid)
     * 
     * @param dayToSet the day value to be set
     */
    public void setDay(int dayToSet) {
        if (isValidDate(dayToSet, _month, _year))
            _day = dayToSet;
    }

    /**
     * Set the month (only if date remains valid)
     * 
     * @param monthToSet the month value to be set
     */
    public void setMonth(int monthToSet) {
        if (isValidDate(_day, monthToSet, _year))
            _month = monthToSet;
    }

    /**
     * Sets the year (only if date remains valid)
     * 
     * @param yearToSet the year value to be set
     */
    public void setYear(int yearToSet) {
        if (isValidDate(_day, _month, yearToSet))
            _year = yearToSet;
    }

    // Methods:

    /**
     * Check if 2 dates are the same
     * 
     * @param other the date to compare this date to
     * @return true if the dates are the same, otherwise false
     */
    public boolean equals(Date other) {
        return (other._day == _day && other._month == _month && other._year == _year);
    }

    /**
     * Check if this date is before other date
     * 
     * @param other date to compare this date to
     * @return true if this date is before other date, otherwise false
     */
    public boolean before(Date other) {
        if (other._year > _year)
            return true;
        else if (other._year == _year && other._month > _month)
            return true;
        else if (other._year == _year && other._month == _month && other._day > _day)
            return true;
        return false;
    }

    /**
     * Check if this date is after other date
     * 
     * @param other other date to compare this date to
     * @return true if this date is after other date, otherwise false
     */
    public boolean after(Date other) {
        return other.before(this);
    }

    /**
     * Calculates the difference in days between two dates
     * 
     * @param other the date to calculate the difference between
     * @return the number of days between the dates (non negative value)
     */
    public int difference(Date other) {
        return Math.abs((calculateDate(_day, _month, _year)) - (calculateDate(other._day, other._month, other._year)));
    }

    /**
     * Returns a String that represents this date
     * 
     * @return String that represents this date in the following format: day (2
     *         digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString() {
        if (_day < TWO_DIGITS || _month < TWO_DIGITS) {
            if (_month >= TWO_DIGITS)
                return "0" + _day + "/" + _month + "/" + _year;
            else if (_day >= TWO_DIGITS)
                return _day + "/0" + _month + "/" + _year;
            return "0" + _day + "/0" + _month + "/" + _year;
        }
        return _day + "/" + _month + "/" + _year;
    }

    /**
     * Calculate the date of tomorrow
     * 
     * @return the date of tomorrow
     */
    public Date tomorrow() {
        if (isValidDate(_day + 1, _month, _year))
            return new Date(_day + 1, _month, _year);
        else {
            if (_month == DECEMBER)
                return new Date(MIN_DAY, MIN_MONTH, _year + 1);
            else
                return new Date(MIN_DAY, _month + 1, _year);
        }
    }

    // Private methods:

    // computes the day number since the beginning of the Christian counting of
    // years
    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    // isValidDate - private method that checks if given date is valid, if do it
    // will return true
    private boolean isValidDate(int day, int month, int year) {
        // isLeapYear - checking if its a leap year.
        boolean isLeapYear = ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);

        if (day < MIN_DAY || day > MAX_DAY || month < MIN_MONTH || month > MAX_MONTH || year < MIN_YEAR || year > MAX_YEAR)
            return false;
        if ((month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) && (day > REG_MONTH_MAX_DAYS))
            return false;
        if (month == FEBRUARY) { // checks if its a valid date on february regular year or leap year
            if (isLeapYear && day > FEB_LEAP_MAX)
                return false;
            if (!isLeapYear && day > FEB_NO_LEAP_MAX)
                return false;
        }
        return true;
    }

}//end of class Date
