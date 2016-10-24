package niit.android;
import java.util.*;
import java.util.regex.*;
import niit.android.Math;
import niit.android.Function;
public class Balan {
    
Stack<String> p = new Stack<String>() ;
//Hàm ưu tiên, đối với mỗi dấu hoặc các hàm sẽ dc gắn sữ ưu tiến bằng các số từ 0->5 , độ ưu tiên tăng dần
public int prioridad(String c)
 {
	 if ((c.equals("+")) )
         return 0;
	 if ((c.equals("-")) )
         return 1;
     if ((c.equals("*")))
         return 2;
     if ((c.equals("/")))
         return 3;
     if (c.equals("^"))
         return 4;
     if (	   c.equals("Sind")
    		 ||c.equals("Cosd")
    		 ||c.equals("Tand")
    		 ||c.equals("Sinr")
    		 ||c.equals("Cosr")
    		 ||c.equals("Tanr")
    		 ||c.equals("Sinh")
    		 ||c.equals("Cosh")
    		 ||c.equals("Tanh")
    		 ||c.equals("Log")
    		 ||c.equals("Ln")
    		 ||c.equals("EXP")
    		 ||c.equals("Cube")
    		 ||c.equals("Square")
    		 ||c.equals("Sqrt")
    		 ||c.equals("!"))
         return 5;
    return -1;
    
 }

