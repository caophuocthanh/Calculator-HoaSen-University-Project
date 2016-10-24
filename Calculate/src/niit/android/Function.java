//Phép chứ 1 toán hạng

package niit.android;

public class Function {
	private Math m = new Math();

	public Function() {
	}

	// Ham binh phuong
	public String Square(String num) {
		return m.Multi(num, num);
	}

	// ham Lap phuong
	public String Cube(String num) {
		return m.Multi(m.Multi(num, num), num);
	}

	public String OneDivX(String x) {
		return m.Divide("1.0", x, 30);
	}

	// Ham giai thua
	public String Factorial(String x) {

		String temp = x;
		FixedPoint num = new FixedPoint(x);
		if (num.toString().equals("0.0") || num.toString().equals("1.0")) {
			return "1.0";
		}
		if (num.toString().equals("2.0")) {
			return "2.0";
		}
		while (!temp.equals("1.0")) {
			temp = m.Sub(temp, "1.0");
			x = m.Multi(x, temp);
			// x = m.Karatsuba(x, temp);

		}
		return x;
	}
	// Ham khai can bac 2

	public String Root(String a) {
		FixedPoint num = new FixedPoint(a);

		if (num.sign == true) {
			return "Math Error";
		}
		if (num.toString().equals("0.0")) {
			return "0.0";
		}
		if (num.toString().equals("1.0")) {
			return "1.0";
		}
		String xn;
		String x0;
		xn = m.Divide(a, "2.0", 30);
		x0 = "0.0";
		while (!xn.equals(x0)) {
			x0 = xn;
			xn = m.Divide((m.Plus(xn, m.Divide(a, xn, 30))), "2.0", 30);
		}
		return xn;
	}

	public String Root1(String a, String b) {
		FixedPoint num1 = new FixedPoint(a);
		FixedPoint num2 = new FixedPoint(b);

		if (num2.sign == true) {
			return "Math Error";
		}

		if (num1.toString().equals("0.0")) {
			return "Math Error!";
		}

		if (num2.toString().equals("0.0")) {
			return "0.0";
		}
		if (num2.toString().equals("1.0")) {
			return "1.0";
		}

		String kq;
		String t = m.Divide("1", num1.toString(), 30);
		kq = Exponential(num2.toString(), t);

		return kq;
	}

	// Ham x mu n ( n nguyen duong)
	public String Exponential(String a, String b) {
		FixedPoint x = new FixedPoint(a);
		FixedPoint n = new FixedPoint(b);
		String temp;
		String result;
		if (x.toString().equals("0.0") || x.toString().equals("1.0")) {
			return x.toString();
		} else {
			result = x.toString();
			temp = n.toString();
			while (!temp.equals("1.0")) {
				result = m.Multi(result, x.toString());
				temp = m.Sub(temp, "1.0");
			}
			return result;
		}
	}

	public String Exp(String a) {
		String exp = "1";
		while (!m.Sub(a, "1.0").equals("-1.0")) {
			exp = exp + "0";
			a = m.Sub(a, "1.0");
		}
		exp = exp + ".0";
		return exp;
	}

	// Ham Logarit co so e (Logarit tu nhien)
	private String Ln(String num) {
		FixedPoint input = new FixedPoint(num);
		String x = input.toString();
		if (x.equals("0.0") || x.contains("-")) {
			return "Math Error";
		}

		else if (x.equals("2.0")) {
			return "0.693148";
		} else {
			if (x.equals("1.0")) {
				return "0.0";
			} else {
				String a = "1.0";
				String b = "0.0";
				x = m.Sub(x, a);
				String c;
				String e;
				if (m.CompareString(x, "-1.0") == 1
						&& m.CompareString(x, "1.0") == 2) {
					String t = x, _ln = x;
					String k = "1.0";
					do {
						// t = -t * x * k / (k + 1);
						c = m.Plus(k, a);
						e = m.Multi(m.Sub(b, t), x);
						t = m.Divide(m.Multi(e, k), c, 12);
						_ln = m.Plus(_ln, t);
						k = m.Plus(k, "1.0");
					} while (!_ln.equals(m.Plus(_ln, t)));
					return _ln.substring(0, 9);
				} else
					// ln(1 + 1 / x) - ln(1 / x)
					return m.Sub(Ln(m.Plus(a, m.Divide(a, x, 12))),
							Ln(m.Divide(a, x, 12)));
			}
		}

	}

