package tyuukan;
import java.util.Scanner;

public class GetForm2 {
	public static String get() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("式を入力してください：");
		String form = scanner.nextLine();
		char[] formCheck = form.toCharArray();
		
		int count1 = 0;
		int count2 = 0;
		for (int i=0; i<formCheck.length; i++) { //括弧の過不足
			if (formCheck[i]=='(') {
				count1++;
			}
			else if (formCheck[i]==')') {
				count2++;
			}
		}
		if (count1 != count2) {
			System.out.println("error:括弧の数が合っていません。");
			return "error";
		}
		
		char[] ope = {'+', '-', '*', '/'};
		for (int i=1; i<formCheck.length; i++) { //演算子が連続で入力されている時
			for (int j=0; j<ope.length; j++) {
				for (int k=0; k<ope.length; k++) {
					if (formCheck[i-1]==ope[j] && formCheck[i]==ope[k]) {
						System.out.println("error:演算子が連続して入力されています。");
						return "error";
					}
				}
			}
		}
		
		for (int i=1; i<formCheck.length; i++) { //0で割っている時
			if (formCheck[i-1]=='/' && formCheck[i]=='0') {
				System.out.println("error:0で割っています。");
				return "error";
			}
		}
		
		return form;
	}

}
