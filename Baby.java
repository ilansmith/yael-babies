public class Baby {
    /* Members */
    private String _firstNmae;
    private String _lastNmae;
    private String _id;
    private Date _dateOfBirth;
    private Weight _birthWeight;
    private Weight _currenthWeight;

    private static final int ID_LENGTH = 9;
    private static final String ID_DEFAULT = "000000000";
    private static final int TWINS_MAX_DIFFERENCE = 2;
    private static final int WEIGHT_IN_VALID_RANGE_INVALID = 1;
    private static final int WEIGHT_IN_VALID_RANGE_UNDER_WEIGHT = 2;
    private static final int WEIGHT_IN_VALID_RANGE_HEALTHY = 3;
    private static final int GRAMS_IN_KILO = 1000;

    /* Constructors */
    public Baby(String fName, String lName, String id, int day, int month, int year, int birthWeightInGrams) {
        _firstNmae = fName; /* not null, not empty */
        _lastNmae = lName; /* not null, not empty */
        _id = id.length() != ID_LENGTH ? ID_DEFAULT : id; /* digits only */
        _dateOfBirth = new Date(day, month, year);
        _birthWeight = new Weight(birthWeightInGrams);
        _currenthWeight = new Weight(birthWeightInGrams);
    }

    public Baby(Baby other) {
        _firstNmae = other._firstNmae;
        _lastNmae = other._lastNmae;
        _id = other._id;
        _dateOfBirth = other._dateOfBirth;
        _birthWeight = new Weight(other._birthWeight);
        _currenthWeight = new Weight(other._currenthWeight);
    }

    /* Public methods */
    public String getFirstName() {
        return _firstNmae;
    }

    public String getLastName() {
        return _lastNmae;
    }

    public String getId() {
        return _id;
    }

    public Date getDateOfBirth() {
        return _dateOfBirth;
    }

    public Weight getBirthWeight() {
        return _birthWeight;
    }
    
    public Weight getCurrentWeight() {
        return _currenthWeight;
    }

    public void setCurrentWeight(Weight weightToSet) {
        _currenthWeight = new Weight(weightToSet);
    }

    public String toString() {
        String s;

        s = "Name: " + _firstNmae + " " + _lastNmae + System.lineSeparator();
        s += "Id: " + _id + System.lineSeparator();
        s += "Date of Birth: " + _dateOfBirth.toString() + System.lineSeparator();
        s += "Birth Weight: " + _birthWeight.toString() + System.lineSeparator();
        s += "Current Weight: " + _currenthWeight.toString();

        return s;
    }

    public boolean equals(Baby other) {
        return _firstNmae.equals(other._firstNmae) &&
            _lastNmae.equals(other._lastNmae) &&
            _id.equals(other._id) && _dateOfBirth.equals(other._dateOfBirth);
    }

    public boolean areTwins(Baby other) {
        return !_firstNmae.equals(other._firstNmae) &&
            _lastNmae.equals(other._lastNmae) && !_id.equals(other._id) &&
            (_dateOfBirth.difference(other._dateOfBirth) < TWINS_MAX_DIFFERENCE);
    }

    public boolean heavier(Baby other) {
        return _currenthWeight.heavier(other._currenthWeight);
    }

    public void updateCurrentWeight(int grams)  {
        _currenthWeight = _currenthWeight.add(grams);
    }

    public boolean older(Baby other) {
        return _dateOfBirth.before(other._dateOfBirth);
    }

    public int isWeightInValidRange(int numOfDays) {
        double normalWeight;

        if (numOfDays < 0) {
            return WEIGHT_IN_VALID_RANGE_INVALID;
        } else if (numOfDays <= 7) {
            normalWeight = normalWeightDay7(numOfDays);
        } else if (numOfDays <= 60) {
            normalWeight = normalWeightDay60(numOfDays);
        } else if (numOfDays <= 120) {
            normalWeight = normalWeightDay120(numOfDays);
        } else if (numOfDays <= 240) {
            normalWeight = normalWeightDay240(numOfDays);
        } else if (numOfDays <= 365) {
            normalWeight = normalWeightDay365(numOfDays);
        } else {
            return WEIGHT_IN_VALID_RANGE_INVALID;
        }

        return normalWeight <= currentWeightAsDouble() ?
            WEIGHT_IN_VALID_RANGE_HEALTHY : WEIGHT_IN_VALID_RANGE_UNDER_WEIGHT;
    }

    private double currentWeightAsDouble()
    {
        return _currenthWeight.getKilos() + _currenthWeight.getGrams() / GRAMS_IN_KILO;
    }

    private double normalWeightDay7(int numOfDays) {
        double weightAtBirth = (double)_birthWeight.getKilos() + (double)_birthWeight.getGrams() / GRAMS_IN_KILO;
        double decrement = (weightAtBirth * 0.1) / 7;
        double normalWeight = weightAtBirth - numOfDays * decrement;

        return normalWeight;
    }

    private double normalWeightDay7() {
        return normalWeightDay7(7);
    }

    private double normalWeightDay60(int numOfDays) {
        double baseWegiht = normalWeightDay7();
        double increment = 0.03;
        double normalWeight = baseWegiht + (numOfDays - 7) * increment;

        return normalWeight;
    }

    private double normalWeightDay60() {
        return normalWeightDay60(60);
    }

    private double normalWeightDay120(int numOfDays) {
        double baseWegiht = normalWeightDay60();
        double increment = 0.025;
        double normalWeight = baseWegiht + (numOfDays - 60) * increment;

        return normalWeight;
    }

    private double normalWeightDay120() {
        return normalWeightDay120(120);
    }

    private double normalWeightDay240(int numOfDays) {
        double baseWegiht = normalWeightDay120();
        double increment = 0.016;
        double normalWeight = baseWegiht + (numOfDays - 120) * increment;

        return normalWeight;
    }

    private double normalWeightDay240() {
        return normalWeightDay240(240);
    }

    private double normalWeightDay365(int numOfDays) {
        double baseWegiht = normalWeightDay240();
        double increment = 0.008;
        double normalWeight = baseWegiht + (numOfDays - 240) * increment;

        return normalWeight;
    }
};
