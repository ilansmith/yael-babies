public class Test {
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;
    
    private static final int YEARS_START = 1000;
    private static final int YEARS_END = 9999;
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_MONTH = 1;
    private static final int DEFAULT_YEAR = 2024;

    public static void main(String[] args)
    {
        Test test = new Test();

        test.new TestIsValidDate();
	    test.new TestDate();
    }

    class TestIsValidDate {
        private int _day;
        private int _month;
        private int _year;
        private boolean _isValid;
    
        public TestIsValidDate() {
            run();
        }
    
        private TestIsValidDate(int day, int month, int year, boolean isValid) {
            _day = day;
            _month = month;
            _year = year;
            _isValid = isValid;
        }
    
        private TestIsValidDate[] getTestCases() {
            TestIsValidDate[] testCases = {
                    new TestIsValidDate(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, true),
                    new TestIsValidDate(0, 0, 0, false),
                    new TestIsValidDate(0, DEFAULT_MONTH, DEFAULT_YEAR, false),
                    new TestIsValidDate(DEFAULT_DAY, 0, DEFAULT_YEAR, false),
                    new TestIsValidDate(DEFAULT_DAY, 13, DEFAULT_YEAR, false),
                    new TestIsValidDate(DEFAULT_DAY, DEFAULT_MONTH, YEARS_START - 1, false),
                    new TestIsValidDate(DEFAULT_DAY, DEFAULT_MONTH, YEARS_END + 1, false),
                    new TestIsValidDate(DEFAULT_DAY, DEFAULT_MONTH, YEARS_END + 1, false),
                    new TestIsValidDate(29, FEBRUARY, 1996, true),
                    new TestIsValidDate(29, FEBRUARY, 1997, false),
                    new TestIsValidDate(29, FEBRUARY, 2000, true),
                    new TestIsValidDate(29, FEBRUARY, 2100, false),
            };
    
            return testCases;
        }
    
        private void run() {
            boolean hasErrors = false;
            String output;
            TestIsValidDate[] testCases = getTestCases();
    
            for (int i = 0; i < testCases.length; i++) {
                boolean ret = Date.isValidDate(testCases[i]._day, testCases[i]._month, testCases[i]._year);
    
                output = "testCases[" + i + "]: isValidDate(" + testCases[i]._day + ", "
                        + testCases[i]._month + ", " +
                        testCases[i]._year + ") = " + (ret ? "true" : "false");
    
                if (ret == testCases[i]._isValid) {
                    output += " (PASS)";
                } else {
                    output += " (FAIL)";
                    hasErrors = true;
                }
    
                System.out.println(output);
            }
    
            System.out.println("Date.isValidDate(): " + (hasErrors ? "BAD" : "GOOD"));
        }
    }
    
    class TestDate {
        private Date _dateThis;
        private Date _dateOther;
        private int _compare;
        private boolean _resultBool;
        private int _resultInt;
    
        private static final int EQUALS = 0;
        private static final int BEFORE = 1;
        private static final int AFTER = 2;
        private static final int DIFFERENCE = 3;
        private static final int TOMORROW = 4;
    
        public TestDate() {
            run();
        }
    
        private TestDate(int compare, int thisDay, int thisMonth,
            int thisYear, int otherDay, int otherMonth, int otherYear, boolean result) {
            _dateThis = new Date(thisDay, thisMonth, thisYear);
            _dateOther = new Date(otherDay, otherMonth, otherYear);
            _compare = compare; /* EQUALS, BEFORE, AFTER */
            _resultBool = result;
            _resultInt = -1;
        }
    
        private TestDate(int compare, int thisDay, int thisMonth,
            int thisYear, int otherDay, int otherMonth, int otherYear, int result) {
            _dateThis = new Date(thisDay, thisMonth, thisYear);
            _dateOther = new Date(otherDay, otherMonth, otherYear);
            _compare = compare; /* DIFFERENCE */
            _resultBool = false;
            _resultInt = result;
        }
    
        private TestDate(int compare, int thisDay, int thisMonth,
            int thisYear, int otherDay, int otherMonth, int otherYear) {
            _dateThis = new Date(thisDay, thisMonth, thisYear);
            _dateOther = new Date(otherDay, otherMonth, otherYear);
            _compare = compare; /* TOMORROW */
            _resultBool = false;
            _resultInt = -1;
        }
    
        private TestDate[] getTestCases() {
            TestDate[] testCases = {
                    new TestDate(EQUALS, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, true),
                    new TestDate(EQUALS, 29, FEBRUARY, 1600, 29, FEBRUARY, 1600, true),
                    new TestDate(BEFORE, 29, FEBRUARY, 1600, 29, FEBRUARY, 1600, false),
                    new TestDate(BEFORE, 1, JANUARY, YEARS_START, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, true),
                    new TestDate(BEFORE, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, 31, DECEMBER, YEARS_END, true),
                    new TestDate(AFTER, 29, FEBRUARY, 1600, 29, FEBRUARY, 1600, false),
                    new TestDate(AFTER, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, 31, DECEMBER, YEARS_END, false),
                    new TestDate(AFTER, 1, JANUARY, YEARS_START, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR, false),
                    new TestDate(DIFFERENCE, 29, FEBRUARY, 1600, 29, FEBRUARY, 1600, 0),
                    new TestDate(DIFFERENCE, 1, MARCH, 1600, 28, FEBRUARY, 1600, 2),
                    new TestDate(TOMORROW, 28, FEBRUARY, 1600, 29, FEBRUARY, 1600),
                    new TestDate(TOMORROW, 28, FEBRUARY, 1700, 1, MARCH, 1700),
                    new TestDate(TOMORROW, 29, FEBRUARY, 1600, 1, MARCH, 1600),
                    new TestDate(TOMORROW, 31, DECEMBER, YEARS_END, DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR),
                    new TestDate(TOMORROW, 31, JANUARY, YEARS_START, 1, FEBRUARY, YEARS_START),
                    new TestDate(TOMORROW, 28, FEBRUARY, 2025, 1, MARCH, 2025),
                    new TestDate(TOMORROW, 31, MARCH, DEFAULT_YEAR, 1, APRIL, DEFAULT_YEAR),
                    new TestDate(TOMORROW, 30, APRIL, YEARS_END, 1, MAY, YEARS_END),
                    new TestDate(TOMORROW, 31, MAY, DEFAULT_YEAR, 1, JUNE, DEFAULT_YEAR),
                    new TestDate(TOMORROW, 30, JUNE, DEFAULT_YEAR, 1, JULY, DEFAULT_YEAR),
                    new TestDate(TOMORROW, 31, JULY, DEFAULT_YEAR, 1, AUGUST, DEFAULT_YEAR),
                    new TestDate(TOMORROW, 31, AUGUST, DEFAULT_YEAR, 1, SEPTEMBER, DEFAULT_YEAR),
                    new TestDate(TOMORROW, 30, SEPTEMBER, DEFAULT_YEAR, 1, OCTOBER, DEFAULT_YEAR),
                    new TestDate(TOMORROW, 31, OCTOBER, DEFAULT_YEAR, 1, NOVEMBER, DEFAULT_YEAR),
                    new TestDate(TOMORROW, 30, NOVEMBER, DEFAULT_YEAR, 1, DECEMBER, DEFAULT_YEAR),
                    new TestDate(TOMORROW, 31, DECEMBER, DEFAULT_YEAR, 1, JANUARY, DEFAULT_YEAR + 1),
            };
    
            return testCases;
        }
    
        private void run() {
            boolean[] tested = {false, false, false, false, false};
            boolean[] errors = {false, false, false, false, false};
            TestDate[] testCases = getTestCases();
    
            for (int i = 0; i < testCases.length; i++) {
                boolean ret;
                boolean retBool = false;
                int retInt = -1;
                Date retDate = null;
                String output;
                
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
                
                output = "testCases[" + i + "]: " +
                    "{" + testCases[i]._dateThis.toString() + "}." +
                    functionName(testCases[i]._compare);
    
                switch (testCases[i]._compare) {
                case EQUALS:
                case BEFORE:
                case AFTER:
                    ret = retBool == testCases[i]._resultBool;
                    output += "(" + testCases[i]._dateOther.toString() + ") = " +
                        (retBool ? "true" : "false");
                    break;
                case DIFFERENCE:
                    ret = retInt == testCases[i]._resultInt;
                    output += "(" + testCases[i]._dateOther.toString() + ") = " +
                       Integer.toString(retInt); 
                    break;
                case TOMORROW:
                    ret = retDate.equals(testCases[i]._dateOther);
                    output += "() = " + retDate.toString();
                    break;
                 default:
                    ret = false;
                    output += "() (UNDEFINED)";
                    break;
                }
    
                if (ret) {
                    output += " (PASS)";
                } else {
                    output += " (FAIL)";
                    errors[testCases[i]._compare] = true;
                }
    
                System.out.println(output);
                tested[testCases[i]._compare] = true;
            }
    
            for (int i = 0 ; i < errors.length; i++) {
                if (!tested[i])
                    continue;
    
                System.out.println("Date." + functionName(i) + "(): " + (errors[i] ? "BAD" : "GOOD"));
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
}
