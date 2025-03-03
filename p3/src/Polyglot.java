import org.graalvm.polyglot.*;

public class Polyglot {
    public static void main(String[] args) {
        Context context = Context.newBuilder().allowAllAccess(true).build();
        String pythonCode =
                "def f1():\n" +
                        "n=input('n=: ')\n" +
                        "x=input('x= ')\n" +
                        "return n,x\n" +
                        "f1()";
        Value res=context.eval("python", pythonCode);
        int n=res.getArrayElement(0).asInt();
        int x=res.getArrayElement(1).asInt();
        String rCode=
                "f2<-function(n,x) {\n" +
                        "prob<-pbinom(x,size=n,prob=0.5)\n" +
                        "return(prob)\n" +
                        "}\n" +
                        "f2(" + n + ", " + x + ")";
        Value rRes=context.eval("R",rCode);
        double REZ=rRes.asDouble();
        System.out.println("Probabilitatea este: "+ REZ);

    }
}