//
//
//
//su dung cau truc insert(stack.getSelectionStart()	de giu lai vị tri con tro sau khi nhập
//.............................................................................
package niit.android;
import java.util.Stack;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioButton;

import niit.android.Balan;// goi class com
import android.widget.*;
public class CalculateActivity extends Activity {
	protected static final Balan a = null;
	TextView tv1;
	int TamChange = 0;// gán TamChange = 0 để xử lý nút Inv
	int TamRadio=0; //bien su ly nut rado
	Stack<String> s = new Stack<String>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// lệnh tăt title của máy tinh
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		// button help
		final Button help = (Button) findViewById(R.id.B_Help);
		// khia bao cac id
		final EditText stack = (EditText) findViewById(R.id.stack);// su dung EditTex để có thể hiện thị con trỏ
		final TextView out = (TextView) findViewById(R.id.out);
		// id cac so
		Button khong = (Button) findViewById(R.id.B_0);
		final Button mot = (Button) findViewById(R.id.B_1);
		final Button hai = (Button) findViewById(R.id.B_2);
		final Button ba = (Button) findViewById(R.id.B_3);
		final Button bon = (Button) findViewById(R.id.B_4);
		final Button nam = (Button) findViewById(R.id.B_5);
		final Button sau = (Button) findViewById(R.id.B_6);
		final Button bay = (Button) findViewById(R.id.B_7);
		final Button tam = (Button) findViewById(R.id.B_8);
		final Button chin = (Button) findViewById(R.id.B_9);
		// id cac phep tính
		Button cong = (Button) findViewById(R.id.B_cong);
		Button tru = (Button) findViewById(R.id.B_tru);
		Button nhan = (Button) findViewById(R.id.B_nhan);
		Button chia = (Button) findViewById(R.id.B_chia);
		final Button bang = (Button) findViewById(R.id.B_bang);
		final Button ngoac0 = (Button) findViewById(R.id.B_ngoac0);
		final Button ngoac1 = (Button) findViewById(R.id.B_ngoac1);
		final Button pi = (Button) findViewById(R.id.B_pi);
		final Button ln = (Button) findViewById(R.id.B_ln);
		final Button sin = (Button) findViewById(R.id.B_sin);
		final Button cos = (Button) findViewById(R.id.B_cos);
		final Button tan = (Button) findViewById(R.id.B_tan);
		final Button sinh = (Button) findViewById(R.id.B_sinh);
		final Button cosh = (Button) findViewById(R.id.B_cosh);
		final Button tanh = (Button) findViewById(R.id.B_tanh);
		final Button log = (Button) findViewById(R.id.B_log);
		final Button x2 = (Button) findViewById(R.id.B_x2);
		final Button x3 = (Button) findViewById(R.id.B_x3);
		final Button xy = (Button) findViewById(R.id.B_xy);
		final Button ni = (Button) findViewById(R.id.B_ni);
		final Button yx = (Button) findViewById(R.id.B_yx);
		final Button bax = (Button) findViewById(R.id.B_3x);
		final Button muoix = (Button) findViewById(R.id.B_10x);
		final Button can = (Button) findViewById(R.id.B_can);
		final Button phantram = (Button) findViewById(R.id.B_phantram);
		final Button motx = (Button) findViewById(R.id.B_1x);
		final Button am = (Button) findViewById(R.id.B_am);
		final Button Cham = (Button) findViewById(R.id.B_cham);
		// CAC BUTTON CHỨC NĂNG
		final Button MR = (Button) findViewById(R.id.B_MR);
		final Button MC = (Button) findViewById(R.id.B_MC);
		final Button MS = (Button) findViewById(R.id.B_MS);
		final Button Inv = (Button) findViewById(R.id.B_Inv);
		final Button Back = (Button) findViewById(R.id.B_back);
		final Button CE = (Button) findViewById(R.id.B_CE);
		final Button TOI = (Button) findViewById(R.id.B_toi);
		final Button LUI = (Button) findViewById(R.id.B_lui);
		final Button C = (Button) findViewById(R.id.B_C);
		final Button Exp = (Button) findViewById(R.id.B_Exp);
		// RADIO button
		RadioButton RBDeg=(RadioButton)findViewById(R.id.radio0);
    	RadioButton RBRad=(RadioButton)findViewById(R.id.radio1);
    	//hàm su ly nut radio
    	RBDeg.setOnClickListener(new RadioGroup.OnClickListener() { //bắt sự kiện khi Click
    		public void onClick(View v){
    		TamRadio=0;
    		}
    		});
    	RBRad.setOnClickListener(new RadioGroup.OnClickListener() { //bắt sự kiện khi Click
    		public void onClick(View v){
    		TamRadio=1;
    		}
    		});
    	//hàm su ly nut help
		help.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		        Intent intent = new Intent();
	            intent.setClass(CalculateActivity.this, Help.class);
	            startActivity(intent);
	             CalculateActivity.this.finish();
			}
		});
		// hàm sử lý dấu bằng củng với các xử lý của nó
		bang.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				out.setText("0");
				// goi ký phap Balan
				Balan b = new Balan();
				String test = b.HamTinhToan(b.RPN(stack.getText().toString()));// tinh toán chuỗi lấy từ stack va gan vào tstet

				boolean j = false; // tạo biến boolean j =false
				int d = 0; // tạo biến int d =0
				try {
					for (int i = 0; i < test.length(); i++) {

						if (test.charAt(i) != '.' && test.charAt(0) != '-')// nếu ký tu vi tri thu i khac i va vi tri thu 0 khac "-"
						{
							if (!Character.isDigit(test.charAt(i)))// kiem tra ky tu i này không phải là so hay ko?
							{
								j = true;// neu dung thi trả về j= true
								break; // đồng thời dừng chuỗi xử lý
							}
						} else // ngược lại
						{
							if (test.charAt(i) == '.') // nếu ký tự ở vị trí i ="."
							{
								d++; // thì d +1
							}

						}
					}
					// kết thúc vong lặp for kiem tra các ket quả của j
					if (j == true || d > 1) // kiem tra neu j =true hoặc d>1
					{
						stack.setText("Math Error");// thông báo báo lỗi "Math Error" tren stack
					} else if (j == false) {
						out.setText(test);
					}
				} catch (Exception e) {
					stack.setText("Math Error or Invalid Expression");// thông báo lỗi "Math Error or Invalid Expression" tren stack
				}
			
			}

		});
		// hàm sủ lý dau "-" (âm), "." ( dấu chấm)
		am.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "-");
			}
		});
		Cham.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), ".");
			}
		});
		// căn thức bậc 2
		can.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "Sqr(");
			}
		});
		// phần trăm
		phantram.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "%");
			}
		});
		motx.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// stack.setText(stack.getText()+"1/");
				stack.getText().insert(stack.getSelectionStart(), "(1/");
			}
		});
		x2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "^2");
			}
		});
		x3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "^3");
			}
		});
		xy.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "^");
			}
		});
		ni.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "!");
			}
		});
		bax.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "3Sqrt(");
			}

		});
		muoix.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "10^");
			}
		});
		yx.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "Sqrt(");
			}
		});
		ngoac0.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "(");
			}
		});
		ngoac1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), ")");
			}
		});

		pi.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "pi"); // hiển thịpi="3.141592653589793"
			}
		});
		// Xử lý các nút giao diện 1 liên quan gia diện 2
		//
		// ta sử lý hiện thị như sau
		// nếu TamChange==0 thì xuất ra stack chính cái button đó của giao diện
		// 1
		// ngược lại ta xuất ta stack của button đó của giao hiện 2
		// ở đây ta phải sử lý các button như:
		// sin - asin
		// cos - acos
		// tan - atan
		// sinh - asinh
		// cosh - acosh
		// tahn - atanh
		// Ln - e^x
		sin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (TamChange == 0) {
					if(TamRadio==0)
					{
						stack.getText().insert(stack.getSelectionStart(), "Sind(");
					}
					else
					{
						stack.getText().insert(stack.getSelectionStart(), "Sinr(");
					}
				} else {
					stack.getText().insert(stack.getSelectionStart(), "Asin(");
				}
			}
		});
		cos.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (TamChange == 0) 
				{
					if(TamRadio==0)
					{
						stack.getText().insert(stack.getSelectionStart(), "Cosd(");
					}
					else
					{
						stack.getText().insert(stack.getSelectionStart(), "Cosr(");
					}
				} 
				else 
				{
					stack.getText().insert(stack.getSelectionStart(), "Acos(");
				}
			}
		});
		tan.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (TamChange == 0) {
					if(TamRadio==0)
					{
						stack.getText().insert(stack.getSelectionStart(), "Tand(");
					}
					else
					{
						stack.getText().insert(stack.getSelectionStart(), "Tanr(");
					}
				} else {
					stack.getText().insert(stack.getSelectionStart(), "Atan(");
				}
			}
		});
		sinh.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (TamChange == 0) {
					stack.getText().insert(stack.getSelectionStart(), "Sinh(");
				} else {
					stack.getText().insert(stack.getSelectionStart(), "Asinh(");
				}
			}
		});
		cosh.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (TamChange == 0) {
					stack.getText().insert(stack.getSelectionStart(), "Cosh(");
				} else {
					stack.getText().insert(stack.getSelectionStart(), "Acosh(");
				}
			}
		});

		tanh.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (TamChange == 0) {
					stack.getText().insert(stack.getSelectionStart(), "Tanh(");
				} else {
					stack.getText().insert(stack.getSelectionStart(), "Atanh(");
				}
			}
		});
		ln.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (TamChange == 0) {
					stack.getText().insert(stack.getSelectionStart(), "Ln(");
				} else {
					stack.getText().insert(stack.getSelectionStart(), "e^");
				}
			}
		});
		// kết thuc su lý cac nut giao dien 1 + giao dien 2
		// ham log
		log.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "Log(");
			}
		});
		// cac so tu 0-> 9
		khong.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "0");
			}
		});
		mot.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "1");

			}
		});
		hai.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "2");
			}
		});
		ba.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "3");
			}
		});
		bon.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "4");
			}
		});
		nam.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "5");
			}
		});
		sau.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "6");
			}
		});
		bay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "7");
			}
		});
		tam.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "8");
			}
		});
		chin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "9");
			}
		});

		// cac phep toan co ban + - * /
		cong.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "+");

			}
		});
		tru.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "-");
			}
		});
		nhan.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "*");

			}
		});
		chia.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stack.getText().insert(stack.getSelectionStart(), "/");
			}
		});

		// su lý thay đổi text hiện thị trên các button để chuyển sang giao diên
		// 2 của máy tính
		// hiện tại chứ lấy dc Id giao diên 2
		Inv.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				switch (TamChange) {
				case 0: // khi case 0 thì giao dien 2
					sinh.setText("asinh");
					sin.setText("asin");
					cos.setText("acos");
					cosh.setText("acosh");
					tan.setText("atan");
					tanh.setText("atanh");
					ln.setText("e^x");
					TamChange = 1;
					break;
				case 1:// khi case 1 thì giao dien 1
					sinh.setText("sinh");
					sin.setText("sin");
					cos.setText("cos");
					cosh.setText("cosh");
					tan.setText("tan");
					tanh.setText("tanh");
					ln.setText("ln");
					TamChange = 0;
					break;
				}
			}
		});
		// Sử lý nút xóa lùi
		Back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				int DemChuoi = stack.getText().toString().length();// dem so ký tự của chuỗi
				try {
					int tam = stack.getSelectionEnd() - 1;// lây vị trí con trỏ hiện tại
					stack.setText(stack.getText().subSequence(0, stack.getSelectionEnd() - 1).toString()+ stack.getText().subSequence(stack.getSelectionEnd(),DemChuoi).toString());
					stack.setSelection(tam);
				} catch (Exception e) {
					stack.setText(stack.getText());
				}

			}
		});
		C.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				stack.setText("");
				out.setText("");
			}
		});

		CE.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CharSequence a = stack.getText();
				for (int i = a.length() - 1; i >= 0; i--)// i chạy từ length chuỗi lấy từ stacl đến 0
				{
					if (a.charAt(i) == '+' || a.charAt(i) == '-'|| a.charAt(i) == '*' || a.charAt(i) == '/') {
						break;
					} else {
						// nếu găp các dâu + - * chi thì thoát ra khỏi chuỗi xử lý
						int DemChuoi = stack.getText().toString().length();// dem so ký tự của chuỗi
						try {
							int tam = stack.getSelectionEnd() - 1;// lây vị trí con trỏ hiện tại
							stack.setText(stack.getText().subSequence(0, stack.getSelectionEnd() - 1).toString()+ stack.getText().subSequence(stack.getSelectionEnd(),DemChuoi).toString());
							stack.setSelection(tam);
						} catch (Exception e) {
							stack.setText(stack.getText());
						}
					}
				}
			}
		});
		// chuc nang di chuyen con tro lùi
		LUI.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				int cursor = stack.getSelectionStart();// lấy vị trí con trỏ hiện tại
				if (cursor == 0) {
					stack.setSelection(cursor);// nêu vi tri nay =0 thi giu nguyen vi tri
				} else {
					stack.setSelection(cursor - 1);// nguoc lại thì -1 ( lùi lại 1)
				}
			}
		});
		// chuc nang di chuyen con tro tới
		TOI.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				int cursor = stack.getSelectionStart();// lấy vị trí con trỏ hiện tại
				if (cursor == stack.getText().length()) {
					stack.setSelection(cursor);// nêu vi tri nay =chiều dài của chuỗi hiện tại thi giu nguyen vi tri
				} else {
					stack.setSelection(cursor + 1);// nguuo7c5 lại thì +1 ( tiến tới 1)
				}
			}
		});

		MS.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (stack.getText().toString() == "") // nếu satck rỗng
				{
					stack.setText("");// stack =""
				} else {
					s.push(out.getText().toString());// ngược lại thì push stack vào biến s
					stack.setText("");// stack =""
				}
			}
		});
		MC.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				s.clear();// xóa dữ liệu trong stack
				stack.setText("");
			}
		});
		MR.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (s.isEmpty())// nếu satck rỗng
				{

					stack.setText(""); // stack =""
				} else {
					String a= s.pop();
					stack.getText().insert(stack.getSelectionStart(), a); // ngược lại pop từ s ra satck
				}
			}
		});
		Exp.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				/*
				 * String z =out.getText().toString(); FixedPoint s = new
				 * FixedPoint(z); out.setText(m.Exp(s.toString()));
				 */
				stack.getText().insert(stack.getSelectionStart(), "E");
			}
		});
	}
}
