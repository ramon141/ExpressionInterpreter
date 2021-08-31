package calculo;

public class Formula {

    private String formula;

    public Formula(String formula){
        this.formula = formula;
    }


    private static boolean isNumber(Character c){
        return isNumber(c + "");
    }

    private static boolean isNumber(String str){
        try {
            double i = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    public static double getFirstNumber(String str){
        return getNNumber(str, 1);
    }

    public static double getNNumber(String str, int n){
        String numero = "";
        int i = 0;

        while(--n >= 0){
            numero = "";
            for(; i < str.length(); i++){

                if(isNumber(str.charAt(i)) || ((str.charAt(i) == ',' || str.charAt(i) == '.') && !numero.isEmpty() && !numero.endsWith(",") && !numero.endsWith(".") && !numero.contains(",") && !numero.contains(".")))
                    numero += str.charAt(i) + "";
                else if(!numero.isEmpty())
                    break;

            }
        }

        if(numero.endsWith(",") || numero.endsWith(".")){
            numero = numero.substring(0, numero.length() - 1);
        }

        return Double.parseDouble(numero.replace(",", "."));
    }

    public static double solveTwoNumbers(String str){
        System.out.println("Resolvendo: " +str);

        String signal = getSignal(str);
        if(signal.equals("+")) return getFirstNumber(str) + getNNumber(str, 2);
        if(signal.equals("-")) return getFirstNumber(str) - getNNumber(str, 2);
        if(signal.equals("/")) return getFirstNumber(str) / getNNumber(str, 2);
        if(signal.equals("*")) return getFirstNumber(str) * getNNumber(str, 2);

        str = str.replace("(", "");
        str = str.replace(")", "");

        System.out.println("Tentando converter: " + str);
        return Double.parseDouble(str);
        //throw new RuntimeException("Não conseguiu calcular nehuma operação entre dois valores");
    }


    public void putParenteses(){

    }

    private static String getSignal(String str){
        if(str.contains("+")) return "+";
        if(str.contains("-")) return "-";
        if(str.contains("/")) return "/";
        if(str.contains("*")) return "*";

        return "";
        //throw new RuntimeException("Erro não foi encontrando nenhum sinal na string: " + str);
    }


    public static double resolver(String formula){
        String calculo = "";
        int quantParentesesAbrindo = 0;
        int firstParentesesAbrindo = -1;
        int quantParentesesFechando = 0;

        for(int i = 0; i < formula.length(); i++){
            if(formula.charAt(i) == '('){
                quantParentesesAbrindo++;
                if(firstParentesesAbrindo == -1)
                    firstParentesesAbrindo = i + 1;
            }
            if(formula.charAt(i) == ')') quantParentesesFechando++;

            if(quantParentesesFechando == quantParentesesAbrindo && quantParentesesAbrindo > 0){
                System.out.println("Formula: " + formula.substring(firstParentesesAbrindo, i));
                resolver(formula.substring(firstParentesesAbrindo, i));
                break;
            }
        }

        System.out.println(calculo);

        return 0;
    }

    public static String nextSignal(String str){
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '+') return "+";
            if(str.charAt(i) == '-') return "-";
            if(str.charAt(i) == '/') return "/";
            if(str.charAt(i) == '*') return "*";
        }

        System.out.println("Não há mais sinais");
        return "";
    }

    public double solve(){
        String calculo = "";
        int lastParentes = -1;
        int nextParentesesFechando = -1;
        int ultimoParenteseAbrindo = -1;

        for(int i = 0; i < formula.length(); i++){
            if(formula.charAt(i) == '(') lastParentes = i;
            if(formula.charAt(i) == ')' && nextParentesesFechando == -1) nextParentesesFechando = i;

            if(nextParentesesFechando != -1){
//                ultimoParenteseAbrindo = lastParentes;
//
//                System.out.println(lastParentes + "," + nextParentesesFechando);
                System.out.println("Formula:" + formula.substring(lastParentes, nextParentesesFechando + 1));
//                calculo += "(" + solveTwoNumbers(formula.substring(lastParentes, nextParentesesFechando + 1)) + ")" + nextSignal(formula.substring(nextParentesesFechando, formula.length()));


                formula  = formula.replace( formula.substring(lastParentes, nextParentesesFechando + 1)+"",   solveTwoNumbers( formula.substring(lastParentes, nextParentesesFechando + 1)  ) + "" );
                System.out.println("Formula completa:" + formula);
                nextParentesesFechando = -1;
                i = -1;
                System.out.println("\n\n");
            }
        }

        System.out.println(calculo);

        return 0;
    }


    @Override
    public String toString(){
        return formula;
    }

}
