import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class test{
    public static void main(String[] args) {
        int h_pos=0,depth=0;
        ArrayList<Integer> elems = new ArrayList<Integer>();
        ArrayList<String> cmds = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line,cmd;
            while ((line = br.readLine()) != null) {
               String cmd2 = line.replaceAll(" ", "");
               cmd = cmd2.substring(0, cmd2.length() -1);
               int elem = (int)(cmd2.charAt(cmd2.length()-1)-'0');
               cmds.add(cmd);
               elems.add(elem);
               }
               //System.out.println(cmds.toString());
               //System.out.println(elems.toString());
               for (int i = 0; i < elems.size(); i++) {
                int elem = elems.get(i);
                String command = cmds.get(i);
                if (command.equals("forward")) {
                    h_pos+=elem;
                }
                if (command.equals("down")) {
                    depth+=elem;
                }
                if (command.equals("up")) {
                    depth-=elem;
                }
                System.out.println(command);
                System.out.println(elem);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("h_pos ha valore: "+h_pos+ ", depth ha valore: "+depth+", totale: "+h_pos*depth);
    }
    
}