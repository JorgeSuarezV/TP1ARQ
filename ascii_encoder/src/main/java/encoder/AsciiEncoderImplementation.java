package encoder;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.InputMismatchException;


public class AsciiEncoderImplementation implements AsciiEncoder {

    private final BiMap<String, Character> asciiMap = HashBiMap.create(new HashMap<>());

    public AsciiEncoderImplementation() {
        for (int i = 0; i <= 127; i++) {
            asciiMap.put(fillString(Integer.toBinaryString(i)), (char) i);
        }
    }

    private String fillString(String string) {
        if (string.length() < 8) return fillString("0" + string);
        return string;
    }

    @Override
    public String encode(String binary) {
        checkMaliciousInputBinary(binary);
        return toText(binary);
    }

    private String toText(String binary) {
        StringBuilder toString = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            if (!asciiMap.containsKey(binary.substring(i, i + 8))) throw new InputMismatchException("not ascii binary");
            toString.append(asciiMap.get(binary.substring(i, i + 8)));
        }
        return toString.toString();
    }

    private void checkMaliciousInputBinary(String binary) {
        if ((binary.length() % 8) != 0) throw new InputMismatchException("input length must be multiple of 8");
        for (char c : binary.toCharArray()) {
            if (!(c == '0' || c == '1')) throw new InputMismatchException("Input must be binary");
        }
    }

    @Override
    public String decode(String ascii) {
        StringBuilder binary = new StringBuilder();
        for (char c : ascii.toCharArray()) {
            if (!asciiMap.inverse().containsKey(c)) throw new InputMismatchException("not ascii character");
            binary.append(asciiMap.inverse().get(c));
        }
        return binary.toString();
    }
}
