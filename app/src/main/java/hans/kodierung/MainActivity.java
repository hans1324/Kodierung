package hans.kodierung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Button btnGo;
    TextView tvMorse;
    TextView tvEntropy;
    HashMap<Character,String> mapMorse;

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
        tvMorse.setTextSize(32);
        mapMorse = new HashMap<>();
        mapMorse.put(' ', "   ");
        mapMorse.put('0', "-----");
        mapMorse.put('1', ".----");
        mapMorse.put('2', "..---");
        mapMorse.put('3', "...--");
        mapMorse.put('4', "....-");
        mapMorse.put('5', ".....");
        mapMorse.put('6', "-....");
        mapMorse.put('7', "--...");
        mapMorse.put('8', "---..");
        mapMorse.put('9', "----.");

        putCapital('A', ".-  ");
        putCapital('B', "-...  ");
        putCapital('C', "-.-.  ");
        putCapital('D', "-..  ");
        putCapital('E', ".  ");
        putCapital('F', "..-.  ");
        putCapital('G', "--.  ");
        putCapital('H', "....  ");
        putCapital('I', "..  ");
        putCapital('J', ".---  ");
        putCapital('K', "-.-  ");
        putCapital('L', ".-..  ");
        putCapital('M', "--  ");
        putCapital('N', "-.  ");
        putCapital('O', "---  ");
        putCapital('P', ".--.  ");
        putCapital('Q', "--.-  ");
        putCapital('R', ".-.  ");
        putCapital('S', "...  ");
        putCapital('T', "-  ");
        putCapital('U', "..-  ");
        putCapital('V', "...-  ");
        putCapital('W', ".--  ");
        putCapital('X', "-..-  ");
        putCapital('Y', "-.--  ");
        putCapital('Z', "--..  ");
    }

    public void putCapital(char latinCapital, String morse) {
        /**
         * Add a letter to the morse alphabet.
         * Both the given capital and the corresponding "Kleinbuchstabe" are added to mapMorse
         *  using the ascii property:
         *  (lowercase) = (capital) + 32
         */
        morse = morse.replace('.', '\u2219'); // Use a middle point
        mapMorse.put(latinCapital, morse);
        char lowercase = (char)(32 + (int) latinCapital);
        mapMorse.put(lowercase, morse);
    }

    public float entropy(String s) {
        /**
         * Calculate the entropy of a text.
         * @param: s input text String
         * @return: float entropy = - sum_i( p_i * log_2(p_i) ) where p_i = #n_i / sum_i( #n_i )
         */

        // Map the characters: Citing the sheet...
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            Integer val = map.get(c);
            if(val != null){
                map.put(c, new Integer(val + 1));
            }else{
                map.put(c,1);
            }
        }

        // Calculate entropy...
        int N = s.length(); // Number of characters
        float entropy = 0; // Prepare sum
        for( char c : map.keySet() ) {
            // S += - p_i * log_2(p_i)
            float p_i = ( (float)map.get(c) ) / (float)N;
            entropy -= p_i * Math.log(p_i) / Math.log(2);
        }

        return entropy;
    }

    public String morse(String s) {
        /**
         * Translate a text into the morse alphabet.
         * @param: s input text String
         * @return A String representing the morse code in a human-readable way: '_' long, '.' short, ' ' sign separator, '   ' word separator
         */
        if( s.length() == 0 ) // Mind the case text.length()==0
            return "";
        StringBuffer morseBuffer = new StringBuffer();
        char character;
        String mCharacter; // One character in morse code
        for( int i = 0; i < s.length(); i++ ) { // Iterate through input text
            character = s.charAt(i); // get current character
            mCharacter = mapMorse.get(character); // Look up Morse
            if( mCharacter != null )
                morseBuffer.append(mCharacter); // Write it to output StringBuffer
        }
        return morseBuffer.toString(); //".... ._ ._.. ._.. ___   .__ . ._.. _";
    }
}
