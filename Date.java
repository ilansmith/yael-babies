import javax.swing.table.TableColumnModel;

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
    private static final int[] DAYS_IN_MONTH = {
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
        int maxDay;
        Date tomorrow = new Date(this);

        if (_month == FEBRUARY)
            maxDay = isLeapYear(_year) ? 29 : 28;
        else
            maxDay = DAYS_IN_MONTH[_month - 1];

        if (_day < maxDay) {
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
        return new String((_day < 10 ? "0" : "") + _day + "/" +
            (_month < 10 ? "0" : "") + _month + "/" + _year);
    }

    /* Private Methods */
    private void setDefaultDate() {
        _day = DEFAULT_DAY;
        _month = DEFAULT_MONTH;
        _year = DEFAULT_YEAR;
    }

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

    private boolean isValidDate(int day, int month, int year) {
        if (day < 1 || month < JANUARY || DECEMBER < month || year < YEARS_START || YEARS_END < year) {
            return false;
        }

        if (month == FEBRUARY)
            return day <= (isLeapYear(year) ? 29 : 28);

        return day <= DAYS_IN_MONTH[month - 1];
    }

    /* Testing - XXXXX NOT TO BE HANDED IN XXXXX */
    private class TestIsValidDate {
        public int _day;
        public int _month;
        public int _year;
        public boolean _isValid;

        public TestIsValidDate() {
            run();
        }

        private TestIsValidDate(int day, int month, int year, boolean isValid) {
            _day = day;
            _month = month;
            _year = year;
            _isValid = isValid;
        }

        private TestIsValidDate[] getTestCases()
        {
            TestIsValidDate[] testCases = {
                    new TestIsValidDate(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, true),
                    new TestIsValidDate(0, 0, 0, false),
                    new TestIsValidDate(0, DEFAULT_MONTH, DEFAULT_YEAR, false),
                    new TestIsValidDate(DEFAULT_DAY, 0, DEFAULT_YEAR, false),
                    new TestIsValidDate(DEFAULT_DAY, 13, DEFAULT_YEAR, false),
                    new TestIsValidDate(DEFAULT_DAY, DEFAULT_MONTH, YEARS_START - 1, false),
                    new TestIsValidDate(DEFAULT_DAY, DEFAULT_MONTH, YEARS_END + 1, false),
                    new TestIsValidDate(DEFAULT_DAY, DEFAULT_MONTH, YEARS_END + 1, false),
                    new TestIsValidDate(29, 2, 1996, true),
                    new TestIsValidDate(29, 2, 1997, false),
                    new TestIsValidDate(29, 2, 2000, true),
                    new TestIsValidDate(29, 2, 2100, false),
            };

            return testCases;
        }

        private void run() {
            boolean hasErrors = false;
            String retStr;
            TestIsValidDate[] testCases = getTestCases();

            for (int i = 0; i < testCases.length; i++) {
                boolean ret = isValidDate(testCases[i]._day, testCases[i]._month, testCases[i]._year);

                System.out.print("testCases[" + i + "]: isValidDate(" + testCases[i]._day + ", "
                        + testCases[i]._month + ", " +
                        testCases[i]._year + ") = " + (ret ? "true" : "false"));

                if (ret == testCases[i]._isValid) {
                    retStr = "PASS";
                } else {
                    retStr = "FAIL";
                    hasErrors = true;
                }

                System.out.println(" (" + retStr + ")");
            }

            System.out.println("isValidDate(): " + (hasErrors ? "BAD" : "GOOD"));
        }
    }

    private class TestAPI {
        public Date _dateThis;
        public Date _dateOther;
        public int _compare;
        public boolean _resultBool;
        public int _resultInt;

        private static final int EQUALS = 0;
        private static final int BEFORE = 1;
        private static final int AFTER = 2;
        private static final int DIFFERENCE = 3;
        private static final int TOMORROW = 4;

        private static final int MARCH = 3;
        private static final int APRIL = 4;
        private static final int MAY = 5;
        private static final int JUNE = 6;
        private static final int JULY = 7;
        private static final int AUGUST = 8;
        private static final int SEPTEMBER = 9;
        private static final int OCTOBER = 10;
        private static final int NOVEMBER = 11;

        public TestAPI() {
            run();
        }

        private TestAPI(int compare, int thisDay, int thisMonth,
            int thisYear, int otherDay, int otherMonth, int otherYear, boolean result) {
            _dateThis = new Date(thisDay, thisMonth, thisYear);
            _dateOther = new Date(otherDay, otherMonth, otherYear);
            _compare = compare; /* EQUALS, BEFORE, AFTER */
            _resultBool = result;
            _resultInt = -1;
        }

        private TestAPI(int compare, int thisDay, int thisMonth,
            int thisYear, int otherDay, int otherMonth, int otherYear, int result) {
            _dateThis = new Date(thisDay, thisMonth, thisYear);
            _dateOther = new Date(otherDay, otherMonth, otherYear);
            _compare = compare; /* DIFFERENCE */
            _resultBool = false;
            _resultInt = result;
        }

        private TestAPI(int compare, int thisDay, int thisMonth,
            int thisYear, int otherDay, int otherMonth, int otherYear) {
            _dateThis = new Date(thisDay, thisMonth, thisYear);
            _dateOther = new Date(otherDay, otherMonth, otherYear);
            _compare = compare; /* TOMORROW */
            _resultBool = false;
            _resultInt = -1;
        }

        private TestAPI[] getTestCases()
        {
            TestAPI[] testCases = {
                    new TestAPI(EQUALS, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, true),
                    new TestAPI(EQUALS, 29, FEBRUARY, 1600, 29, FEBRUARY, 1600, true),
                    new TestAPI(BEFORE, 29, FEBRUARY, 1600, 29, FEBRUARY, 1600, false),
                    new TestAPI(BEFORE, 1, JANUARY, YEARS_START, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, true),
                    new TestAPI(BEFORE, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, 31, DECEMBER, YEARS_END, true),
                    new TestAPI(AFTER, 29, FEBRUARY, 1600, 29, FEBRUARY, 1600, false),
                    new TestAPI(AFTER, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, 31, DECEMBER, YEARS_END, false),
                    new TestAPI(AFTER, 1, JANUARY, YEARS_START, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, false),
                    new TestAPI(DIFFERENCE, 29, FEBRUARY, 1600, 29, FEBRUARY, 1600, 0),
                    new TestAPI(DIFFERENCE, 1, MARCH, 1600, 28, FEBRUARY, 1600, 2),
                    new TestAPI(TOMORROW, 28, FEBRUARY, 1600, 29, FEBRUARY, 1600),
                    new TestAPI(TOMORROW, 28, FEBRUARY, 1700, 1, MARCH, 1700),
                    new TestAPI(TOMORROW, 29, FEBRUARY, 1600, 1, MARCH, 1600),
                    new TestAPI(TOMORROW, 31, DECEMBER, YEARS_END, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR),
                    new TestAPI(TOMORROW, 31, JANUARY, YEARS_START, 1, FEBRUARY, YEARS_START),
                    new TestAPI(TOMORROW, 28, FEBRUARY, 2025, 1, MARCH, 2025),
                    new TestAPI(TOMORROW, 31, MARCH, DEFAULT_YEAR, 1, APRIL, DEFAULT_YEAR),
                    new TestAPI(TOMORROW, 30, APRIL, YEARS_END, 1, MAY, YEARS_END),
                    new TestAPI(TOMORROW, 31, MAY, DEFAULT_YEAR, 1, JUNE, DEFAULT_YEAR),
                    new TestAPI(TOMORROW, 30, JUNE, DEFAULT_YEAR, 1, JULY, DEFAULT_YEAR),
                    new TestAPI(TOMORROW, 31, JULY, DEFAULT_YEAR, 1, AUGUST, DEFAULT_YEAR),
                    new TestAPI(TOMORROW, 31, AUGUST, DEFAULT_YEAR, 1, SEPTEMBER, DEFAULT_YEAR),
                    new TestAPI(TOMORROW, 30, SEPTEMBER, DEFAULT_YEAR, 1, OCTOBER, DEFAULT_YEAR),
                    new TestAPI(TOMORROW, 31, OCTOBER, DEFAULT_YEAR, 1, NOVEMBER, DEFAULT_YEAR),
                    new TestAPI(TOMORROW, 30, NOVEMBER, DEFAULT_YEAR, 1, DECEMBER, DEFAULT_YEAR),
                    new TestAPI(TOMORROW, 31, DECEMBER, DEFAULT_YEAR, 1, JANUARY, DEFAULT_YEAR + 1),

            };

            return testCases;
        }

        private void run() {
            boolean[] tested = {false, false, false, false, false};
            boolean[] errors = {false, false, false, false, false};
            TestAPI[] testCases = getTestCases();

            for (int i = 0; i < testCases.length; i++) {
                boolean ret;
                boolean retBool = false;
                int retInt = -1;
                Date retDate = null;
                String retStr;
                
                switch (testCases[i]._compare) {
                    case EQUALS:
                        retBool = testCases[i]._dateThis.equals(testCases[i]._dateOther);
                        break;
                    case BEFORE:
                        retBool = testCases[i]._dateThis.before(testCases[i]._dateOther);
                        break;
                    case AFTER:
                        retBool = testCases[i]._dateThis.after(testCases[i]._dateOther);
                        break;
                    case DIFFERENCE:
                        retInt = testCases[i]._dateThis.difference(testCases[i]._dateOther);
                        break;
                    case TOMORROW:
                        retDate = testCases[i]._dateThis.tomorrow();
                        break;
                    default:
                        break;
                }
                
                System.out.print("testCases[" + i + "]: " +
                    "{" + testCases[i]._dateThis.toString() + "}." +
                    functionName(testCases[i]._compare));

                switch (testCases[i]._compare) {
                case EQUALS:
                case BEFORE:
                case AFTER:
                    ret = retBool == testCases[i]._resultBool;
                    retStr = "(" + testCases[i]._dateOther.toString() + ") = " +
                        (retBool ? "true" : "false");
                    break;
                case DIFFERENCE:
                    ret = retInt == testCases[i]._resultInt;
                    retStr = "(" + testCases[i]._dateOther.toString() + ") = " +
                       Integer.toString(retInt); 
                    break;
                case TOMORROW:
                    ret = retDate.equals(testCases[i]._dateOther);
                    retStr = "() = " + retDate.toString();
                    break;
                 default:
                    ret = false;
                    retStr = "() (UNDEFINED)";
                    break;
                }

                if (ret) {
                    retStr += " (PASS)";
                } else {
                    retStr = " (FAIL)";
                    errors[testCases[i]._compare] = true;
                }

                System.out.println(retStr);
                tested[testCases[i]._compare] = true;
            }

            for (int i = 0 ; i < errors.length; i++) {
                if (!tested[i])
                    continue;

                System.out.println(functionName(i) + "(): " + (errors[i] ? "BAD" : "GOOD"));
            }
        }

        private String functionName(int func) {
            String s;

            switch (func) {
                case EQUALS:
                    s = "equals";
                    break;
                case BEFORE:
                    s = "before";
                    break;
                case AFTER:
                    s = "after";
                    break;
                case DIFFERENCE:
                    s = "difference";
                    break;
                case TOMORROW:
                    s = "tomorrow";
                    break;
                default:
                    s = "UNKNOWN";
                    break;
            }

            return s;
        }
    }

    public void test() {
        new TestIsValidDate();
        new TestAPI();
    }
}
