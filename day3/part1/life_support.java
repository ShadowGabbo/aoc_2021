import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class life_support {
    //ATTRIBUTI
    /**Array orizzontale */
    private List<String> arr;
    /**Array verticale */
    private List<String> vertical_arr;

    //COSTRUTTORI
    public life_support() {
        arr = new ArrayList<String>();
    }

    
    //METODI
    /**
     * Effetti collaterali: modifica this
     * Post-condizioni: legge i numeri da file e li inserisce all'interno di elems
     *                  Solleva un'eccezione di tipo FileNotFoundException se il file
     *                  non viene trovato, e una IOException se c'è un problema di input/ouput
     */
    public void insert(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                arr.add(line);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Post-condizioni: restituisce una rappresentazione testuale di un array
    */
    @Override 
    public String toString(){
        String r = "Life support: {";
        for (int i=0; i<arr.size()-1;i++){
            r+= arr.get(i)+", ";
        }
        r+=arr.get(arr.size()-1);
        return r+"}";
    }

    /**
     * Post-condizioni: crea e restituisce un array "verticale" 
     */
    public void vertical_array(){
        vertical_arr = new ArrayList<String>();
        String elem = "";
        for (int i=0; i< this.arr.get(0).length(); i++) { //carattere indice 
            elem = "";
            for (int j = 0; j < this.arr.size(); j++){ //elemento
                elem += Character.toString(this.arr.get(j).charAt(i));
            }
            vertical_arr.add(elem);
        }
    }

    /**
     * Post-condizioni: calcola il gamma rate e l'epsilon rate e il power consumption rate
     */
    public void gamma_epsilon(){
        String gamma ="", epsilon="";
        for (String num : this.vertical_arr){
            int zero = 0,uno = 0;
            for (char ch : num.toCharArray()){
                if (ch=='0') zero++;
                else uno++; 
            }
            if (zero>uno){
                gamma += "0";
                epsilon += "1";
            }else{
                gamma += "1";
                epsilon += "0";
            }
        }
        System.out.println("Gamma rate is "+Integer.parseInt(gamma, 2));
        System.out.println("Epsilon rate is "+Integer.parseInt(epsilon, 2));
        System.out.println("Power consumption rate of the submarine is "+Integer.parseInt(epsilon, 2)*Integer.parseInt(gamma, 2));
    }
}