package tyuukan;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class GetRpn2 {
	public static String get(String form) {
		char[] formula  = form.toCharArray();
		StringBuilder resultForm = new StringBuilder(formula.length);
        Deque<Character> stack = new ArrayDeque<>();
        
        for(int i=0; i<formula.length; i++){
            switch (formula[i]) {
                case '+':
                case '-':
                    while (!stack.isEmpty()) {
                        char c = stack.getFirst();
                        if (c == '*' || c == '/') {
                            resultForm.append(stack.removeFirst());
                        } else {
                            break;
                        }
                    }
                    stack.addFirst(formula[i]);
                    break;
                case '*':
                case '/':
                	stack.addFirst(formula[i]);
                    break;
                case '(':
                	if (Character.isDigit(formula[i-1])) {
                		stack.addFirst('*');
                	}
                	stack.addFirst(formula[i]);
                    break;
                case ')':
                    List<Object> list = Arrays.asList(stack.toArray());
                    int index = list.indexOf('(');

                    Deque<Character> workStack = new ArrayDeque<>();
                    for (int j = 0; j <= index; j++) {
                        char c = stack.removeFirst();
                        if (c != '(') {
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
