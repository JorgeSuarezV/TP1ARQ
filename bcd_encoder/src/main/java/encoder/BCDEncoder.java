package encoder;

public interface BCDEncoder {
    String encode(int number);
    int decode(String bcdEncodedNumber);
}
