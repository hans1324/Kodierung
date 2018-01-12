package hans.kodierung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Button btnGo;
    TextView tvMorse;
    TextView tvEntropy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeApp();
    }

    public void initializeApp() {
        this.etInput   = (EditText) findViewById(R.id.text_input);
        this.btnGo     = (Button)   findViewById(R.id.button_start);
        this.tvMorse   = (TextView) findViewById(R.id.textView_morse);
        this.tvEntropy = (TextView) findViewById(R.id.textView_entropy);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etInput.getText().toString();
                tvMorse.setText( morse(text) );
                tvEntropy.setText( String.valueOf( entropy(text) ) );
            }
        });
    }

    public float entropy(String s) {
        /**
         * Calculate the entropy of a text.
         * @param: s input text String
         * @return: entropy = sum_i( p_i * log_2(p_i) ) where p_i = #n_i / sum_i( #n_i )
         */
        //TODO: Implement this
        return (float) 2.2;
    }

    public String morse(String s) {
        /**
         * Translate a text into the morse alphabet.
         * @param: s input text String
         * @return A String representing the morse code in a human-readable way: '_' long, '.' short, ' ' sign seperator, '   ' word seperator
         */
        //TODO: Implement this. Mind the case text.length()==0
        return ".... ._ ._.. ._.. ___   .__ . ._.. _";
    }
}
