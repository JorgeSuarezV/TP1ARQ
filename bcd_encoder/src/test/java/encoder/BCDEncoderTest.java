package encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

public class BCDEncoderTest {

    @Test
    public void correctEncodeTest(){
        BCDEncoder encoder = new BCDEncoderImplementation();
        Assertions.assertEquals("00100100",  encoder.encode(24));
        Assertions.assertEquals("0010000001001000",  encoder.encode(2048));
        Assertions.assertEquals("01101001010000100000",  encoder.encode(69420));
    }


    @Test
    public void correctDecodeTest(){
        BCDEncoder encoder = new BCDEncoderImplementation();
        Assertions.assertEquals(24, encoder.decode("00100100"));
        Assertions.assertEquals(69420,  encoder.decode("01101001010000100000"));
    }

    @Test
    public void unexpectedCharacterInputEncode(){
        BCDEncoder encoder = new BCDEncoderImplementation();
        Exception exception = Assertions.assertThrows(InputMismatchException.class, () -> encoder.decode("010000010101101F"));
        Assertions.assertEquals("Input must be binary", exception.getMessage());
    }

    @Test
    public void lengthIsIncorrectStringDecodeTest(){
        BCDEncoder encoder = new BCDEncoderImplementation();
        Exception exception = Assertions.assertThrows(InputMismatchException.class, () -> encoder.decode("0100000101011"));
        Assertions.assertEquals("input length must be multiple of 4", exception.getMessage());
    }
}
