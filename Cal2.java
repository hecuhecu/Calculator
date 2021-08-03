package tyuukan;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

public class Cal2 {
	public static String calc(String[] form) {
        Deque<String> stack = new ArrayDeque<>();
        Pattern pattern = Pattern.compile("^([1-9]\\d*|0)(\\.\\d+)?$|^(-[1-9]\\d*|0)(\\.\\d+)?$"); //数値かどうかの確認
        double a = 0;
        double b = 0;
        double c = 0;
        String x;
        String y;
        String z;
        boolean xIs; //上のpatternを用いて数値かどうかを確認する
        boolean yIs;
                
       /* for (int i=0; i<form.length; i++) { //逆ポーランドに変換されているかの確認
        	System.out.println(form[i]);
        }*/
        
        for (int i = 0; i < form.length; i++) {
            switch (form[i]) {
            case "+":
            	x = stack.removeFirst();
            	y = stack.removeFirst();
            	xIs = pattern.matcher(x).matches(); //数値だとtrue
            	yIs = pattern.matcher(y).matches();
	            if (xIs && yIs) { //両方数値だと計算
	            	a = Double.parseDouble(x);
		            b = Double.parseDouble(y);
		            c = b + a;
		            stack.addFirst(String.valueOf(c));
	          	}
	           	else {
	           		z = y + "+" + x; //文字が含まれるときは計算せずにそのまま
	           		stack.addFirst(z);
	           	}
                break;
            case "-":
            	x = stack.removeFirst();
            	y = stack.removeFirst();
            	xIs = pattern.matcher(x).matches();
            	yIs = pattern.matcher(y).matches();
	            if (xIs && yIs) {
	            	a = Double.parseDouble(x);
		            b = Double.parseDouble(y);
		            c = b - a;
		            stack.addFirst(String.valueOf(c));
	          	}	      
	           	else {
	           		z = y + "-" + x;
	           		stack.addFirst(z);
	           	}      
                break;
            case "*":
            	x = stack.removeFirst();
            	y = stack.removeFirst();
            	xIs = pattern.matcher(x).matches();
            	yIs = pattern.matcher(y).matches();
	            if (xIs && yIs) {
	            	a = Double.parseDouble(x);
		            b = Double.parseDouble(y);
		            c = b * a;
		            stack.addFirst(String.valueOf(c));
	          	}
	            else if (xIs && !yIs) { // x:2 y:a -> 2a
	            	z = x + y;
		           	stack.addFirst(z);
	            }
	           	else {                  // x:a y:2 -> 2a
	           		z = y + x;
		          	stack.addFirst(z);
	           	}
                break;
            case "/":
            	x = stack.removeFirst();
            	y = stack.removeFirst();
            	xIs = pattern.matcher(x).matches();
            	yIs = pattern.matcher(y).matches();
	            if (xIs && yIs) {
	            	a = Double.parseDouble(x);
		            b = Double.parseDouble(y);
		            c = b / a;
		            stack.addFirst(String.valueOf(c));
	          	}
	           	else {
	           		z = y + "/" + x;
	           		stack.addFirst(z);
	           	}
                break;
            default:
            	stack.addFirst((form[i]));
            }
        }
        
        return stack.removeFirst();
	}
}