	public String Lnx(String num) {
		FixedPoint input = new FixedPoint(num);
		String temp = input.toString();
		if (temp.equals("0.0") || temp.contains("-")) {
			return "Math Error";
		}

		else {
			if (temp.equals("2.0")) {
				return "0.693148";
			} else {
				if (temp.equals("1.0")) {
					return "0.0";
				} else {
					if (m.CompareString(temp, "2.0") == 2
							&& m.CompareString(temp, "1.0") == 1) {
						return Ln(temp);
					} else {
						if (m.CompareString(temp, "1.0") == 2) {
							input.removeDot();
							int dot = 0;
							while (input.fixednumber.getFirst() == '0') {
								input.fixednumber.removeFirst();
								dot++;
							}
							input.fixednumber.add(1, '.');
							return m.Sub(
									Ln(input.toString()),
									m.Multi(Integer.toString(dot),
											"2.30258509299404"))
									.substring(0, 9);
						} else {
							int dot = input.dot - 1;
							input.removeDot();
							while (input.fixednumber.getLast() == '0') {
								input.fixednumber.removeLast();
							}
							input.fixednumber.add(1, '.');
							return m.Plus(
									Ln(input.toString()),
									m.Multi(Integer.toString(dot),
											"2.30258509299404"))
									.substring(0, 9);
						}
					}
				}
			}
		}

	}

	// Ham Log (Logarit co so 10)
	public String Log(String a) {
		FixedPoint x = new FixedPoint(a);
		if (x.toString().equals("1.0")) {
			return "0.0";
		} else {
			if (x.toString().equals("0.0"))
				return "Math Error";
			else {
				return m.Divide(Lnx(x.toString()), "2.30258509299404", 15);
			}
		}

	}

	// hàm chuyển từ độ -> radian
	//x(deg) = x(radian)*pi/180
	public String Radian(String a) {
		return m.Divide(m.Multi(a, "3.141592653"), "180", 15);
	}

	// hàm chuyển từ radian -> độ
	//x(radian) =x(deg)*180/pi
	public String Degrees(String a) {
		return m.Divide(m.Multi(a, "180"), "3.142592653589793", 15);// pi=
																	// 3,142592653589793238462643383279.
	}

