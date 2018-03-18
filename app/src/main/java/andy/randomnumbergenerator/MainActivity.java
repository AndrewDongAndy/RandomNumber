package andy.randomnumbergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnGen;
    EditText etLower, etUpper;
    TextView tvNum;

    ArrayList<Integer> nums;
    int lower, upper;

    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGen = findViewById(R.id.btnGen);
        etLower = findViewById(R.id.etLower);
        etUpper = findViewById(R.id.etUpper);
        tvNum = findViewById(R.id.tvNum);

        nums = new ArrayList<>();

        lower = 0; upper = 10;
        fillNums(0, 10);

        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tmpLower = Integer.parseInt(etLower.getText().toString());
                int tmpUpper = Integer.parseInt(etUpper.getText().toString());


                // if the range was changed
                if (tmpLower != lower || tmpUpper != upper) {
                    lower = tmpLower; upper = tmpUpper;
                    fillNums(lower, upper);
                }

                int removeIndex = rand.nextInt(nums.size());
                int removeValue = nums.remove(removeIndex);
                tvNum.setText(String.format(Locale.CANADA,
                        "Value generated: %d;\n%d numbers remaining.",
                        removeValue, nums.size()));

                if (nums.isEmpty()) {
                    fillNums(lower, upper);
                    tvNum.append("\nNumbers refilled.");
                }
            }
        });
    }

    private void fillNums(int lower, int upper) {
        nums.clear();
        for (int i = lower; i <= upper; ++i) {
            nums.add(i);
        }
    }
}
