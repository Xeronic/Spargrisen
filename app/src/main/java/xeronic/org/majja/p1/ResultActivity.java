package xeronic.org.majja.p1;

import android.app.Activity;
import android.content.Intent;
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

        TextView total_income_text = (TextView) findViewById(R.id.total_income_text);
        TextView total_expense_text = (TextView) findViewById(R.id.total_expense_text);

        DBHandler db_handler = new DBHandler(this);
        Transaction[] total_income_transactions = db_handler.getAllIncomes();
        Transaction[] total_expense_transactions = db_handler.getAllExpenses();

        int total_income = 0, total_expense = 0;

        for (Transaction t : total_income_transactions) {
            total_income += t.getAmount();
        }

        for (Transaction t : total_expense_transactions) {
            total_expense += t.getAmount();
        }

        total_income_text.setText(getString(R.string.total_income_text) + " " + total_income);
        total_expense_text.setText(getString(R.string.total_expense_text) + " " + total_expense);

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
}
