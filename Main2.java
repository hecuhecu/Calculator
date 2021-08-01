package tyuukan;

public class Main2 {
	public static void main(String args[]) {
		String form = GetForm2.get();
		if (form.equals("error")) {
			return;
		}
		form = GetRpn2.get(form);
		
		String result = Cal2.calc(form);
		System.out.println("計算結果は"+result+"です。");
	}
}
