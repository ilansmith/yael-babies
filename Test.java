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
	    test.new TestWeight();
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

    private class TestWeight {
        private Weight _weightThis;
        private Weight _weightOther;
        private int _gramsAdd;
        private String _resStr;
        private boolean _resBool;
        private int _resInt;
        private int _type;

        private static final int TO_STRING = 0;
        private static final int EQUALS = 1;
        private static final int LIGHTER = 2;
        private static final int HEAVIER = 3;
        private static final int ADD = 4;

        public TestWeight() {
            run();
        }

        private TestWeight(int type, int kilos, int grams, String string) {
            _weightThis = new Weight(kilos, grams);
            _weightOther = null;
            _gramsAdd = 0;
            _resStr = string;
            _resBool = false;
            _resInt = -1;
            _type = type;
        }

        private TestWeight(int type, int kilosThis, int gramsThis, int kilosOther, int gramsOther, boolean result) {
            _weightThis = new Weight(kilosThis, gramsThis);
            _weightOther = new Weight(kilosOther, gramsOther);
            _gramsAdd = 0;
            _resStr = null;
            _resBool = result;
            _resInt = -1;
            _type = type;
        }

        private TestWeight(int type, int gramsThis, int kilosOther, int gramsOther, boolean result) {
            _weightThis = new Weight(gramsThis);
            _weightOther = new Weight(kilosOther, gramsOther);
            _gramsAdd = 0;
            _resStr = null;
            _resBool = result;
            _resInt = -1;
            _type = type;
        }

        private TestWeight(int type, int kilosThis, int gramsThis, int gramsAdd, int kilosOther, int gramsOther) {
            _weightThis = new Weight(kilosThis, gramsThis);
            _weightOther = new Weight(kilosOther, gramsOther);
            _gramsAdd = gramsAdd;
            _resStr = null;
            _resBool = false;
            _resInt = -1;
            _type = type;
        }

        private TestWeight[] getUseCases() {
            TestWeight[] useCases = {
                new TestWeight(TO_STRING, 3, 55, "3.055"),
                new TestWeight(TO_STRING, 4, 5, "4.005"),
                new TestWeight(TO_STRING, 4, 70, "4.07"),
                new TestWeight(TO_STRING, 5, 48, "5.048"),
                new TestWeight(TO_STRING, 3, 200, "3.2"),
                new TestWeight(TO_STRING, 7, 938, "7.938"),
                new TestWeight(TO_STRING, 1, 0, "1.0"),
                new TestWeight(EQUALS, 1, 0, 1, 0, true),
                new TestWeight(EQUALS, 1, 5, 2, 5, false),
                new TestWeight(EQUALS, 1, 2, 1, 7, false),
                new TestWeight(EQUALS, 5, 103, 5, 103, true),
                new TestWeight(EQUALS, 2, 0, 1, 1000, true),
                new TestWeight(EQUALS, 2, 10, 1, 1010, true),
                new TestWeight(EQUALS, -1, 10, 1, 0, true),
                new TestWeight(EQUALS, 1, -10, 1, 0, true),
                new TestWeight(LIGHTER, 5, 103, 5, 103, false),
                new TestWeight(LIGHTER, 5, 0, 4, 999, false),
                new TestWeight(LIGHTER, 4, 999, 5, 0, true),
                new TestWeight(LIGHTER, 4, 234, 2, 2234, false),
                new TestWeight(LIGHTER, 4, 234, 3, 1230, false),
                new TestWeight(LIGHTER, 4, 234, 2, 2235, true),
                new TestWeight(LIGHTER, -100, 1, 0, false),
                new TestWeight(HEAVIER, 5103, 5, 103, false),
                new TestWeight(HEAVIER, 5000, 4, 999, true),
                new TestWeight(HEAVIER, 4999, 5, 0, false),
                new TestWeight(HEAVIER, 4234, 2, 2234, false),
                new TestWeight(HEAVIER, 4234, 3, 1230, true),
                new TestWeight(HEAVIER, 4234, 2, 2235, false),
                new TestWeight(HEAVIER, -100, 1, 0, false),
                new TestWeight(ADD, 3, 150, 900, 4, 50),
                new TestWeight(ADD, 3, 150, -250, 2, 900),
                new TestWeight(ADD, 3, 150, -3250, 3, 150),
                new TestWeight(ADD, 3, 150, -3000, 3, 150),
            };

            return useCases;
        }

        private void run() {
            boolean[] tested = {false, false, false, false, false};
            boolean[] errors = {false, false, false, false, false};
            TestWeight[] testCases = getUseCases();

            for (int i = 0; i < testCases.length; i++) {
                boolean ret;
                String retStr = null;
                boolean retBool = false;
                Weight retWeight = null;
                String output = null;

                output = "testCases[" + i + "]: " +
                    "{" + testCases[i]._weightThis.getKilos() + ", " + testCases[i]._weightThis.getGrams() + "}.";
    
                switch (testCases[i]._type) {
                case TO_STRING:
                    retStr = testCases[i]._weightThis.toString();
                    ret = retStr.equals(testCases[i]._resStr);
                    break;
                case EQUALS:
                    retBool = testCases[i]._weightThis.equals(testCases[i]._weightOther);
                    ret = retBool == testCases[i]._resBool;
                    break;
                case LIGHTER:
                    retBool = testCases[i]._weightThis.lighter(testCases[i]._weightOther);
                    ret = retBool == testCases[i]._resBool;
                    break;
                case HEAVIER:
                    retBool = testCases[i]._weightThis.heavier(testCases[i]._weightOther);
                    ret = retBool == testCases[i]._resBool;
                    break;
                case ADD:
                    retWeight = testCases[i]._weightThis.add(testCases[i]._gramsAdd);
                    ret = testCases[i]._weightOther.equals(retWeight);
                    break;
                default:
                    ret = false;
                    break;
                }

                switch (testCases[i]._type) {
                case TO_STRING:
                    output += functionName(testCases[i]._type) + "() = " + retStr;
                    break;
                case EQUALS:
                case LIGHTER:
                case HEAVIER:
                    output += functionName(testCases[i]._type) + "(" + testCases[i]._weightOther.getKilos() + ", " + testCases[i]._weightOther.getGrams() + ") = " + retBool;
                    break;
                case ADD:
                    output += functionName(testCases[i]._type) + "(" + testCases[i]._gramsAdd + ") = " + retWeight.toString();
                    break;
                default:
                    ret = false;
                    break;

                }

                if (ret) {
                    output += " (PASS)";
                } else {
                    output += " (FAIL)";
                    errors[testCases[i]._type] = true;
                }
    
                tested[testCases[i]._type] = true;
                System.out.println(output);
            }

            for (int i = 0 ; i < errors.length; i++) {
                if (!tested[i])
                    continue;
    
                System.out.println("Weight." + functionName(i) + "(): " + (errors[i] ? "BAD" : "GOOD"));
            }
        }

        private String functionName(int func) {
            String s;
    
            switch (func) {
                case TO_STRING:
                    s = "to_string";
                    break;
                case EQUALS:
                    s = "equals";
                    break;
                case LIGHTER:
                    s = "lighter";
                    break;
                case HEAVIER:
                    s = "heavier";
                    break;
                case ADD:
                    s = "add";
                    break;
                default:
                    s = "UNKNOWN";
                    break;
            }
    
            return s;
        }
    }
}
