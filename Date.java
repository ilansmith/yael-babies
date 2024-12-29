import javax.swing.table.TableColumnModel;

public class Date {
    /* Members */
    private int _day;
    private int _month;
    private int _year;

    /* Constructors */
    public Date() {
        _day = 1;
        _month = 1;
        _year = 2024;
    }

    public Date(int day, int month, int year) {
        // TBD (requires validation)
    }

    public Date(Date other) {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    /* Getters */
    public int getDay() {
        return _day;
    }

    public int getMonth() {
        return _month;
    }

    public int getYear() {
        return _year;
    }

    /* Setters */
    public void setDay(int dayToSet) {
        // TBD (requires validation)

        _day = dayToSet;
    }

    public void setMonth(int monthToSet) {
        // TBD (requires validation)

        _month = monthToSet;
    }

    public void setYear(int yearToSet) {
        // TBD (requires validation)

        _year = yearToSet;
    }

    /* API Methods */
    public boolean equals(Date other) {
        return other._year == _year &&
                other._month == _month &&
                other._day == _day;
    }

    public boolean before(Date other) {
        return false; // TBD
    }

    public boolean after(Date other) {
        return !equals(other) && !before(other);
    }

    public int difference(Date other) {
        return 0; // TBD
    }

    public Date tomorrow() {
        return null; // TBD
    }

    public String toString() {
        return null; // TBD
    }

    /* Private Methods */
    // computes the day number since the beginning of the Christian counting of years
    private int calculateDaysSinceZero(int day, int month, int year) {
        if (month < 3) {
            year--;
            month += 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    // checks if the year is a leap year
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
