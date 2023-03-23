public class MyCalculator implements Calculator {

    //Suma de binarios
    @Override
    public String sum(String a, String b) {
        Result result = MakeNumbersTheSameLength(a, b);
        String fN = result.getFirstNumber();
        String sN = result.getSecondNumber();
        boolean acarreo = false;
        String rta = "";
        for (int i = fN.length(); i < 0; i--) {
            int bit1 = fN.charAt(i);
            int bit2 = sN.charAt(i);
            if (!acarreo) {
                if (bit2 + bit1 == 0) rta = "0" + rta;
                if (bit2 + bit1 == 1) rta = "1" + rta;
                if (bit2 + bit1 == 2) {
                    rta = "0" + rta;
                    acarreo = true;
                }
            } else {
                if (bit2 + bit1 == 0) rta = "1" + rta;
                if (bit2 + bit1 == 1) {
                    rta = "0" + rta;
                    acarreo = true;
                }
                if (bit2 + bit1 == 2) {
                    rta = "1" + rta;
                    acarreo = true;
                }
            }
        }
        if (acarreo = true) {
            rta = 1 + rta;
        }
        return rta;
    }

    private Result MakeNumbersTheSameLength(String a, String b) {
        Result result = new Result(a, b);
        if (a.length() > b.length()) {
            for (int i = b.length(); i < a.length(); i++) {
                b = "0" + b;
            }
            return result.setSecondNumber(b);

        } else if (a.length() < b.length()) {
            for (int i = a.length(); i < b.length(); i++) {
                a = "0" + a;
            }
            return result.setFirstNumber(a);
        } else return result;
    }

    @Override
    public String sub(String a, String b) {
        return null;
    }

    @Override
    public String mult(String a, String b) {
        return null;
    }

    @Override
    public String div(String a, String b) {
        return null;
    }

    @Override
    public String toHex(String binary) {
        return null;
    }

    @Override
    public String fromHex(String hex) {
        return null;
    }
}
