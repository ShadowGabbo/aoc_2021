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
     * Post-condizioni: conta il numero di elementi maggiori del dell'elemento precedente
     */
    public int counter(){
        int counter = 0;
        int last = elems.get(0);
        for(int num: elems){
            if (num>last) counter++;
            last = num;
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