	/*
	 * HAM SIN(x)Ta sẽ tính ket qua theo bieu thưc Talor (link:
	 * http://en.wikipedia.org/wiki/Taylor_series)Sin(x) =x -x^3/3!+
	 * x^5/5!-x^7/7!+...+ [(-1)^n]*[x^(2n+1)]/[(2n+1)!] với x tính theo radian
	 * làm sao chuyển về số radian đây? radian là so gì?????vậy thuật toán sẽ
	 * dựa trên cái chuỗi này [(-1)^n]*[x^(2n+1)]/[(2n+1)!]giá trị truyền vào sẽ
	 * là a sẽ chính cái số đầu tiên ==> gán x=aChắc chắn sẽ chạy vòng lặp sẽ
	 * chạy tới n lần. Vậy sẽ rất lâu. và tìm hiểu bik dc. Sẽ chạy tới khi
	 * esilon =1.Ta có epsilon = [x^(2n+1)]/[(2n+1)!]. Vậy chạy vòng lặp cho đến
	 * khi esilon gần =0 thì dừng lại.Truyền vào giá trị kiểu String nên ta sẽ
	 * sử dụng các hàm công trù nhân chia đã viết để giải quyết bài toánPhân
	 * tích xong. Bắt đầu code.(2n+1) = Exponential(x,m.Plus(m.Multi("2",
	 * n),"1")(-1^n) = Exponential("-1", n)=> epsilon = [x^(2n+1)]/[(2n+1)!] =
	 * m.Divide(Exponential(x,m.Plus(m.Multi("2", n)),
	 * "1"))),Factorial(m.Plus(m.Multi("2", n)), "1"))),0)=>
	 * [(-1)^n]*[x^(2n+1)]/[(2n+1)!] =m.Multi(Exponential("-1", n), epsilon)SỬ
	 * DỤNG HÀM SIN() ĐỂ XỬ LÝ CÁC HÀM CÒN LẠI LÀ COS, TAN, COSTAN
	 */
	public String Sinr(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = s.toString();
		String result = x;
		String epsilon = null;
		String n = "1";
			do {
				epsilon = m.Divide(Exponential(x, m.Plus(m.Multi("2", n), "1")),
						Factorial(m.Plus(m.Multi("2", n), "1")), 15);
				result = m.Plus(result, m.Multi(Exponential("-1", n), epsilon));
				n = m.Plus(n, "1");
			} while (m.CompareString(epsilon, "0.0000000000111") == 1);
		return result;
	
	}
	public String Cosr(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = s.toString();
		String result = "1";
		String epsilon = null;
		String n = "1";
			do {
				epsilon = m.Divide(Exponential(x, m.Multi("2", n)),Factorial(m.Multi("2", n)), 15);
				result = m.Plus(result, m.Multi(Exponential("-1", n), epsilon));
				n = m.Plus(n, "1");
			} while (m.CompareString(epsilon, "0.0000000000111") == 1);
		return result;
	}
	public String Tanr(String a) {
		FixedPoint x = new FixedPoint(a);
		return m.Divide(Sinr(x.toString()), Cosr(x.toString()), 15);
	}
	public String Sin(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = Radian(s.toString());
		String result = x;
		String epsilon = null;
		String n = "1";
		String so =s.toString();
		if(so.equals("0.0")||so.equals("180.0")||so.equals("360.0")||so.equals("90.0"))
		{
			if(so.equals("90.0"))
			{
				return "1.0";
			}
			else
			{
				return "0.0";
			}
		}
		else
		{
			do {
				epsilon = m.Divide(Exponential(x, m.Plus(m.Multi("2", n), "1")),
						Factorial(m.Plus(m.Multi("2", n), "1")), 15);
				result = m.Plus(result, m.Multi(Exponential("-1", n), epsilon));
				n = m.Plus(n, "1");
			} while (m.CompareString(epsilon, "0.0000000000111") == 1);
		}
		return result;
	}

	// cos(x) = sin(90-x)
	public String Cos(String a) {
		FixedPoint x = new FixedPoint(a);
		String result= null;
		String so =x.toString();
		if(so.equals("0.0")||so.equals("180.0")||so.equals("360.0")||so.equals("90.0"))
		{
			if(so.equals("0.0")||so.equals("360.0"))
			{
				return "1.0";
			}
			if(so.equals("180.0"))
			{
				return "-1.0";
			}
			if(so.equals("90.0"))
			{
				return "0.0";
			}
		}
		else
		{
			result= Sin(m.Sub("90.0", x.toString()));
		}
		return result;
	}

	// Ham Tan = Sin/Cos
	public String Tan(String a) {
		FixedPoint x = new FixedPoint(a);
		return m.Divide(Sin(x.toString()), Cos(x.toString()), 15);
	}

	// KẾT THÚC SIN, COS TAN, COSTAN
	/*
	 * Tiếp tục với các hàm sinh, cosh, tanh, costanh Có chuỗi taylor cũng gần
	 * giống như sin (http://en.wikipedia.org/wiki/Taylor_series) Sinh(x) =x
	 * +x^3/3!+ x^5/5!+x^7/7!+...+ [[x^(2n+1)]/[(2n+1)!] với x không phải là so
	 * radian Thay đổi ở hàm sin 1 chút. bỏ cái đổi dấu đi là dc. vì hàm sinh()
	 * ko đổi dấu.
	 */
	public String Sinh(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = s.toString();
		String result = s.toString();
		String epsilon = null;
		String n = "1";

			do {
				epsilon = m.Divide(Exponential(x, m.Plus(m.Multi("2", n), "1")),
						Factorial(m.Plus(m.Multi("2", n), "1")), 15);
				result = m.Plus(result, epsilon);
				n = m.Plus(n, "1");
			} while (m.CompareString(epsilon, "0.0000000000001") == 1);
			return result;
	}

