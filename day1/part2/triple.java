/**
 * OVERVIEW: Le istanze di questa classe rappresentano insiemi di 3 interi.
 */
public class triple {
    //ATTRIBUTI
    int num1;
    int num2;
    int num3;

    //COSTRUTTORI
    public triple(int num1,int num2,int num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    //METODI
    /**
     * Post-condizioni: calcola la somma di un array di 3 elementi
     */
    public int sum(){
        return this.num1 + this.num2 + this.num3;
    }

     /**
     * Post-condizioni: restituisce una rappresentazione testuale di submarine
     */
    @Override // solo se la segnatura è uguale (ereditarietà)
    public String toString(){
        return "Triple:{"+this.num1+", "+this.num2+", "+this.num3+"}";
    }
}
