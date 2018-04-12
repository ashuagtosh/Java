import java.util.Scanner;

public class kmp {

	public static void main(String[] args) {
		//String str = "abcaby";
		String pattern;
		String text;
		
		Scanner sc =new Scanner(System.in);
		pattern = sc.next();
		text = sc.next();
		
		kmp k =new kmp();
		
		int arr[] = k.getTable(pattern);
		
		
		
		int position = k.strStr(text, pattern);
		if(position == -1){
			System.out.println("Substring Not Matched");
		}else{
			System.out.println("Substring Matched at Position : "+position);
		}
		
	}
	
	public int[] getTable(String pattern) {
		int[] next = new int[pattern.length()];
		next[0] = 0;
	 
		for (int i = 1; i < pattern.length(); i++) {
			int index = next[i - 1];
			while (index > 0 && pattern.charAt(index) != pattern.charAt(i)) {
				index = next[index - 1];
			}
	 
			if (pattern.charAt(index) == pattern.charAt(i)) {
				next[i] = next[i - 1] + 1;
			} else {
				next[i] = 0;
			}
		}
	 
		return next;
	}
	
	public int strStr(String text, String pattern) {
        if(text==null || pattern==null)    
            return 0;
 
	int h = text.length();
	int n = pattern.length();
 
	if (n > h)
		return -1;
	if (n == 0)
		return 0;
 
	int[] next = getTable(pattern);
	int i = 0;
 
	while (i <= h - n) {
		int success = 1;
		for (int j = 0; j < n; j++) {
			if (pattern.charAt(0) != text.charAt(i)) {
				success = 0;
				i++;
				break;
			} else if (pattern.charAt(j) != text.charAt(i + j)) {
				success = 0;
				i = i + j - next[j - 1];
				break;
			}
		}
		if (success == 1)
			return i;
	}
 
	return -1;
}

}
