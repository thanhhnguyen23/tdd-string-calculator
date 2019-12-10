import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static final String ERROR_PREFIX = "01";
    public static final String SUCCESS_PREFIX = "00";
    private String input = "";
    private int output;
    private Integer result;
    private String operator = "";

    public Calculator(int invalidInput) throws inputValidInputException {
        throw new inputValidInputException(getErrorPrefix());
    }

    public Calculator(String input) {
        setInput(input);
        System.out.println("original input: " + getInput());
    }


    public String calculate() {
        List<String> operator = new ArrayList<>();
        List<String> strOperands = new ArrayList<>();
        List<Integer> intOperands = new ArrayList<>();

        // validate input
        if (isOperatorValid()) {

            // parsing input for operators and operands
            if (parsingInput(operator, strOperands)) return getErrorPrefix();

            System.out.println("length of String operands: " + strOperands.size());

            System.out.println("String operands: " + strOperands);

            processingStringOperands(strOperands, intOperands);

            System.out.println("Integer operands: " + strOperands);

            System.out.println("addition with Integers: " + intOperands);

            if(this.operator.contains("AD")){
                additionOperation(intOperands);
            }
            if(this.operator.contains("MU")){
                multiplicationOperation(intOperands);
            }

            System.out.println("result: " + this.result);

            setOutput(this.result);

            System.out.println("output: " + getOutput());

        } else {
            return getErrorPrefix();
        }
        return getSuccessPrefix() + getOutput();
    }

    private boolean parsingInput(List<String> operator, List<String> strOperands) {
        for (String originInput : getInput().split(";")) {

            // adding operator to list
            if (originInput.contains("AD") || originInput.contains("MU")) {
                operator.add(originInput);
                System.out.println("operator: " + operator);
                this.operator = operator.get(0);
            }
            // adding operands to list
            else if (!originInput.contains("MU") && !originInput.contains("AD")) {
//                        System.out.println("getting values from input ** : " + originInput);
                strOperands.add(originInput);
                System.out.println("String operands: " + strOperands);
                if (maxInputValidation(strOperands)) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }
    private void multiplicationOperation(List<Integer> intOperands) {
        this.result = intOperands.get(0);

        for(int i = 1; i <= intOperands.size() - 1; i++){
            this.result *= intOperands.get(i);
//            System.out.println("multiplication :: result :" + this.result);
//            System.out.println("multiplication :: i: " + i);

        }
    }

    private void additionOperation(List<Integer> intOperands) {
        this.result = 0;
        for(Integer intOps : intOperands){
            this.result += intOps;
        }
    }

    private void processingStringOperands(List<String> strOperands, List<Integer> intOperands) {
        for(String strOp : strOperands){
            intOperands.add(Integer.parseInt(strOp));
        }
    }

    private boolean maxInputValidation(List<String> strOperands) {
        if (strOperands.size() > 10) {
//            System.out.println("operands exceeds more than 10 variables: ");
//            System.out.println(strOperands);
            return true;
        }
        return false;
    }

    private boolean isOperatorValid() {
        return getInput().contains("MU") || getInput().contains("AD") ? true : false;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public static String getErrorPrefix() {
        return ERROR_PREFIX;
    }

    public static String getSuccessPrefix() {
        return SUCCESS_PREFIX;
    }
}
