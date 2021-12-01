import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * OVERVIEW: Le istanze di questa classe rappresentano insiemi di interi.
 */
public class submarine {
    //ATTRIBUTI
    /**Struttura dati contenente gli elementi dell'insieme*/
    private List<Integer> elems;

    //COSTRUTTORI
    public submarine(){
        elems = new ArrayList<Integer>();
    }

    //METODI
    /**
     * Effetti collaterali: modifica this
     * Post-condizioni: Legge i numeri da file e li inserisce all'interno di elems
     *                  Solleva un'eccezione di tipo FileNotFoundException se il file
     *                  non viene trovato, e una IOException se c'è un problema di input/ouput
     */
    public void insert(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                elems.add(Integer.valueOf(line));
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Post-condizioni: conta il numero di terne maggiori della precedente
     */
    public int count(){
        int a = 0,b = 0,c = 0,sum = 0,counter = 0;
        boolean start = true;
        for (int i = 2; i < elems.size(); i++){
            if (start){
                a = elems.get(0);
                b = elems.get(1);
                c = elems.get(2);
                triple t = new triple(a,b,c);
                sum = t.sum();
                start = false;
            }else{
                a = b;
                b = c;
                c = elems.get(i);
                triple t = new triple(a,b,c);
                if (t.sum()>sum) counter++;
                sum = t.sum();
            }
        }
        return counter;
    }

    /**
     * Post-condizioni: restituisce una rappresentazione testuale di submarine
     */
    @Override // solo se la segnatura è uguale (ereditarietà)
    public String toString(){
        String r = "Submarine: {";
        for (int i=0; i<elems.size()-1;i++){
            r+= elems.get(i)+", ";
        }
        r+=elems.get(elems.size()-1);
        return r+"}";
    }
}