 public boolean checkstring(String s)
 {
	 Pattern checkStringS = Pattern.compile("[0-9]+");		
	 Matcher m = checkStringS.matcher(s);		
     if (m.find())
     {
         return false;
     
 	 }
         return true;
 }
// xử lý khi nhập ko d8ung1 cú pháp, trả về đúng cú pháp xử lý của máy tính
public String repairstring(String input)
 {
	 int k = 0;
	 for(int i = 0;i<input.length();i++)
	 {
		 if(input.charAt(i)=='(')
		 {
			 k++;
		 }
		 else
		 {
			 if(input.charAt(i)==')')
			 {
				 k--;
			 }
		 }
	 }
	 if(k!=0)
	 {
		 return "Invalid Expression";
	 }
          
     String lastchar = String.valueOf(input.charAt(input.length()-1));// ký tự cuối của chuỗi truyền vào
     String firstchar = String.valueOf(input.charAt(0));// ký tự đầu của chuổi truyền vào
     // nếu ký tự đầu của chuỗi ="-" thì thêm số 0 truoc nó (- thi doi thanh -0)
     if(firstchar.equals("-")||firstchar.equals("^"))
     {
    	 input ="0" + input; 
     }
     if(lastchar.equals("^")||firstchar.equals("!"))
     {
    	 input ="0.0"; 
     }
     // nếu ký tự cuối = các dấu + - * / ^ thì thêm số 0no1truoc (3- doi thanh 3-0)
     if (lastchar.contains("+") || lastchar.contains("-") || lastchar.contains("*") || lastchar.contains("/") ||lastchar.contains("^"))
     {
         input += "0";
     }
     // doi các dau khong dung cu phap thanh cac dau theo dung cu phap
     //		"(-" đổi thành "(0-"
      input = input.replace("(-", "(0-");
     //		"-)" đổi thành "-0)"
     // tương tữ cho mấy cái sau :D
     input = input.replace("-)", "-0)");
     input = input.replace("()", "(0)");
     input = input.replace(")(", ")*(");
    // input = input.replace("IP", "I*P");
     input = input.replace("%","/100");
     input = input.replace("e", "2.7182818");
     input = input.replace("pi", "3,142592653589793238462643383279");
     input = input.replace("E", "*E");
     String s = input;
     //Them dau nhan truoc dau mo ngoac neu trc do la number
     Pattern PaBe = Pattern.compile("[0-9]\\(");
     Matcher theMatchesPaBe = PaBe.matcher(s);
     
     while(theMatchesPaBe.find())
     {
    	  String tempPaBe = theMatchesPaBe.group(0);
          String tempPaBe2 = tempPaBe.replace("(", "*(");
          s = s.replace(tempPaBe, tempPaBe2);
     }
     // Them dau nhan sau dau dong ngoac ( neu sau dau ngoac la 1 so hoac ham
     Pattern PaAf = Pattern.compile("\\)[0-9,A,S,C,T,E,L,P,EXP,R]");
     Matcher theMatchesPaAf = PaAf.matcher(s);
     while (theMatchesPaAf.find())
     {
         String tempPaAf = theMatchesPaAf.group(0);
         String tempPaAf2 = tempPaAf.replace(")", ")*");
         s = s.replace(tempPaAf, tempPaAf2);
     }
     Pattern NuBe = Pattern.compile("[0-9][A,S,C,T,E,L,P,EXP,R]");
     Matcher theMatchesNuBe = NuBe.matcher(s);
     while (theMatchesNuBe.find())
     {
         String tempNuBe = theMatchesNuBe.group(0);
         String tempNuBe2 = tempNuBe.substring(1, 2);                
         String tempNuBe3 = tempNuBe.substring(0, 1);
         tempNuBe2 = "*" + tempNuBe2;
         tempNuBe2 = tempNuBe3 + tempNuBe2;
         s = s.replace(tempNuBe, tempNuBe2);
     }
     /*
     Pattern PiNu = Pattern.compile("[I][0-9,A,S,C,T,E,L,P,EXP,R]");
     Matcher theMatchesPiNu = PiNu.matcher(s);
     while (theMatchesPiNu.find())
     {
         String tempPiNu = theMatchesPiNu.group(0);
         String tempPiNu2 = tempPiNu.replace("I", "I*");
         s = s.replace(tempPiNu, tempPiNu2);
     }
     Pattern NuPi = Pattern.compile("[0-9,)][P]");
     Matcher theMatchesNuPi = NuPi.matcher(s);
     while (theMatchesNuPi.find())
     {
         String tempNuPi = theMatchesNuPi.group(0);
         String tempNuPi2 = tempNuPi.replace("P", "*P");
         s = s.replace(tempNuPi, tempNuPi2);
     }        
     */
     Pattern Dot = Pattern.compile("[.]{2,99}");
     Matcher theMatchesDot = Dot.matcher(s);
     while (theMatchesDot.find())
     {
         String tempDot = theMatchesDot.group(0);
         s = s.replace(tempDot, ".");
     }
     return s;
 }
// chuyển sang chuỗi hậu tố
public LinkedList<String> RPN(String stringinput)
{
    LinkedList<String> result = new LinkedList<String>();
    stringinput = repairstring(stringinput);
    if(stringinput.equals("Invalid Expression"))
    {
    	result.clear();
    	return result;
    }
    int l = stringinput.length();
    int i=0;
    String c;        
    while(i!= l)
    {
    	c = String.valueOf(stringinput.charAt(i));
    	if(Character.isDigit(c.charAt(0))||c.contains("."))
    	{	      	
        	String temp = "";
        	while(i!= l && (Character.isDigit(stringinput.charAt(i))||stringinput.charAt(i)=='.'))
        	{	
	        	temp = temp + String.valueOf(stringinput.charAt(i));
	        	i++;       			       			
        	}
        	result.addLast(temp);
    	}
    	else
    	{

    		if(c.contains("+")||c.contains("-")||c.contains("*")||c.contains("/")||c.contains("^")||c.contains("S")||c.contains("C")||c.contains("T")||c.contains("L")||c.contains("E")||c.contains("A")||c.contains("!")||c.contains("R"))
    		{  
    			    			
    			if(Character.isLetter(c.charAt(0)))
    			{
    				
    				String tmp = "";
        			while((Character.isLetter(stringinput.charAt(i))) )
        			{
        				tmp = tmp + stringinput.charAt(i);
        				i++;
        			}
        			if( !p.empty() && (prioridad(tmp) <= prioridad(p.peek())))
        			{
        				while(!p.empty() && (prioridad(tmp) <= prioridad(p.peek()))&& p.peek()!="(")
        				{
        					result.addLast(p.pop()) ;
        				}
        				p.push(tmp);
        			}
        			else
        			{
        				p.push(tmp);
        			}
    			
    			}
    			else
    			{
    				if(!p.empty() && (prioridad(String.valueOf(stringinput.charAt(i))) <= prioridad(p.peek())))
    				{
    					while(!p.empty() && (prioridad(String.valueOf(stringinput.charAt(i))) <= prioridad(p.peek()))&&p.peek()!="(")
    					{      						
    						result.addLast(p.pop()) ;
    					} 
    					p.push(String.valueOf(stringinput.charAt(i)));
    				}
    				else
    				{
    					p.push(String.valueOf(stringinput.charAt(i)));
    				}
    				i++;
    				
    			}
    		}
    		else
    		{
    			if(c.contains("("))
    			{
    				p.push("(");
    				i++;
    			}
    			else
    			{
    				if(c.contains(")"))
    				{
    					while(!p.empty() && p.peek()!="(")
    					{
    						result.addLast(p.pop()) ;
    					}
    					p.pop();
    					i++;
    				}
    			}
    		}
    		
    	}
    }
    while(!p.empty())
    {       	
    	result.addLast(p.pop()) ;
    	
    }
    return result;        
}
// Hảm tính toán
public String HamTinhToan(LinkedList<String> RPN)
{    	
   if(RPN.size()==0) 
   {
	   return "Invalid Expression";
   }
  
    String val1;
    String val2;
    String c;
    String result;
    Stack<String> p = new Stack<String>();
    Math m = new Math();
    Function f = new Function();
    while(!RPN.isEmpty())
    {	
    	c = RPN.peek();
    	if(c.contains("+")||c.contains("-")||c.contains("*")||c.contains("/")||c.contains("^") || c.contains("Sqrt("))
    	{
    		RPN.poll();
    		val2 = p.pop();
    		val1 = p.pop();
    		if(c.contains("+"))
    		{
    			
    			p.push(m.Plus(val1, val2));
    		
    		} 
    		if(c.contains("-"))
    		{       			
    			p.push(m.Sub(val1, val2));
    			
    		} 
    		if(c.contains("*"))
    		{
    			       			
    			p.push(m.Multi(val1, val2));
    			
    		} 
    		if(c.contains("/"))
    		{
    			if(val2.equals("0.0"))
    			{
    				return "Not a number";
    			}
    			else
    			{
    				p.push(m.Divide(val1, val2,30));
    			}
    			
    		} 
    		if(c.contains("^"))
    		{
    			p.push(f.Exponential(val1, val2));
    		}
    		if(c.contains("Sqrt("))
    		{
    			p.push(f.Root1(val1, val2));
    		}
    		
    		
    	}
    	else
    	{
    		if(		  c.equals("Sind")
    				||c.equals("Cosd")
    				||c.equals("Tand")
    				||c.equals("Sinr")
    				||c.equals("Cosr")
    				||c.equals("Tanr")
    				||c.equals("Asin")
    				||c.equals("Acos")
    				||c.equals("Atan")
    				||c.equals("Sinh")
    				||c.equals("Cosh")
    				||c.equals("Tanh")
    				||c.equals("Asinh")
    				||c.equals("Acosh")
    				||c.equals("Atanh")
    				||c.equals("Log")
    				||c.equals("Ln")
    				||c.equals("Exp")
    				||c.equals("Cube")
    				||c.equals("Square")
    				||c.equals("Sqr")
    				||c.equals("!")
    				||c.equals("E")
    				
    				)
    		{
    			RPN.poll();
    			val1 = p.pop();
    			if(c.equals("Sind"))
    			{
    				p.push(f.Sin(val1));
    			}
    			if(c.equals("Cosd"))
    			{
    				p.push(f.Cos(val1));
    			}
    			if(c.equals("Tand"))
    			{
    				p.push(f.Tan(val1));
    			}
    			if(c.equals("Sinr"))
    			{
    				p.push(f.Sinr(val1));
    			}
    			if(c.equals("Cosr"))
    			{
    				p.push(f.Cosr(val1));
    			}
    			if(c.equals("Tanr"))
    			{
    				p.push(f.Tanr(val1));
    			}
    			if(c.equals("Sinh"))
    			{
    				p.push(f.Sinh(val1));
    			}
    			if(c.equals("Cosh"))
    			{
    				p.push(f.Cosh(val1));
    			}
    			if(c.equals("Tanh"))
    			{
    				p.push(f.Tanh(val1));
    			}
    			if(c.equals("Asin"))
    			{
    				p.push(f.Arcsin(val1));
    			}  
    			if(c.equals("Acos"))
    			{
    				p.push(f.Arccos(val1));
    			} 
    			if(c.equals("Atan"))
    			{
    				p.push(f.Arctan(val1));
    			} 
    			if(c.equals("Asinh"))
    			{
    				p.push(f.Arcsinh(val1));
    			} 
    			if(c.equals("Acosh"))
    			{
    				p.push(f.Arccosh(val1));
    			} 
    			if(c.equals("Atanh"))
    			{
    				p.push(f.Arctanh(val1));
    			}
    			if(c.equals("Ln"))
    			{
    				p.push(f.Lnx(val1));
    			}
    			if(c.equals("Exp"))
    			{
    				p.push(f.Exp(val1));
    			}
    			if(c.equals("Log"))
    			{
    				p.push(f.Log(val1));
    			}
    			if(c.equals("Square"))
    			{
    				p.push(f.Square(val1));
    			}
    			if(c.equals("Cube"))
    			{
    				p.push(f.Cube(val1));
    			}

    			if(c.equals("Sqr"))
    			{
    				p.push(f.Root(val1));
    			}
    			if(c.equals("!"))
    			{
    				p.push(f.Factorial(val1));
    			}
    			/*
    			if(c.equals("E"))
    			{
    				p.push(f.Exp(val1));
    			}*/
    			
    		}
    		else
    		p.push(RPN.poll());
    	}
    	
    }
    result = p.pop();
    if(result.contentEquals("Math Error") || result.contentEquals("Not a number"))
    {
    	return " Math Error";
    }
    else
    {
    return result;
    }
    
}

}
