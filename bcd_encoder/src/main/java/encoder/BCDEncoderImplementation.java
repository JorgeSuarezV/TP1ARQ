package encoder;

import java.util.InputMismatchException;

public class BCDEncoderImplementation implements BCDEncoder {

    @Override
    public String encode(int number) {
        String numStr = Integer.toString(number);
        return encodeToBCD(numStr).toString();
    }

    private StringBuilder encodeToBCD(String numStr) {
        StringBuilder encodedString = new StringBuilder();
        for (int i = numStr.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numStr.charAt(i));
            encodedString.insert(0, (fillString(Integer.toBinaryString(digit))));
        }
        return encodedString;
    }

    @Override
    public int decode(String bcdEncodedNumber) {
        checkMaliciosInputBinary(bcdEncodedNumber);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bcdEncodedNumber.length(); i += 4) {
            result.append(calculateNumber4bits(bcdEncodedNumber.substring(i, i + 4)));
        }
        return Integer.parseInt(result.toString());
    }

    private String fillString(String string) {
        if (string.length() < 4) return fillString("0" + string);
        return string;
    }

    private String calculateNumber4bits(String bits) {
        char[] bitsArray = bits.toCharArray();
        int result = 0;
        for (int i = 0; i < bitsArray.length; i++) {
            result += (int) (Integer.parseInt("" + bitsArray[bitsArray.length - 1 - i]) * Math.pow(2, i));
        }
        return "" + result;
    }

    private void checkMaliciosInputBinary(String binary) {
        if ((binary.length() % 4) != 0) throw new InputMismatchException("input length must be multiple of 4");
        for (char c : binary.toCharArray()) {
            if (!(c == '0' || c == '1')) throw new InputMismatchException("Input must be binary");
        }
    }
}
