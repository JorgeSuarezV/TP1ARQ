public class Result {
    private String firstNumber;
    private String secondNumber;

    public Result(String a, String b) {
        firstNumber=a;
        secondNumber=b;
    }


    public Result setFirstNumber(String firstNumber) {
        return new Result(firstNumber , secondNumber);
    }

    public Result setSecondNumber(String secondNumber) {
        return new Result(firstNumber , secondNumber);
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public String getFirstNumber() {
        return firstNumber;
    }
}
