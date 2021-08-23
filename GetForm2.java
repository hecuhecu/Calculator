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
		
		int openBrackets = 0;
		int closeBrackets = 0;
		for (int i=0; i<formula.length; i++) { //括弧の過不足
			if (formula[i].equals("(")) {
				openBrackets++;
			}
			else if (formula[i].equals(")")) {
				closeBrackets++;
			}
			
			if (openBrackets<closeBrackets) {
				System.out.println("error:括弧に問題があります・");
				scanner.close();
				return error;
			}
		}
		if (openBrackets != closeBrackets) {
			System.out.println("error:括弧の数が合っていません。");
			scanner.close();
			return error;
		}
		
		String[] ope = {"+", "-", "*", "/"};
		for (int i=1; i<formula.length; i++) { //演算子が連続で入力されている時
			for (int j=0; j<ope.length; j++) {
				for (int k=0; k<ope.length; k++) {
					if (formula[i-1].equals(ope[j]) && formula[i].equals(ope[k])) {
						System.out.println("error:演算子が連続して入力されています。");
						scanner.close();
						return error;
					}
				}
			}
		}
		
		for (int i=1; i<formula.length; i++) { //0で割っている時
			if (formula[i-1].equals("/") && formula[i].equals("0")) {
				System.out.println("error:0で割っています。");
				scanner.close();
				return error;
			}
		}
		
		String[] var = new String[formula.length]; //式における文字(変数)を保持
		int[] varIndex = new int[formula.length]; //式における文字(変数)の位置を保持
		int varCount = 0; //式に文字(変数)が含まれていたらカウント
		Map<String, String> substitute = new HashMap<>(); //文字(変数)とそれに代入する値を保持
		for (int i=0; i<formula.length; i++) {
			if (formula[i].matches("[a-z]")) {
				varIndex[varCount] = i;
				var[varCount] = formula[i];
				substitute.put(var[varCount], null);
				varCount++;
			}
			else if (formula[i].matches("-[a-z]")) {
				char charPart = formula[i].charAt(1); //負の符号を抜き取った文字(変数)
				varIndex[varCount] = i;
				var[varCount] = String.valueOf(charPart);
				substitute.put(var[varCount], null);
				varCount++;
			}
		}
		
		String[] varValue = new String[varCount];
		if (varCount>0) { //countが１以上ということは式に文字(変数)が含まれている
			System.out.println("変数に値を代入して下さい(しない場合はEnter)");
			for (int i=0; i<varCount; i++) { //一つの文字に一つの値しか代入出来ないようにする
				if (substitute.get(var[i])==null) {
					System.out.print(var[i] + ":");
					varValue[i] = scanner.nextLine();
					substitute.put(var[i], varValue[i]);
				}
				else {
					varValue[i] = substitute.get(var[i]);
				}
			}
			
			for (int i=0; i<formula.length; i++) { //負の文字(変数)には、符号付きの文字に代入するのではなく、符号を除いた文字だけに代入されるようにする
				for (int j=0; j<varCount; j++) {
					if (i==varIndex[j] && !varValue[j].equals("")) {
						if (formula[i].matches("[a-z]")) {
							formula[i] = varValue[j];
						}
						else if (formula[i].matches("-[a-z]")) {
							formula[i] = "-" + varValue[j];
						}
					}
				}
			}
		}
		
		scanner.close();
		
		return formula;
	}

}
