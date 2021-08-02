package tyuukan;
import java.util.ArrayDeque;
import java.util.Deque;

public class Cal2 {
	public static String calc(String form) {
		String[] token = form.split("");
        Deque<String> stack = new ArrayDeque<>();
        double a = 0;
        double b = 0;
        double c = 0;
        String x;
        String y;
        String z;
                
        /*for (int i=0; i<token.length; i++) { //逆ポーランドに変換されているかの確認
        	System.out.println(token[i]);
        }*/
        
        for (int i = 0; i < token.length; i++) {
            switch (token[i]) {
            case "+":
            	for (int k=0; k<token.length; k++) {
                	System.out.println(stack);
                }
            	x = stack.removeFirst();
            	y = stack.removeFirst();
	            if (Character.isDigit(x.toCharArray()[0]) && Character.isDigit(y.toCharArray()[0])) {
	            	a = Double.parseDouble(x);
		            b = Double.parseDouble(y);
		            c = b + a;
		            stack.addFirst(String.valueOf(c));
	          	}
	            else if ((x.contains("-") && Character.isDigit(y.toCharArray()[0])) || (Character.isDigit(x.toCharArray()[0]) && y.contains("-"))) {
	            	a = Double.parseDouble(x);
	            	b = Double.parseDouble(y);
	            	c = b + a;
	            	stack.addFirst(String.valueOf(c));
	            }
	            /*else if (x.contains("-") && !Character.isDigit(y.toCharArray()[0])) {
	            	a = Double.parseDouble(x);
	            	b = Double.parseDouble(y);
	            	c = b + a;
	            	stack.addFirst(String.valueOf(c));
	            }*/
	           	else {
	           		z = y + "+" + x;
	           		stack.addFirst(z);
	           	}
                break;
            case "-":
            	x = stack.removeFirst();
            	y = stack.removeFirst();
	            if (Character.isDigit(x.toCharArray()[0]) && Character.isDigit(y.toCharArray()[0])) {
	            	a = Double.parseDouble(x);
		            b = Double.parseDouble(y);
		            c = b - a;
		            stack.addFirst(String.valueOf(c));
	          	}
	            else if (x.contains("-") || y.contains("-")) {
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
	            if (Character.isDigit(x.toCharArray()[0]) && Character.isDigit(y.toCharArray()[0])) {
	            	a = Double.parseDouble(x);
		            b = Double.parseDouble(y);
		            c = b * a;
		            stack.addFirst(String.valueOf(c));
	          	}
	            else if (x.contains("-") || y.contains("-")) {
	            	a = Double.parseDouble(x);
	            	b = Double.parseDouble(y);
	            	c = b * a;
	            	stack.addFirst(String.valueOf(c));
	            }
	            else if (Character.isDigit(x.toCharArray()[0]) && !Character.isDigit(y.toCharArray()[0])) {
	            	z = x + y;
		           	stack.addFirst(z);
	            }
	           	else {
	           		z = y + x;
		          	stack.addFirst(z);
	           	}
                break;
            case "/":
            	x = stack.removeFirst();
            	y = stack.removeFirst();
	            if (Character.isDigit(x.toCharArray()[0]) && Character.isDigit(y.toCharArray()[0])) {
	            	a = Double.parseDouble(x);
		            b = Double.parseDouble(y);
		            c = b / a;
		            stack.addFirst(String.valueOf(c));
	          	}
	            else if (x.contains("-") || y.contains("-")) {
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
            case "(":
            	stack.addFirst(token[i+1]+token[i+2]);
            	i+=3;
                break;
            default:
            	stack.addFirst((token[i]));
            }
        }
        
        return stack.removeFirst();
	}
}
