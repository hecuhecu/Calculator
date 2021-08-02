package tyuukan;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class GetRpn2 {
	public static String get(String form) {
		String[] formula  = form.split("");
		StringBuilder resultForm = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();
        
        for(int i=0; i<formula.length; i++){    		
            switch (formula[i]) {
                case "+":
                case "-":
                	if (resultForm.length()==0) {
                		resultForm.append("("+formula[i]+formula[i+1]+")");
                		i++;
                		break;
                	}
                	else if (formula[i-1].equals("(") && formula[i+2].equals(")")) {
                		resultForm.append("("+formula[i]+formula[i+1]+")");
                		i++;
                		break;
                	}
                    while (!stack.isEmpty()) {
                        String c = stack.getFirst();
                        if (c.equals("*") || c.equals("/")) {
                            resultForm.append(stack.removeFirst());
                        } else {
                            break;
                        }
                    }
                    stack.addFirst(formula[i]);
                    break;
                case "*":
                case "/":
                	stack.addFirst(formula[i]);
                    break;
                case "(":
                	if (i!=0) { //()が先頭の時は以下の動作がエラーになる
	                	if (Character.isDigit(formula[i-1].toCharArray()[0])) { //2(1+1)のように数字の後に演算子なし
	                		stack.addFirst("*");                                //で()が入力された時、掛け算が行われるようにする
	                	}
	                	else if (formula[i+1].equals("-")) { //case "-" で(-2)のような入力の時に括弧をつけ足してresultFormに
	                		break;                           //追加してるため、余分に追加しないようにbreak
	                	}
                	}
                	stack.addFirst(formula[i]);
                    break;
                case ")":
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
                        resultForm.append(workStack.removeFirst());
                    }
                    break;
                default:
                    // 数値の場合
                    resultForm.append(formula[i]);
                    break;
            }
        }

        while (!stack.isEmpty()) {
            resultForm.append(stack.removeFirst());
        }
        return resultForm.toString();
	}

}
