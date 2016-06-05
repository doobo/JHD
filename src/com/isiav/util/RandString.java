package com.isiav.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class RandString {
	
	private static RandString randstring = null;
	
	private RandString(){}

    public  String getCode(int passLength, int type)  
    {  
        StringBuffer buffer = null;  
        StringBuffer sb = new StringBuffer();  
        Random r = new Random();  
        r.setSeed(new Date().getTime());  
        switch (type)  
        {  
        case 0:  
            buffer = new StringBuffer("0123456789");  
            break;  
        case 1:  
            buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");  
            break;  
        case 2:  
            buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");  
            break;  
        case 3:  
            buffer = new StringBuffer(  
                    "a0b1c2ad1b2ce345d3fe4f6789a5b6c7d9e8f0");  
            break;  
        case 4:  
            buffer = new StringBuffer(  
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");  
            sb.append(buffer.charAt(r.nextInt(buffer.length() - 10)));  
            passLength -= 1;  
            break;  
        case 5:  
            String s = UUID.randomUUID().toString();  
            sb.append(s.substring(0, 8) + s.substring(9, 13)  
                    + s.substring(14, 18) + s.substring(19, 23)  
                    + s.substring(24));  
        }  
  
        if (type != 5)  
        {  
            int range = buffer.length();  
            for (int i = 0; i < passLength; ++i)  
            {  
                sb.append(buffer.charAt(r.nextInt(range)));  
            }  
        }  
        return sb.toString();  
    }  
    
    public String get0xString(int lo){
    	  lo = lo/8;
    	  String s="";
    	  for(int i=0;i<lo;i++)
    	  {
    	   s=s+Integer.toHexString(new Random().nextInt());
    	  }
    	  lo=lo*8;
    	  if(s.length()==lo){
    		  return s;
    	  }else if(s.length()==(lo-1)){
    		  return s+"0";
    	  }else if(s.length()==(lo-2)){
    		  return s+"00";
    	  }
    	  return s;
    }
    
//    在<min,max>之间随机取值
    public  int getIntRang(int min ,int max){
    	
    	return  (int) Math.round(Math.random()*(max-min)+min);
    }
    
    public String getUrl(String word){
    	String sb = "";
    	int i = getIntRang(1 ,10);
    	if(word.equals("")){
    		sb = "http://hao.360.cn/?src=lm&ls=n2a27c3f091";
    	}else{
		    	if(i==1){
		    		sb = "https://www.so.com/s?ie=utf-8&src=lm&shb=1&q="+word+"&ls=n2a27c3f091";
		    	}else if(i==2){
		    		sb = "https://www.so.com/s?q="+word+"&src=srp_suggst_3.2.1&fr=lm&psid="+get0xString(32)
		       			 +"&ls=n2a27c3f091";
		    	}else if(i==3){
		    		sb = "https://www.so.com/s?q="+word+"&src=srp_suggst_3.2.1&fr=lm&psid="+get0xString(32)
		    			 +"&ls=n2a27c3f091";
		    	}else if(i==4){
		    		sb = "https://www.so.com/s?q="+word+"&src=srp_suggst_hot&fr=lm&psid="+get0xString(32)
		    			 +"&ls=n2a27c3f091";
		    	}else if(i==5){
		    		sb = "https://www.so.com/s?ie=utf-8&src=dlm&shb=1&hsid="+get0xString(16)+"&ls=n2a27c3f091&q="+word;
		    	}if(i==6){
		    		sb = "https://www.so.com/s?ie=utf-8&src=dlm&shb=1&hsid="+get0xString(16)+"&ls=n2a27c3f091&q="+word;
		    	}else if(i==7){
		    		sb = "https://www.so.com/s?ie=utf-8&src=dlm&shb=1&hsid="+get0xString(16)+"&ls=n2a27c3f091&q="+word;
				}else if(i==8){
					sb = "https://www.so.com/s?ie=utf-8&src=dlm&shb=1&hsid="+get0xString(16)+"&ls=n2a27c3f091&q="+word;
				}else if(i==9){
					sb = "https://www.so.com/s?q="+word+"&src=srp_suggst_3.2.1&fr=lm&psid="+get0xString(32)
			    			 +"&ls=n2a27c3f091";
				}else if(i==10){
					sb="https://www.so.com/s?q="+word+"&src=srp&fr=dlm&psid="+get0xString(32)+"&ls=n2a27c3f091";
				}
    	}
    	return sb;
    }
    
    public static RandString getInstance(){
    	if(randstring==null){
    		return new RandString();
    	}
    	return randstring;
    }
    
    
    
//   public static void main(String args []){
////	   System.out.println(RandString.getCode(32, 3));
////	   System.out.println(get0xString(32));
////	   System.out.println(getIntRang(0, 10000));
//   }
    
}
