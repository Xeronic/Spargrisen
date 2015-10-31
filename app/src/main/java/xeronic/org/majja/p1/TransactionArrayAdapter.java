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
public class TransactionArrayAdapter extends ArrayAdapter<Transaction> {
        private final Context context;
        private final Transaction[] transactions;

        public TransactionArrayAdapter(Context context, Transaction[] transactions) {
            super(context, -1, transactions);
            this.context = context;
            this.transactions = transactions;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_item_income, parent, false);

            TextView list_item_income_category = (TextView) rowView.findViewById(R.id.list_item_income_category);
            TextView list_item_income_title = (TextView) rowView.findViewById(R.id.list_item_income_title);

            list_item_income_category.setText(transactions[position].getCategory());
            list_item_income_title.setText(transactions[position].getTitle() + ": " + transactions[position].getAmount() + "kr");

            return rowView;
        }
    
}
