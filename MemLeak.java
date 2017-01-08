package memleak;
import java.util.Map;
/**
 * Klasa <code>MemLeak</code> reprezentuje przykładowy wyciek pamięci. Jest on 
 * spowodowany tym, że w mapie może być tylko jedno wystąpienie danego klucza, 
 * czyli klucze nie mogą się powtarzać. Klasa MemLeak początkowo nie posiada metod 
 * porównujących dwa obiekty, więc mapa nie może sprawdzić w sposób poprawny, czy 
 * dany klucz już w niej występuje. 
 * @author AleksanderSklorz
 */
public class MemLeak {
    public final String key; 
    public MemLeak(String key){
        this.key = key;
    }
    /**
     * Metoda pozwala na porównanie dwóch obiektów. Dzięki niej mapa rozpoznaje 
     * czy dany klucz już się w niej znajduje. 
     * @param o obiekt z którym porównujemy aktualny obiekt. 
     * @return true jeśli obiekty są równe, false w przeciwnym przypadku. 
     */
    public boolean equals(Object o){
        if(this == o) return true; // dodane głównie w celu optymalizacji
        if(o == null) return false; 
        if(getClass() != o.getClass()) return false; 
        return hashCode() == ((MemLeak)o).hashCode();
    }
    /**
     * Metoda zwracająca hashcode 
     * @return hashcode
     */
    public int hashCode(){
        return key.hashCode();
    }
    public static void main(String[] args) {
        try{
            Map map = System.getProperties(); 
            for(;;){
                map.put(new MemLeak("key"), "value");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