	/*
	 * Không thể áp dụng như sin để xuy ra cosh Nên sẽ sử dung taylor để xu ly
	 * ham luong giac cosh này Ta có chuỗi taylor của cosh như sau: Cosh(x) =1
	 * +x^2/2!+ x^4/4!+x^6/6!+...+ [[x^(2n)]/[(2n)!] với x không phải là so
	 * radian chú ý giá trĩ đầu là = 1 Vậy chỉ việc thay đổi hàm sinh . Thay vì
	 * số lẽ thì làm biến thành số chẵn
	 */
	public String Cosh(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = s.toString();
		String result = "1";
		String epsilon = null;
		String n = "1";
		do {
			epsilon = m.Divide(Exponential(x, (m.Multi("2", n))),
					Factorial(m.Multi("2", n)), 15);
			result = m.Plus(result, epsilon);
			n = m.Plus(n, "1");
		} while (m.CompareString(epsilon, "0.0000000000111") == 1);
		return result;
	}

	// Tanh = Sinh/Cosh
	public String Tanh(String a) {
		FixedPoint x = new FixedPoint(a);
		return m.Divide(Sinh(x.toString()), Cosh(x.toString()), 15);
	}

	/*
	 * Tiếp tục với các hàm ARC arssin, arccos, arctan Cuối cùng đã tìm dc các
	 * chuỗi taylor
	 * 
	 * Hàm arcsin cới chuỗi taylor : arcsin =x +(1/2)(x^3/3!)+
	 * (1/2)(3/4)(x^5/5!)+(1/2)(3/4)(5/6)(x^7/7!)+...+(ko biết là cái gì :D
	 * )[[x^(2n)]/[(2n)!] thì làm theo ccach thủ công cái đoạn đó. lo gì :D
	 * http://en.wikipedia.org/wiki/Inverse_trigonometric_function Cuoi cùng
	 * chuyen ve Do Bắt đầu thôi Nhap vao so radian và ket qua l
	 */
	public String Arcsin(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = s.toString();
		String result = x;
		String tu = "1", mau = "2"; // do so dau =1/2
		String epsilon = null;
		String n = "1";
		if((m.CompareString(x,"1.0")==1)||(m.CompareString("-1.0",x)==1))
		{
			return "erro";
		}
		else
		{
		do {
			// tu=tu*(i);
			tu = m.Multi(tu, n);
			// mau=mau*(i+1)
			mau = m.Multi(mau, m.Plus(n, "1"));
			// epsilon ko sử dụng ohuong phao ở sin để tính epsilon, tân dụng số
			// t8ang của tu và mau
			epsilon = m.Divide(Exponential(x, m.Plus(n, "1")),
					Factorial(m.Plus(n, "1")), 15);
			// result = result+ (tu/mau)*(epsilon)
			result = m.Plus(result, m.Multi(m.Divide(tu, mau, 15), epsilon));
			// so=m.Plus(so, "2");
			n = m.Plus(n, "2");
		} while (m.CompareString(epsilon, "0.0000000000011") == 1);
		}
		return Degrees(result);
	}

	// arccos= (pi/2)-arcsin
	public String Arccos(String a) {
		String result = null;
		if((m.CompareString(a,"1.0")==1)||(m.CompareString("-1.0",a)==1))
		{
			return "erro";
		}
		else
		{
		result= Degrees(m.Sub(m.Divide("3.14", "2", 15), Radian(Arcsin(a))));
		}
		return result;
	}

