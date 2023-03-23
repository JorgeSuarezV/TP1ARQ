package encoder;

import encoder.AsciiEncoder;
import encoder.AsciiEncoderImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

public class AsciiEncoderTest {

    @Test
    public void correctEncoderTest() {
        AsciiEncoder encoder = new AsciiEncoderImplementation();
        String encoded = encoder.encode("0100000101011010"); // binary for AZ
        Assertions.assertEquals("AZ", encoded);
    }

    @Test
    public void wrongLengthBinaryInputEncode() {
        AsciiEncoder encoder = new AsciiEncoderImplementation();
        Exception exception = Assertions.assertThrows(InputMismatchException.class, () -> encoder.encode("010000101011010"));
        Assertions.assertEquals("input length must be multiple of 8", exception.getMessage());
    }

    @Test
    public void unexpectedCharacterInputEncode() {
        AsciiEncoder encoder = new AsciiEncoderImplementation();
        Exception exception = Assertions.assertThrows(InputMismatchException.class, () -> encoder.encode("010000010101101F"));
        Assertions.assertEquals("Input must be binary", exception.getMessage());
    }

    @Test
    public void characterNotAsciiBinaryInputEncode() {
        AsciiEncoder encoder = new AsciiEncoderImplementation();
        Exception exception = Assertions.assertThrows(InputMismatchException.class, () -> encoder.encode("1100000101011010"));
        Assertions.assertEquals("not ascii binary", exception.getMessage());
    }

    @Test
    public void correctDecode() {
        AsciiEncoder encoder = new AsciiEncoderImplementation();
        String result = encoder.decode("hola");
        Assertions.assertEquals("01101000011011110110110001100001", result); //hola en binario
    }

    @Test
    public void notAsciiCarDecode() {
        AsciiEncoder encoder = new AsciiEncoderImplementation();
        Exception exception = Assertions.assertThrows(InputMismatchException.class, () -> encoder.decode("ñ"));
        Assertions.assertEquals("not ascii character", exception.getMessage());
    }
}
