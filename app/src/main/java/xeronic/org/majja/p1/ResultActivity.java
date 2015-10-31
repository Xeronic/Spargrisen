package xeronic.org.majja.p1;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        DBHandler db_handler = new DBHandler(this);
        TextView total_income_text = (TextView) findViewById(R.id.total_income_text);
        TextView total_expense_text = (TextView) findViewById(R.id.total_expense_text);
        TextView balance_text = (TextView) findViewById(R.id.balance_text);

        Transaction[] total_income_transactions = db_handler.getAllIncomes();
        Transaction[] total_expense_transactions = db_handler.getAllExpenses();

        long total_incomes = sumTransactions(total_income_transactions);
        long total_expenses = sumTransactions(total_expense_transactions);

        total_income_text.setText(getString(R.string.total_income_text) + " " + total_incomes);
        total_expense_text.setText(getString(R.string.total_expense_text) + " " + (-1 * total_expenses));

        long balance = total_incomes - -(total_expenses);

        SharedPreferences sp = getSharedPreferences(getString(R.string.preference_file_name), Activity.MODE_PRIVATE);
        String firstname = sp.getString("firstname", "");
        String lastname = sp.getString("lastname", "");
        String username = firstname + " " + lastname;

        if (balance > 0) {
            balance_text.setText(username + ",\n" + getString(R.string.good_balance_message));
        } else if (balance < 0) {
            balance_text.setText(username + ",\n" + getString(R.string.bad_balance_message));
        } else {
            balance_text.setText(username + ",\n" + getString(R.string.neutral_balance_message));
        }

        setListeners();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setListeners() {
        TextView income_details = (TextView) findViewById(R.id.income_detail_button);
        income_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, IncomeListActivity.class);
                startActivity(intent);
            }
        });

        TextView expense_details = (TextView) findViewById(R.id.expense_detail_button);
        expense_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ExpenseListActivity.class);
                startActivity(intent);
            }
        });
    }

    private long sumTransactions(Transaction[] transactions) {
        long total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }
}
