
// Chứa các phem toán chứa 2 toán hạng
package niit.android;

public class Math {
	
public Math(){}
	//can bang 2 toan hang truoc khi tinh toan
	public int BalanceHead(FixedPoint a, FixedPoint b )
	{
		int n = 0;
		int j = a.fixednumber.size()-b.fixednumber.size();
	 	if(j>0)
	 	{
	 		n = a.fixednumber.size();	 		
		 	while(j != 0)
		 	{
		 		if(j>0)
			 		b.fixednumber.addFirst('0');
			 		j--;	
			 		
		 	}
	 	}
	 	else
	 	{
	 		if(j<0)
	 		{
	 			n = b.fixednumber.size();
	 			j = j*(-1);
	 			while(j != 0)
	 		 	{
	 		 		if(j>0)
	 			 		a.fixednumber.addFirst('0');
	 			 		j--;		 			 		
	 		 	}
	 			
	 		}
	 	}
	 	n = a.fixednumber.size();
	 	if(n%2!=0)
 		{
 			n = n+1;
 			a.fixednumber.addFirst('0');
 			b.fixednumber.addFirst('0');
 		}
	 	return n;
	}
	public void checkBalance (FixedPoint fx1, FixedPoint fx2)
	{
		int i = fx1.dot - fx2.dot;
		if(i>0)
		{
			while(i!=0)
			{
				fx2.fixednumber.addFirst('0');
				i--;
			}
		}
		if(i<0)
		{	i = i*(-1);
			while(i!=0)
			{
				fx1.fixednumber.addFirst('0');
				i--;
			}
		}
		int j =fx1.fixednumber.size()- fx2.fixednumber.size();
		if(j>0)
		{
			while(j!=0)
			{
				fx2.fixednumber.addLast('0');
				j--;
			}
		}
		if(j<0)
		{	j = j*(-1);
			while(j!=0)
			{
				fx1.fixednumber.addLast('0');
				j--;
			}
		}
		fx1.dot = 0;
		fx2.dot = 0;
	}		
	
	char convert(int value)
	{		
		return Character.forDigit(value, 10);		 
	}
	// phep toan cong
	public String Plus(String so1, String so2)
	{
		FixedPoint num1 = new FixedPoint(so1);
		FixedPoint num2 = new FixedPoint(so2);
		if(num1.sign != num2.sign)
		{
			if(num1.sign == true)
			{
				num1.sign = num2.sign = false;			
				return Sub(num2.toString(),num1.toString());
			}
			else
			{
				num1.sign = num2.sign = false;
				return Sub(num1.toString(),num2.toString());
			}
		}
		checkBalance(num1,num2);
		FixedPoint result = new FixedPoint();
		int mem = 0;
		int i = num1.fixednumber.size()-1;
		while(i>=0)
		{
			if(num1.fixednumber.get(i)!='.')
			{	
				mem = mem + Character.digit(num1.fixednumber.get(i), 10) + Character.digit(num2.fixednumber.get(i), 10);
								
						if(mem >= 10)
						{
							result.fixednumber.addFirst(convert(mem%10));						
							mem = mem/10;
						}
						else
						{
							
							result.fixednumber.addFirst(convert(mem));
							mem = 0;
						}					
							
			}
			else
			{
				result.fixednumber.addFirst('.');								
			}
			i--;
			if(i==-1 && mem != 0)
			{
				result.fixednumber.addFirst(convert(mem));
			}
		}
		
		result.sign = num1.sign;
		result.setDot();
		return result.toString();
		}
	// phem toan tru
	public String Sub(String sobitru,String sotru )
	{		
		FixedPoint num1 = new FixedPoint(sobitru);
		FixedPoint num2 = new FixedPoint(sotru);
		boolean sign = false;		
		if(num1.sign != num2.sign)
		{
			if(num1.sign == true)
			{
				num2.sign = true;
			}
			else
			{
				num1.sign = num2.sign = false;
			}
			return Plus(num1.toString(),num2.toString());
		}
		else
		{
			if(CompareString(num1.toString(),num2.toString())==2)
				sign = true;
			if(Compare(num1,num2)==2)
			{
				FixedPoint temp = new FixedPoint();
				temp = num1;
				num1 = num2;
				num2 = temp;
			}
			
		}
		FixedPoint result = new FixedPoint();
		int mem = 0;
		int i = num1.fixednumber.size()-1;
		while(i>=0)
		{
			if(num1.fixednumber.get(i)!='.')
			{
				mem = mem +  Character.digit(num2.fixednumber.get(i), 10);
				if( Character.digit(num1.fixednumber.get(i), 10) < mem)
				{
					result.fixednumber.addFirst(convert((10 + Character.digit(num1.fixednumber.get(i), 10))-mem));
					mem = 1;
				}
				else
				{
					result.fixednumber.addFirst(convert( Character.digit(num1.fixednumber.get(i), 10)-mem));
					mem = 0;
				}
			}
			else
			{
				result.fixednumber.addFirst('.');								
			}
			i--;
		}
		result.repair();
		result.setDot();
		if(sign == true)
		{
			result.sign = true;
		}
		return result.toString();
	}
	
