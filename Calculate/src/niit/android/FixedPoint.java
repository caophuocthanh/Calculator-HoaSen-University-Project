package niit.android;

import java.util.LinkedList;

public class FixedPoint 
{
	//Khai báo biến
	public LinkedList<Character> fixednumber; 
	public int dot;
	public boolean sign = false;
	//Hàm chuyển số thành số fixedpoint 
	public FixedPoint(String input)
	{		
		fixednumber = new LinkedList<Character>();	// khai báo chuỗi fixednumber có kiểu linledList 		
		char[] ch = input.toCharArray();
		int i = 0;
		boolean checkdot = false;
		while(i != ch.length)
		{		
			if(ch[i]=='-')// nếu thấy - thì bật cờ sign =true;
			{
				this.sign = true;
				
			}
			else
			{
				
				if(ch[i]=='.') //nếu thấy . thì bật cờ checkdot=true;
				{
					checkdot = true;
				}
				fixednumber.addLast(ch[i]);// bỏ từng phần của chuỗi input lần lượt vào fixednaumber .
			}
			i++;
		}
		if(checkdot == false)//neu khong co dau . thi tu tham dau .0 vao de bien thanh so thuc
		{
			fixednumber.addLast('.');
			fixednumber.addLast('0');
		}
		else//neu vi tri cuoi cung la dau cham thi tu tham so 0 vao
		{
			if(fixednumber.getLast()=='.')
			{
				fixednumber.addLast('0');
			}
		}
		while(fixednumber.get(1)!='.')//[0-9] --> k lam j het, 0[0-9] thi remove so 0 
		{
			if(fixednumber.get(0)!='0')
			{
				break;
			}
			else
			{
				fixednumber.remove(0);
			}
		}
		while(fixednumber.get(fixednumber.size()-2)!='.')// nếu [0-9].000 thì xóa đến khi =[0-9].0
		{
			if(fixednumber.get(fixednumber.size()-1)!='0')
			{
				break;
			}
			else
			{
				fixednumber.remove(fixednumber.size()-1);
			}
		}
		for(int j = 0;j<fixednumber.size();j++)// lưu lại vị trí dấu chấm vào dot
		{
			if(fixednumber.get(j)=='.')
			{
				this.dot = j;
				break;
			}
		}
		// nếu chuỗi là "0.0" thì sign =false (ko phải là số âm)
		if(fixednumber.size()==3&&fixednumber.get(0)=='0'&&fixednumber.get(1)=='.'&&fixednumber.get(2)=='0')
		{
			this.sign = false;
		}	
	}	
	//tao moi' fixednumber cuc bo (khong lien quan toi fixednumber cu) 
	//Định nghĩa kiểu Fixedpoint
	public  FixedPoint()
	{
		fixednumber = new LinkedList<Character>();
		this.dot = 0;
		this.sign = false;
	}
	//Hàm loại bỏ dấu chấm
	public void removeDot()
	 {
		int i = 0;
		while(i<fixednumber.size())
		{
			if(fixednumber.get(i)=='.')
			{
				fixednumber.remove(i);
				break;
			}
			else
			{
				i++;
			}
		}
	 }
	//Hàm loại bỏ số không ở đầu
	public void removeZeroFirst()
	{
		while(fixednumber.get(0)=='0' && fixednumber.size()!=1)
		{
			fixednumber.remove(0);
		}
	}
	//Hma2 Loau5 bỏ số không ở cuối
	public void removeZeroLast()
	{
		while(fixednumber.get(fixednumber.size()-1)=='0' && fixednumber.size()!=1)
		{
			fixednumber.remove(fixednumber.size()-1);
		}
	}
	//tao ham rieng de tie.n goi khi can
	//Hàm loại bỏ bớt số không nếu sau dấu chấm có nhiều hơn 1 số không (vd:5.02000->5.02)
	public void repair()
	{
		while(fixednumber.get(1)!='.')
		{
			if(fixednumber.get(0)!='0')
			{
				break;
			}
			else
			{
				fixednumber.remove(0);
			}
		}
		while(fixednumber.get(fixednumber.size()-2)!='.')
		{
			if(fixednumber.get(fixednumber.size()-1)!='0')
			{
				break;
			}
			else
			{
				fixednumber.remove(fixednumber.size()-1);
			}
		}
	}//co the du*
	public boolean checkInteger()
	{
		if(fixednumber.getLast()=='0' && fixednumber.get(fixednumber.size()-2)=='.')
		{
			fixednumber.removeLast();
			fixednumber.removeLast();
			return true;
		}
		else
			return false;
	}
	// Hàm đưa dấu chấm vào lại số vừa sử lý hoàn thiện
	public void setDot()
	{
		int i = 0;
		while(fixednumber.get(i)!='.')
		{
			if(fixednumber.get(i)=='.')
			{
				this.dot = i;
				break;
			}
			else
			{
				i++;
			}
		}
	}
	
	//Hàm chuyển từ Fixpoint -> String
	 public String toString()
	{
		 String s = "";
		 int i = 0;
		 while(i!= fixednumber.size())
		 {
			 s = s + String.valueOf(fixednumber.get(i));
			 i++;
		 }
		 if(sign == true)
		 {
			 s = "-"+s;
		 }
		 return s;
		 				
		
	}
	 
}
