package niit.android;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class Help extends Activity {
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        Intent intent = new Intent();
        intent.setClass(Help.this, Help.class);
        //bắt su85 kiên button cancel
        Button Cancel = (Button) findViewById(R.id.cancel);
        //hàm sử lý trả về giao diên chính
        Cancel.setOnClickListener(new Button.OnClickListener()
        {
          public void onClick(View v)
          {
        	  Intent intent = new Intent();
              intent.setClass(Help.this, CalculateActivity.class);
              startActivity(intent);
              Help.this.finish();
          }
        });
	}
}