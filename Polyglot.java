//import libraria principala polyglot din graalvm
import org.graalvm.polyglot.*;




//clasa principala - aplicatie JAVA
class Polyglot {
    //metoda privata pentru conversie low-case -> up-case folosind functia toupper() din R
    private static String RToUpper(String token){
        //construim un context care ne permite sa folosim elemente din R
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        //folosim o variabila generica care va captura rezultatul excutiei funcitiei R, toupper(String)
        //pentru aexecuta instructiunea I din limbajul X, folosim functia graalvm polyglot.eval("X", "I");
        Value result = polyglot.eval("R", "toupper(\""+token+"\");");
        //utilizam metoda asString() din variabila incarcata cu output-ul executiei pentru a mapa valoarea generica la un String
        String resultString = result.asString();
        // inchidem contextul Polyglot
        polyglot.close();

        return resultString;
    }

    private static void afisare(Value v){
        for(int i=0;i<v.getArraySize();i++) {
            System.out.println(v.getArrayElement(i));
            System.out.println('\n');
        }
    }

    //metoda privata pentru evaluarea unei sume de control simple a literelor unui text ASCII, folosind PYTHON
    private static int SumCRC(String token){
        //construim un context care ne permite sa folosim elemente din PYTHON
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        //folosim o variabila generica care va captura rezultatul excutiei functiei PYTHON, sum()
        //avem voie sa inlocuim anumite elemente din scriptul pe care il construim spre evaluare, aici token provine din JAVA, dar va fi interpretat de PYTHON
        Value result = polyglot.eval("python", "sum(ord(ch) for ch in '" + token + "')");
        //utilizam metoda asInt() din variabila incarcata cu output-ul executiei, pentru a mapa valoarea generica la un Int
        int resultInt = result.asInt();
        // inchidem contextul Polyglot
        polyglot.close();

        return resultInt;
    }

    private static Value NumRND(){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        polyglot.eval("python", "import random\n" + "v=[random.randint(1,100) for i in range(20)]");
        Value result= polyglot.eval("python", "v");

        return result;
    }

    //functia MAIN
    public static void main(String[] args) {
        //construim un context pentru evaluare elemente JS
        Context polyglot = Context.create();
        //construim un array de string-uri, folosind cuvinte din pagina web:  https://chrisseaton.com/truffleruby/tenthings/
        Value array = polyglot.eval("js", "[\"If\",\"we\",\"run\"]");
        //pentru fiecare cuvant, convertim la upcase folosind R si calculam suma de control folosind PYTHON
        List<String> cuvinte =new ArrayList<>();
        List<Integer> sume_de_cntrl= new ArrayList<>();
        for (int i = 0; i < array.getArraySize();i++){
            String element = array.getArrayElement(i).asString();
            String upper = RToUpper(element);
            int crc = SumCRC(upper);
            cuvinte.add(element);
            sume_de_cntrl.add(crc);
        }

        for(int i=0;i<sume_de_cntrl.size();i++)
        {
            if(cuvinte.get(i)!=null) {
                int crc = sume_de_cntrl.get(i);
                System.out.printf(sume_de_cntrl.get(i)+ ": "+cuvinte.get(i) + " ");
                for (int j = i + 1; j < sume_de_cntrl.size(); j++) {
                    if (sume_de_cntrl.get(j) == crc) {
                        System.out.printf(cuvinte.get(j) + " ");
                        cuvinte.set(j, null);
                    }
                }
                System.out.println();
            }
        }
        // inchidem contextul Polyglot
        polyglot.close();
    }
}

