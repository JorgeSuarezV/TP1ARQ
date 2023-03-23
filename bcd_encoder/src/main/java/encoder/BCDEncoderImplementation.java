package encoder;

public class BCDEncoderImplementation implements BCDEncoder {
    @Override
    public String encode(int number) {
        String numStr = Integer.toString(number);
        for (int i = 0; i < numStr.length(); i++) {
            int digito = Character.getNumericValue(numStr.charAt(i));
        }
    }

    @Override
    public int decode(String bcdEncodedNumber) {
        return 0;
    }
}
