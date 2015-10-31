package xeronic.org.majja.p1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment {

    DBHandler db_handler;

    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        db_handler = new DBHandler(getActivity());

        Transaction[] transactions = db_handler.getAllTransactions();
        TextView balance_text = (TextView) view.findViewById(R.id.balance_text);

        balance_text.setText(getString(R.string.current_balance) + sumTransactions(transactions));

        return view;
    }

    private long sumTransactions(Transaction[] transactions) {
        long total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }

}