	/*
	 * Biểu thức taylor giống với hàm sin Nhưng đây là arctan Có nghĩa là chuyển
	 * từ radian -> do. nên ko chyển về từ độ sang ra đi an khi tính toán. Vì
	 * nhap vào là so deg cuoi cùng chuyen ve do
	 */
	public String Arctan(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = s.toString();
		String result = x;
		String epsilon = null;
		String n = "1";
		if((m.CompareString(a,"1.0")==1)||(m.CompareString("-1.0",a)==1))
		{
			return "erro";
		}
		else
		{
		do {
			epsilon = m.Divide(Exponential(x, m.Plus(m.Multi("2", n), "1")),
					Factorial(m.Plus(m.Multi("2", n), "1")), 15);
			result = m.Plus(result, m.Multi(Exponential("-1", n), epsilon));
			n = m.Plus(n, "1");
		} while (m.CompareString(epsilon, "0.0000000000111") == 1);
		}
		return Degrees(result);
	}

	/*
	 * hàm arcsinh Giờ lười ghi quá
	 * http://en.wikipedia.org/wiki/Inverse_hyperbolic_function giống hàm
	 * arcsinh nhưng có đổi dấu.
	 */
	public String Arcsinh(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = s.toString();
		String result = x;
		String tu = "1", mau = "2"; // do so dau =1/2
		String epsilon = null;
		String n = "1", i = "1";
		if((m.CompareString(a,"1.0")==1)||(m.CompareString("-1.0",a)==1))
		{
			return "erro";
		}
		else
		{
		do {
			// tu=tu*(i);
			tu = m.Multi(tu, n);
			// mau=mau*(i+1)
			mau = m.Multi(mau, m.Plus(n, "1"));
			// epsilon ko sử dụng ohuong phao ở sin để tính epsilon, tân dụng số
			// t8ang của tu và mau
			epsilon = m.Divide(Exponential(x, m.Plus(n, "1")),
					Factorial(m.Plus(n, "1")), 15);
			// doi dau

			// result = result+ (tu/mau)*(epsilon)
			result = m.Plus(
					result,
					m.Multi(Exponential("-1", n),
							m.Multi(m.Divide(tu, mau, 15), epsilon)));
			i = m.Plus(i, "1");
			n = m.Plus(n, "2");
		} while (m.CompareString(epsilon, "0.0000000000011") == 1);
	}
		return result;
	}

	/*
	 * hàm arccosh http://en.wikipedia.org/wiki/Inverse_hyperbolic_function
	 * tuong tu hàm arccosh tahy doi rerult =0 và chỗi số chẵn và x^n = x^-n
	 * =1/x^n result = ln(2x)-result voi x>1
	 */
	public String Arccosh(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = s.toString();
		String result = "0";
		String tu = "1", mau = "2"; // do so dau =1/2
		String epsilon = null;
		String n = "1", i = "1";
		if((m.CompareString("1.0",a)==1))
		{
			return "erro";
		}
		else
		{
		do {
			// tu=tu*(i);
			tu = m.Multi(tu, n);
			// mau=mau*(i+1)
			mau = m.Multi(mau, m.Plus(n, "1"));
			// epsilon ko sử dụng ohuong phao ở sin để tính epsilon, tân dụng số
			// t8ang của tu và mau
			epsilon = m.Divide("1", m.Multi(Exponential(x, n), Factorial(n)),
					15);
			// doi dau
			// result = result+ (tu/mau)*(epsilon)
			result = m.Plus(result, m.Multi(m.Divide(tu, mau, 15), epsilon));
			i = m.Plus(i, "1");
			n = m.Plus(n, "2");
		} while (m.CompareString(epsilon, "0.0000000000011") == 1);
		}
		return m.Sub(Ln(m.Multi("2", x)), result);
	}

	/*
	 * Hàm arctanh Giống như hàm arctan nhưng ko doi dau vói x<1
	 */
	public String Arctanh(String a) {
		FixedPoint s = new FixedPoint(a);
		String x = s.toString();
		String result = x;
		String epsilon = null;
		String n = "1";
		if((m.CompareString(a,"1.0")==1)||(m.CompareString("0.0",a)==1))
		{
			return "erro";
		}
		else
		{
		do {
			epsilon = m.Divide(Exponential(x, m.Plus(m.Multi("2", n), "1")),
					Factorial(m.Plus(m.Multi("2", n), "1")), 15);
			result = m.Plus(result, epsilon);
			n = m.Plus(n, "1");
		} while (m.CompareString(epsilon, "0.0000000000111") == 1);
		}
		return result;
	}

}