	FixedPoint exMulti(FixedPoint num , int a)
	{
		FixedPoint result = new FixedPoint();
		int mem = 0;
		for(int i = num.fixednumber.size()-1; i>=0 ;i--)
		{	
			mem = mem + Character.digit(num.fixednumber.get(i), 10) * a;
			if(i != 0)
			{
				
				if(mem >= 10)
				{
					result.fixednumber.addFirst(convert(mem%10));
					mem = mem /10;
				}
				else
				{
					result.fixednumber.addFirst(convert(mem));
					mem = 0;
				}
			}
			else
			{								
				if(mem >= 10)
				{
					result.fixednumber.addFirst(convert(mem%10));
					result.fixednumber.addFirst(convert(mem/10));
				}
				else
				{
					result.fixednumber.addFirst(convert(mem));
					mem = 0;
				}					
									
			}
				
		}
		return result;
	}
 FixedPoint exPlus(FixedPoint tmp1, FixedPoint tmp2)
{
	FixedPoint exPlus = new FixedPoint();
	int mem = 0;
	int j = tmp1.fixednumber.size() - tmp2.fixednumber.size();
	if(j>0)
	{
		while(j>=0)
		{
			tmp1.fixednumber.addFirst('0');
			j--;
		}
	}
	if(j<0)
	{
		j = j*(-1);
		while(j>0)
		{
			tmp1.fixednumber.addFirst('0');
			j--;
		}
	}
	int i = tmp1.fixednumber.size()-1;
	while(i>=0)
	{
		
			mem = mem + Character.digit(tmp1.fixednumber.get(i), 10) + Character.digit(tmp2.fixednumber.get(i), 10);
							
					if(mem >= 10)
					{
						exPlus.fixednumber.addFirst(convert(mem%10));						
						mem = mem/10;
					}
					else
					{
						
						exPlus.fixednumber.addFirst(convert(mem));
						mem = 0;
					}					
								
		i--;
		if(i==-1 && mem != 0)
		{
			exPlus.fixednumber.addFirst(convert(mem));
		}
	}
	
											
	return exPlus;
}

public String Multi(String so1, String so2)
	{
		FixedPoint num1 = new FixedPoint(so1);
		FixedPoint num2 = new FixedPoint(so2);
		FixedPoint result = new FixedPoint();		
		FixedPoint tmp2 = new FixedPoint();
		int dot = (num1.fixednumber.size()- num1.dot-1) + (num2.fixednumber.size()-num2.dot-1);
		num1.removeDot();
		num2.removeDot();
		for(int i = num2.fixednumber.size()-1;i>=0;i--)
		{	
			if(i != num2.fixednumber.size()-1)
			{	
				tmp2 = exMulti(num1,Character.digit(num2.fixednumber.get(i), 10));
				int k = num2.fixednumber.size()-1-i;
				while(k>0)
				{
					tmp2.fixednumber.addLast('0');
					k--;
				}
				result = exPlus(result,tmp2);
			
			}
			else
			{
				result = exMulti(num1,Character.digit(num2.fixednumber.get(i), 10));
			}
		}	
		result.fixednumber.add(result.fixednumber.size()- dot , '.');
		result.repair();
		result.setDot();
		if(num1.sign == num2.sign)
		{
			result.sign = false;
		}
		else
		{
			result.sign = true;
			
		}
		
		return result.toString();
	}
//Ham mu co so 10
public String Exp(String val)
{
	String exp = "1";
	while(!Sub(val,"1.0").equals("-1.0"))
	{
		exp = exp + "0";
		val = Sub(val,"1.0");
	}
	exp = exp + ".0";
	return exp;
}

//Ham so sanh tong quat 2 so
 public int Compare(FixedPoint a, FixedPoint b)
	{	
	 	a.repair();
	 	a.setDot();
	 	b.repair();
	 	b.setDot();
	 	checkBalance(a, b);
	 	int i = 0;
	 	while(i != a.fixednumber.size())
	 	{
	 		if(a.fixednumber.get(i)=='.')
	 		{
	 			i++;
	 		}
	 		else
	 		{
		 		if(Character.digit((a.fixednumber.get(i)),10) > Character.digit((b.fixednumber.get(i)),10))
		 		{
		 			return 1;
		 		}
		 		else
		 		{
		 			if(Character.digit((a.fixednumber.get(i)),10) < Character.digit((b.fixednumber.get(i)),10))
		 			{
		 				return 2;
		 			}
		 			else
		 			{
		 				i++;
		 			}
		 		}
		 		
	 		}
	 	}
	 return 0;
	}
 public int CompareString(String a,String b)
 {
	 FixedPoint num1 = new FixedPoint(a);
	 FixedPoint num2 = new FixedPoint(b);
	 if(num1.sign == true && num2.sign == false)
	 {
		 return 2;
	 }
	 else
	 {
		 if(num1.sign == false && num2.sign == true)
		 {
			 return 1;
		 }
		 else
		 {
			if(num1.sign == true && num2.sign == true)
			{
				if(Compare(num1,num2)==1)
					return 2;
				else
				{
					if(Compare(num1,num2)==2)
					return 1;
				}
					
			}
			return Compare(num1,num2);
		 }
	 }
	 
 }
 int CompareDiv (FixedPoint sbc, FixedPoint sc)
 {
	 sbc.removeZeroFirst();
	 sc.removeZeroFirst();
	 if(sbc.fixednumber.size() > sc.fixednumber.size())
	 {
		 return 1;
	 }
	 else
	 {
		 if(sbc.fixednumber.size() < sc.fixednumber.size())
		 {
			 return 2;
		 }
		 else
		 {
			 int i = 0;
			 while(i!=sbc.fixednumber.size())
			 {
				 if(Character.digit(sbc.fixednumber.get(i), 10) > Character.digit(sc.fixednumber.get(i), 10))
				 {
					 return 1;
				 }
				 else
				 {
					 if(Character.digit(sbc.fixednumber.get(i), 10) < Character.digit(sc.fixednumber.get(i), 10))
					 {
						 return 2;
					 }
					 else
					 {
						 i++;
					 }
				 }
			 }
		 }
	 }
	 return 0;
 }
 void BalancePoint(FixedPoint sbc, FixedPoint sc)
	{
		int i = (sbc.fixednumber.size()-sbc.dot) - (sc.fixednumber.size()-sc.dot);
		if(i>0)
		{
			while(i>0)
			{
				sc.fixednumber.addLast('0');
				i--;
			}
		}
		if(i<0)
		{
			i = i*(-1);
			while(i>0)
			{
				sbc.fixednumber.addLast('0');
				i--;
			}
		}
		
	}
 FixedPoint exSub(FixedPoint tmp1, FixedPoint tmp2)
	{
	 	int j = tmp1.fixednumber.size()-tmp2.fixednumber.size();
	 	
	 	while(j != 0)
	 	{
	 		if(j>0)
		 		tmp2.fixednumber.addFirst('0');
		 		j--;	
		 		
	 	}
		FixedPoint exSub = new FixedPoint();
		int mem = 0;
		for(int i = tmp1.fixednumber.size()-1; i>=0 ;i--)
		{			
				mem = mem + Character.digit(tmp2.fixednumber.get(i), 10);
				if( Character.digit(tmp1.fixednumber.get(i), 10) < mem)
				{
					exSub.fixednumber.addFirst(convert(10 + Character.digit(tmp1.fixednumber.get(i), 10)-mem));
					mem = 1;
				}
				else
				{
					exSub.fixednumber.addFirst(convert(Character.digit(tmp1.fixednumber.get(i), 10)-mem));
					mem = 0;
				}					
		}
		
		return exSub;
	}
public boolean checkZero(FixedPoint a)
	{
		int i = 0;
		while (i!= a.fixednumber.size())
		{
			if(a.fixednumber.get(i)=='.')
			{
				i++;
			}
			else
			{
				if(a.fixednumber.get(i)!= '0')
				{
					return false;
				}
				else
				{
					i++;
				}
			}
		}
		return true;
	}
 FixedPoint temp_sbc(FixedPoint sbc, int length)
	{
		FixedPoint temp_sbc = new FixedPoint();
		for(int i = 0; i< length;i++)
		{
			temp_sbc.fixednumber.addLast(sbc.fixednumber.get(i));
		}
		return temp_sbc;
	}
 FixedPoint du(FixedPoint sbc,int length)
	{
		FixedPoint du = new FixedPoint();
		if(length != sbc.fixednumber.size() )
		{
			for(int i = length; i<sbc.fixednumber.size();i++)
			{
				du.fixednumber.addLast(sbc.fixednumber.get(i));
			}			
		}
		return du;
	}
 
public String  Divide(String sbc, String sc, int lim)
	{	
		
		
		FixedPoint t_sbc = new FixedPoint();
		FixedPoint du = new FixedPoint();
		FixedPoint result = new FixedPoint();
		FixedPoint Sobichia = new FixedPoint(sbc);
		FixedPoint Sochia = new FixedPoint(sc);
		BalancePoint(Sobichia, Sochia);
		Sobichia.removeZeroFirst();
		Sochia.removeZeroFirst();
		Sobichia.removeDot();
		Sochia.removeDot();
		if(checkZero(Sochia))
		{
			return "Not a number";
		}
		if(checkZero(Sobichia)) // kiem tra so bi chia va so bi chia xem chung co = 0 hay khong
		{
			
				return"0.0";			
			
		}
		boolean checkDot = false;
		
		if(CompareDiv(Sobichia, Sochia)==2)
		{
			while(CompareDiv(Sobichia,Sochia)==2)
			{
				Sobichia.fixednumber.addLast('0');
				result.fixednumber.addLast('0');
			}
			checkDot = true;
			result.fixednumber.add(1, '.');
			
		}
		else
		{
			if(CompareDiv(Sobichia,Sochia)==0) //neu so bi chia = so chia thi ket qua = 1.0
			{
				result.fixednumber.addLast('1');
				result.fixednumber.addLast('.');
				result.fixednumber.addLast('0');
				result.dot = 1;
				if(Sobichia.sign == Sochia.sign)
				{
					result.sign = false;
				}
				else
				{
					result.sign = true;
				}
				return result.toString();
			}
		}		
		t_sbc = temp_sbc(Sobichia, Sochia.fixednumber.size()); //Lay n chu so cua so bi chia voi n = chieu dai cua so chia
		du = du(Sobichia, Sochia.fixednumber.size()); // lay n chu so con lai 
		if(CompareDiv(t_sbc,Sochia)==0 && checkDot==true)
		{
			result.fixednumber.addLast('1');
			return result.toString();
		}
		else
		{
										
			while(checkZero(t_sbc)!= true || du.fixednumber.size()!=0 )
			{
				if(result.fixednumber.size()==lim)
				{
					break;
				}
								
				if(CompareDiv(t_sbc,Sochia)==2)
				{								
					if(!du.fixednumber.isEmpty())
					{
						t_sbc.fixednumber.addLast(du.fixednumber.get(0));
						du.fixednumber.remove(0);
					}
					else
					{
						if(!checkDot)
						{
						result.fixednumber.addLast('.');
						checkDot = true;
						}
						t_sbc.fixednumber.addLast('0');
					}	
				}
					int i = 0;
					while(CompareDiv(t_sbc,Sochia)!=2)
					{
						t_sbc = exSub(t_sbc, Sochia);
						i++;								
					}	
					result.fixednumber.addLast(convert(i));
					
					
		    }
			if(!du.fixednumber.isEmpty() && result.fixednumber.size() != lim)
			{
				while(!du.fixednumber.isEmpty())
				{
					result.fixednumber.addLast(du.fixednumber.get(0));
					du.fixednumber.remove(0);
				}
			}
			if(!checkDot)
			{
				result.fixednumber.addLast('.');
				result.fixednumber.addLast('0');
			}
			
			if(Sobichia.sign == Sochia.sign)
			{
				result.sign = false;
			}
			else
			{
				result.sign = true;
				
			}
			result.setDot();
			return result.toString();							
		}							
	}

}
