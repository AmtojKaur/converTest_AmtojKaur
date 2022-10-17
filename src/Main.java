public class Main {
    public static void main(String[] args){
        ConvertCopy c = new ConvertCopy();
        char[] ca = {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'};
        System.out.println(c.convert2sCompToDecimal(ca));

        Convert co = new Convert();
        System.out.println(co.convert2sCompToDecimal(ca));

        //System.out.println(co.);
        //System.out.println(c.convertDecimalTo2sComp(63));
        //System.out.println(c.convertDecimalTo2sComp(-64));
    }
}
