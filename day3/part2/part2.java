import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class part2{
    public static void main(String[] args) {
        String oxygen = "";
        String co2 = "";
        ArrayList<String> arr = 
                  new ArrayList <String>();
        int num_zero = 0, num_one;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
             arr.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ArrayList arr2 = new ArrayList();
        arr2 = arr;
        //System.out.println("Oxygen: "+ bit_criteria_oxygen(arr));
        System.out.println("Oxygen: "+ bit_criteria_oxygen(arr2));
        }
        
        //for oxygen
        public static String bit_criteria_oxygen(ArrayList<String> arr){
            int zero = 0;
            int one = 0;
            int index = 0;
            while(arr.size()!=1){
                String res = "";
                for (int i = 0; i < arr.size(); i++){
                    String line = arr.get(i);
                    for (int j = 0; j < line.length(); j++){
                        if (j==index){
                            res += line.charAt(j);
                        }
                    }
                }
                
                int c = count_v2(res);
                if (c==0){
                    System.out.println("Caso uguale");
                    arr = remove_elems(arr, index, '1');
                }else if (c==1){
                    System.out.println("Ci sono piu zeri di uno");
                    arr = remove_elems(arr, index, '1');
                }else{
                    System.out.println("Ci sono piu uno di zeri");
                    arr = remove_elems(arr, index, '0');
                }
                System.out.println(arr.toString());
                index++;
            }
            return arr.get(0);
        }

    // se il numero di 1 > del numero di 0 allora ritorna true
    public static int count(String num){
        int zero = 0,one = 0;
        for(int i=0; i<num.length(); i++){
            if (num.charAt(i)=='0') one++;
            else zero++;
        }
        if (zero==one){
            return 0;
        }else if (zero<one){
            return 1;
        }else{
            return 2;
        }
    }

    // se il numero di 1 > del numero di 0 allora ritorna true
    public static int count_v2(String num){
        int zero = 0,one = 0;
        for(int i=0; i<num.length(); i++){
            if (num.charAt(i)=='0') one++;
            else zero++;
        }
        if (zero==one){
            return 0;
        }else if (zero<one){
            return 2;
        }else{
            return 1;
        }
    }

    //distrugge gli elementi che hanno il covid
    public static remove_elems(ArrayList<String> arr, int index, char covid){
        for (String elem : arr){
            if (elem.charAt(index)==covid){
                arr.remove(index);
        }
    }
}
}
