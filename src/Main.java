import calculo.Formula;

public class Main {


    public static void main(String[] args) {
        Formula f = new Formula("(((1+2)*(3+4))*((5+6)*(7+8)))");
        //Formula.resolver("(((1+2)*(3+4))*((5+6)*(7+8)))");

        f.solve();
        //System.out.println(f);
    }

}
