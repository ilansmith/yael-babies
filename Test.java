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
}
