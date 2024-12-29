public class Weight {
    /* Members */
    private int _kilos;
    private int _grams;

    /* Constants */
    private static final int GRAMS_IN_KILO = 1000;

    /* Constructors */
    public Weight(int kilos, int grams) {
        if (kilos < 1 || grams < 0) {
            setWeightDefault();
        } else {
            if (GRAMS_IN_KILO <= grams) {
                kilos += grams / GRAMS_IN_KILO;
                grams %= GRAMS_IN_KILO;
            }

            setWeight(kilos, grams);
        }
    }
    
    public Weight(int grams) {
        if (grams < GRAMS_IN_KILO) {
            setWeightDefault();
        } else {
            setWeight(grams / GRAMS_IN_KILO, grams % GRAMS_IN_KILO);
        }
    }

    public Weight(Weight other) {
        setWeight(other._kilos, other._grams);
    }

    /* API methods */
    public int getKilos() {
        return _kilos;
    }

    public int getGrams() {
        return _grams;
    }

    public boolean equals(Weight other)
    {
        return _kilos == other._kilos && _grams == other._grams;
    }

    public boolean lighter(Weight other) {
        if (_kilos < other._kilos)
            return true;
        if (_kilos > other._kilos)
            return false;

        return _grams < other._grams;
    }

    public boolean heavier(Weight other) {
        return !equals(other) && !lighter(other);
    }

    public Weight add(int grams) {
        grams += _kilos * GRAMS_IN_KILO + _grams;

        return grams < GRAMS_IN_KILO ? new Weight(this) : new Weight(grams);
    }

    public String toString() {
        int decimal = _grams;
        int shift = 0;

        if (decimal != 0) {
            if (decimal < 100) {
                shift++;
                if (decimal < 10)
                    shift++;
            }

            if ((decimal % 10) == 0) {
                decimal /= 10;
                if ((decimal % 10) == 0)
                    decimal /= 10;
           }
        }

        String s = _kilos + ".";
        for (int i = 0; i < shift; i++)
            s += "0";
        s += decimal;

        return s;
    }

    /* Private methods */
    private void setWeight(int kilos, int grams) {
        _kilos = kilos;
        _grams = grams;
    }

    private void setWeightDefault() {
        setWeight(1, 0);
    }
}
