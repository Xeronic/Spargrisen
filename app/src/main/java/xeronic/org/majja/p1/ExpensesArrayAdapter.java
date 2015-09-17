package xeronic.org.majja.p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Xeronic on 15-09-16.
 */
public class ExpensesArrayAdapter extends ArrayAdapter<Transaction> {
    private final Context context;
    private final Transaction[] transactions;

    public ExpensesArrayAdapter(Context context, Transaction[] transactions) {
        super(context, -1, transactions);
        this.context = context;
        this.transactions = transactions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_expense, parent, false);

        ImageView list_item_expense_image = (ImageView) rowView.findViewById(R.id.list_item_expense_image);
        TextView list_item_expense_text = (TextView) rowView.findViewById(R.id.list_item_expense_text);

        if (transactions[position].getCategory().equals("Boende")) {
            list_item_expense_image.setImageResource(R.drawable.boende);
        } else if (transactions[position].getCategory().equals("Fritid")) {
            list_item_expense_image.setImageResource(R.drawable.fritid);
        }else if (transactions[position].getCategory().equals("Resor")) {
            list_item_expense_image.setImageResource(R.drawable.resa);
        }else if (transactions[position].getCategory().equals("Livsmedel")) {
            list_item_expense_image.setImageResource(R.drawable.livsmedel);
        } else if (transactions[position].getCategory().equals("Övrigt")) {
            list_item_expense_image.setImageResource(R.drawable.other);
        }

        list_item_expense_text.setText(transactions[position].toString());

        return rowView;
    }

}
