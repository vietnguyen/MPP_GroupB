package Lab4.probC;

public class PayCheck {
    private final double grossPay;
    private final double fica = 0.23;
    private final double state = 0.05;
    private final double local = 0.01;
    private final double medicare = 0.03;
    private final double socialSecurity = 0.075;

    public PayCheck(double grossPay){
        this.grossPay = grossPay;
    }

    public double getNetPay(){
        double net = grossPay - (grossPay * (fica + state + local + medicare + socialSecurity));
        return net;
    }

    public void print(){
        double ficaAmount = fica * grossPay;
        double stateAmount = state * grossPay;
        double localAmount = local * grossPay;
        double medicareAmount = medicare * grossPay;
        double socialSecurityAmount = socialSecurity * grossPay;

        System.out.printf("Gross: $%.0f\n", grossPay);
        System.out.printf("Fica: $%.0f\n", ficaAmount);
        System.out.printf("State: $%.0f\n", stateAmount);
        System.out.printf("Local: $%.0f\n", localAmount);
        System.out.printf("Medicare: $%.0f\n", medicareAmount);
        System.out.printf("Social Security: $%.0f\n", socialSecurityAmount);
        System.out.printf("Net: $%.0f\n", getNetPay());
    }
}
