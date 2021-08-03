package tyuukan;
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
		
		return formula;
	}

}
