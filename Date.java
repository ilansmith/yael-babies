public class Date {
    /* Members */
    private int _day;
    private int _month;
    private int _year;

    /* Constants */
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int DECEMBER = 12;
    private static final int YEARS_START = 1000;
    private static final int YEARS_END = 9999;
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_MONTH = 1;
    private static final int DEFAULT_YEAR = 2024;

    /* Constructors */
    public Date() {
        setDefaultDate();
    }

    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            _day = day;
            _month = month;
            _year = year;
        } else {
            setDefaultDate();
        }
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
        if (isValidDate(dayToSet, _month, _year) == false)
            return;

        _day = dayToSet;
    }

    public void setMonth(int monthToSet) {
        if (isValidDate(_day, monthToSet, _year) == false)
            return;

        _month = monthToSet;
    }

    public void setYear(int yearToSet) {
        if (isValidDate(_day, _month, yearToSet) == false)
            return;

        _year = yearToSet;
    }

    /* API Methods */
    public boolean equals(Date other) {
        return other._year == _year &&
                other._month == _month &&
                other._day == _day;

    }

    public boolean before(Date other) {
        if (_year < other._year)
            return true;
        if (other._year < _year)
            return false;

        if (_month < other._month)
            return true;
        if (other._month < _month)
            return false;

        return _day < other._day;
    }

    public boolean after(Date other) {
        return !equals(other) && !before(other);
    }

    public int difference(Date other) {
        int daysThis = calculateDaysSinceZero(_day, _month, _year);
        int daysOther = calculateDaysSinceZero(other._day, other._month, other._year);

        return daysThis < daysOther ?
            daysOther - daysThis :
            daysThis - daysOther;
    }

    public Date tomorrow() {
        Date tomorrow = new Date(this);

        if (_day < getMaxDayOfMonth(_month, _year)) {
            tomorrow._day += 1;
        } else {
            tomorrow._day = 1;

            if (tomorrow._month < DECEMBER) {
                tomorrow._month += 1;
            } else {
                tomorrow._month = JANUARY;

                if (tomorrow._year < YEARS_END)
                    tomorrow._year += 1;
                else
                    tomorrow.setDefaultDate();
            }
        }

        return tomorrow;
    }

    public String toString() {
        String s = (_day < 10 ? "0" : "") + _day + "/" +
            (_month < 10 ? "0" : "") + _month + "/" + _year;

        return s;
    }

    /* Private Methods */
    private void setDefaultDate() {
        _day = DEFAULT_DAY;
        _month = DEFAULT_MONTH;
        _year = DEFAULT_YEAR;
    }

    /* computes the day number since the beginning of the Christian counting of years */
    private static int calculateDaysSinceZero(int day, int month, int year) {
        if (month < 3) {
            year--;
            month += 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    /* checks if the year is a leap year */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /* gets the number of days in the month, taking leap years into account */
    private static int getMaxDayOfMonth(int month, int year) {
        final int[] DAYS_IN_MONTH = {
            31, /* January */
            28, /* February (special case, is 29 in leap-years) */
            31, /* March */
            30, /* April */
            31, /* May */
            30, /* June */
            31, /* July */
            31, /* August */
            30, /* September */
            31, /* October */
            30, /* November */
            31, /* December */
        };

        if (month == FEBRUARY)
            return isLeapYear(year) ? 29 : 28;
        
        return DAYS_IN_MONTH[month - 1];
    }

    /* checks if a day, month, year combination represents a valid date */
    public static boolean isValidDate(int day, int month, int year) {
        if (day < 1 || month < JANUARY || DECEMBER < month || year < YEARS_START || YEARS_END < year) {
            return false;
        }

        return day <= getMaxDayOfMonth(month, year);
    }
}
