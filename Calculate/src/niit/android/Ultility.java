package niit.android;

public class Ultility {
	private final Math m = new Math();
	private final Function f = new Function();
	public Ultility(){}
	
	public String Eqn1(String A,String B)
	{
		FixedPoint a = new FixedPoint(A);
		FixedPoint b = new FixedPoint(B);
		if(!a.toString().equals("0.0"))
		{
			
			String temp  = m.Sub("0.0", b.toString());
			return "x = " + m.Divide(temp, a.toString(), 30);
		}
		else
		{
			if(!b.toString().equals("0.0"))
			{
				return "PT vo nghiem";
			}
			else
			{
				return "PT dung voi moi x ";
			}
		}		
	}
	public String Eqn2(String A, String B,String C)
	{
		FixedPoint a = new FixedPoint(A);
		FixedPoint b = new FixedPoint(B);
		FixedPoint c = new FixedPoint(C);
		if(a.toString().equals("0.0"))
		{
			return Eqn1(b.toString(),c.toString());
		}
		else
		{
			String delta = m.Sub(f.Square(b.toString()),m.Multi("4.0",m.Multi(a.toString(),c.toString())));
			if(m.CompareString(delta, "0.0")==2)
			{
				return "PT vo nghiem";
			}
			else
			{
				if(delta.equals("0.0"))
				{
					String temp = m.Sub("0.0", b.toString());
					return "x1 = x2 = " + m.Divide(temp, m.Multi("2.0", a.toString()),30);
				}
				else
				{
					if(m.CompareString(delta, "0.0")==1)
					{
						delta = f.Root(delta);
						String temp = m.Sub("0.0", b.toString());
						return "x1 = " + m.Divide(m.Sub(temp,delta),m.Multi("2.0", a.toString()),7) + " ,x2 = " + m.Divide(m.Plus(temp,delta),m.Multi("2.0", a.toString()),7);
					}
				}
			}
			return "Math error";			
		}
	}
	public String FE(String a)
	{
		FixedPoint fix = new FixedPoint(a);
		if(fix.fixednumber.getLast()=='0')
		{
			fix.fixednumber.removeLast();
		}
		int k = fix.dot;
		fix.removeDot();
		if(fix.toString().equals("0"))
		{
			return fix.toString()+".E+0";
		}
		if(k == 1 && fix.fixednumber.getFirst()=='0')
		{
			int i = 0;
			while(fix.fixednumber.getFirst()=='0')
			{
				fix.fixednumber.removeFirst();
				i++;
			}
			fix.fixednumber.add(1, '.');
			return fix.toString()+"E-"+Integer.toString(i);
		}
		else
		{
			if(k == 1 && fix.fixednumber.getFirst()!='0')
			{
				fix.fixednumber.add(1, '.');
				return fix.toString()+"E+0";
			}
			if(k != 1)
			{
				fix.fixednumber.add(1, '.');
				while(fix.fixednumber.getLast()=='0')
				{
					fix.fixednumber.removeLast();
				}
				return fix.toString() + "E+" + Integer.toString(k-1);
			}
		}
		
		return fix.toString();
	}
	public String Eqn3(String A1,String A2,String B1,String B2,String C1,String C2)
	{
		FixedPoint a1 = new FixedPoint(A1);
		FixedPoint a2 = new FixedPoint(A2);
		FixedPoint b1 = new FixedPoint(B1);
		FixedPoint b2 = new FixedPoint(B2);
		FixedPoint c1 = new FixedPoint(C1);
		FixedPoint c2 = new FixedPoint(C2);
		
		String D = m.Sub(m.Multi(a1.toString(), b2.toString()), m.Multi(a2.toString(), b1.toString()));
		String Dx = m.Sub(m.Multi(c1.toString(),b2.toString()), m.Multi(c2.toString(), b1.toString()));
		String Dy = m.Sub(m.Multi(a1.toString(),c2.toString()), m.Multi(a2.toString(), c1.toString()));
		 if(!D.equals("0.0"))
		 {
			 return "x = "+m.Divide(Dx, D, 7) + ",y = " + m.Divide(Dy, D, 7);
		 }
		 else
		 {
			 if(!Dx.equals("0.0")||!Dy.equals("0.0"))
			 {
				 return "He PT vo nghiem";
			 }
			 if(Dx.equals("0.0")&&Dy.equals("0.0"))
			 {
				 return "He PT vo so nghiem";
			 }
		 }
		return "Math Error";
	}

}
