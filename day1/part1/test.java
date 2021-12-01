public class test{
    public static void main(String[] args) {
        submarine s = new submarine();
        s.insert("input.txt");
        System.out.println("Counter: "+ s.counter());
    }
}