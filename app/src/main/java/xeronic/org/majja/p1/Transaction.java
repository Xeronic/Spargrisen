package xeronic.org.majja.p1;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Xeronic on 15-09-14.
 */
public class Transaction implements Serializable {

    private int _id;
    private String title;
    private int amount;
    private String date;

    private String category;
    public Transaction(String title, int amount, String date, String category) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int get_id() {
        return _id;
    }

    public int getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        if (amount < 0) amount *= -1;
        return title + ": " + amount;
    }
}
