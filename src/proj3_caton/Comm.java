/**
 *
 * @author Josh
 */
package proj3_caton;

public class Comm {
    String date;
    String name;
    String total;

    public Comm(String date, String name, String total) {
        this.date=date;
        this.name=name;
        this.total=total;
        }
    
    public String getDate(){
        return date;
    }
    public String getTotal(){
        return total;
    }
    }