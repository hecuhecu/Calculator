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
        
        for (int i = 0; i < token.length; i++) {
            switch (token[i]) {
            case "+":
            	x = stack.removeFirst();
            	y = stack.removeFirst();
	            if (Character.isDigit(x.toCharArray()[0]) && Character.isDigit(y.toCharArray()[0])) {
	            	a = Double.parseDouble(x);
		            b = Double.parseDouble(y);
		            c = b + a;
		            stack.addFirst(String.valueOf(c));
	          	}
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
	           	else {
	           		if (Character.isDigit(x.toCharArray()[0]) && !Character.isDigit(y.toCharArray()[0])) {
	           			z = x + y;
		           		stack.addFirst(z);
	           		}
	           		else {
	           			z = y + x;
		           		stack.addFirst(z);
	           		}
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
	           	else {
	           		z = y + "/" + x;
	           		stack.addFirst(z);
	           	}      
                break;
            default:
            	stack.addFirst((token[i]));
            }
        }
        
        return stack.removeFirst();
	}
}
