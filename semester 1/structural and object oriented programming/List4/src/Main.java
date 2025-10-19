public class Main
{
    public static void print(functionApproximation values)
    {

    }
    public static void main(String[] args)
    {
        //1 output
        functionApproximation values = new functionApproximation(Math.PI/4, 50);
        System.out.println("sin " + values.x + " = " + values.sin());
        System.out.println("cos " + values.x + " = " + values.cos());
        System.out.println("tan "+values.x+" = sin "+values.x+"/cos "+values.x+" = "+values.tan());
        System.out.println("arcsin "+values.x+" = "+values.arcsin());
        System.out.println("arccos " + values.x + " = Pi/2 - arcsin x = " + values.arccos());
        System.out.println("arctan " + values.x + " = " + values.arctan());

        //2 output
        System.out.println("with seperate package:");
        functionApproximation2 fp = new functionApproximation2(Math.PI/4, 50);
        System.out.println("sin " + fp.x + " = " + fp.sin());
        System.out.println("cos " + fp.x + " = " + fp.cos());
        System.out.println("tan "+fp.x+" = sin "+fp.x+"/cos "+fp.x+" = "+fp.tan());
        System.out.println("arcsin "+fp.x+" = "+fp.arcsin());
        System.out.println("arccos " + fp.x + " = Pi/2 - arcsin x = " + fp.arccos());
        System.out.println("arctan " + fp.x + " = " + fp.arctan());

    }
}