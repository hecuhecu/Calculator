package tyuukan;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GetForm2 {
	public static String[] get() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("式を入力して下さい(演算子,被演算子,括弧は空白で区切って下さい(負の符号はひとまとまりで))：");
		System.out.println("例：3 * ( -2 ) * ( 3 - 1 )");
		String form = scanner.nextLine();
		String[] formula = form.split(" ");
		String[] error = {"error"};
		
		int count1 = 0;
		int count2 = 0;
		for (int i=0; i<formula.length; i++) { //括弧の過不足
			if (formula[i].equals("(")) {
				count1++;
			}
			else if (formula[i].equals(")")) {
				count2++;
			}
			
			if (count1<count2) {
				System.out.println("error:括弧に問題があります・");
				return error;
			}
		}
		if (count1 != count2) {
			System.out.println("error:括弧の数が合っていません。");
			return error;
		}
		
		String[] ope = {"+", "-", "*", "/"};
		for (int i=1; i<formula.length; i++) { //演算子が連続で入力されている時
			for (int j=0; j<ope.length; j++) {
				for (int k=0; k<ope.length; k++) {
					if (formula[i-1].equals(ope[j]) && formula[i].equals(ope[k])) {
						System.out.println("error:演算子が連続して入力されています。");
						return error;
					}
				}
			}
		}
		
		for (int i=1; i<formula.length; i++) { //0で割っている時
			if (formula[i-1].equals("/") && formula[i].equals("0")) {
				System.out.println("error:0で割っています。");
				return error;
			}
		}
		
		int count = 0; //式に文字(変数)が含まれていたらカウント
		int[] parameterIndex = new int[formula.length]; 
		String[] parameter = new String[formula.length];
		Map<String, String> paraKeep = new HashMap<>();
		for (int i=0; i<formula.length; i++) {
			if (formula[i].matches("[a-z]")) {
				parameterIndex[count] = i;
				parameter[count] = formula[i];
				paraKeep.put(parameter[count], null);
				count++;
			}
			else if (formula[i].matches("-[a-z]")) {
				char charPart = formula[i].charAt(1); //負の符号を抜き取った文字(変数)
				parameterIndex[count] = i;
				parameter[count] = String.valueOf(charPart);
				paraKeep.put(parameter[count], null);
				count++;
			}
		}
		
		String[] parameterValue = new String[count];
		if (count>0) { //countが１以上ということは式に文字(変数)が含まれている
			System.out.println("変数に値を代入して下さい(しない場合はEnter)");
			for (int i=0; i<count; i++) { //一つの文字に一つの値しか代入出来ないようにする
				if (paraKeep.get(parameter[i])==null) {
					System.out.print(parameter[i] + ":");
					parameterValue[i] = scanner.nextLine();
					paraKeep.put(parameter[i], parameterValue[i]);
				}
				else {
					parameterValue[i] = paraKeep.get(parameter[i]);
				}
			}
			
			for (int i=0; i<formula.length; i++) { //負の文字(変数)には、符号付きの文字に代入するのではなく、符号を除いた文字だけに代入されるようにする
				for (int j=0; j<count; j++) {
					if (i==parameterIndex[j] && !parameterValue[j].equals("")) {
						if (formula[i].matches("[a-z]")) {
							formula[i] = parameterValue[j];
						}
						else if (formula[i].matches("-[a-z]")) {
							formula[i] = "-" + parameterValue[j];
						}
					}
				}
			}
		}
		
		return formula;
	}

}
