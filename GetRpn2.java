package tyuukan;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;

public class GetRpn2 {
	public static String[] get(String[] formula) {
		String[] form = new String[64];
		int formIndex = 0;
        Deque<String> stack = new ArrayDeque<>();
        Pattern pattern = Pattern.compile("^([1-9]\\d*|0)(\\.\\d+)?$|^(-[1-9]\\d*|0)(\\.\\d+)?$"); //数値かどうかの確認
        
        for(int i=0; i<formula.length; i++){
        	boolean number = pattern.matcher(formula[i]).matches(); //数値だとtrue
        	if (formula[i].equals("+") || formula[i].equals("-") || formula[i].equals("*") || formula[i].equals("/")) {
        		while (!stack.isEmpty()) {
        			String c = stack.getFirst();
        			if (c.equals("*") || c.equals("/")) {
        				form[formIndex] = stack.removeFirst();
        				formIndex++;
                    }
        			else {
        				break;
                    }
        		}
        		stack.addFirst(formula[i]);
        	}
        	else if (formula[i].equals("(")) {
                if (i!=0) { 
                	boolean number2 = pattern.matcher(formula[i-1]).matches(); //数値だとtrue
	                if (number2) { //2(1+1)のように数字の後に演算子なしで()が入力された時もきちんと掛け算が行われるようにする
	                	stack.addFirst("*");
	                }
                }
                stack.addFirst(formula[i]);
        	}
        	else if (formula[i].equals(")")) {
        		List<Object> list = Arrays.asList(stack.toArray());
        		int index = list.indexOf("(");
        		Deque<String> workStack = new ArrayDeque<>();
        		for (int j = 0; j <= index; j++) {
        			String c = stack.removeFirst();
        			if (!c.equals("(")) {
        				workStack.addFirst(c);
        			}
        		}
        		
        		while (!workStack.isEmpty()) {
        			String check = workStack.removeFirst();
        			while (!workStack.isEmpty()) {
	        			String c = workStack.getFirst();
	        			if (c.equals("*") || c.equals("/")) {
	        				form[formIndex] = workStack.removeFirst();
	        				formIndex++;
	                    }
	        			else {
	        				break;
	                    }
        			}
        			form[formIndex] = check;
    				formIndex++;
        		}
        	}
        	else if (number) {
        		form[formIndex] = formula[i];
				formIndex++;
            }
        	else {
        		String st = formula[i];
        		for (int j=i+1; j<formula.length; j++) {
        			if (!formula[j].equals("+") && !formula[j].equals("-") && !formula[j].equals("*") && !formula[j].equals("/") && !formula[j].equals("(") && !formula[j].equals(")")) {
        				st += formula[j];
        			}
        			else {
        				break;
        			}
        		}
        		form[formIndex] = st;
				formIndex++;
        	}
        }

        while (!stack.isEmpty()) {
        	form[formIndex] = stack.removeFirst();
			formIndex++;
        }
        
        int indexSize = 0;
        for (int i=0; i<form.length; i++) { //formは配列のサイズが余分にあるため、要素数分の配列を用意する
        	if (form[i]==null) {            //そのために、formの要素数を調べる
        		break;
        	}
        	indexSize++;
        }
        
        String[] resultForm = new String[indexSize]; //要素数分の新たな配列に入れ替える
        for (int i=0; i<indexSize; i++) {
        	resultForm[i] = form[i];
        }
        
        return resultForm;
	}

}

