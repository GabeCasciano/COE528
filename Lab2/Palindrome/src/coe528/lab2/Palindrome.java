package coe528.lab2;

public class Palindrome {

    /*Requires: a:String to not be empty ("") or null*/
    /*Modifies: a temporary variable reversed:String*/
    /*Effects: compares the original string and a reversed version of it
    *          if they are the same, then true, if any others false */
    public static boolean isPalindrome(String a){
        String temp = "";
        if(a == null) return false;
        else if(a.length() < 3) return false;
        for(int i = a.length() ; i > 0; i--)
            temp += a.charAt(i-1);
        if(temp.equals(a)) return true;
        return false;
    }
    public static void main(String[] args) {
	    if(args.length == 1){
	        if(args[0].equals("1"))
	            System.out.println(isPalindrome(null));
	        else if(args[0].equals("2"))
	            System.out.println(isPalindrome(""));
	        else if(args[0].equals("3"))
	            System.out.println(isPalindrome("deed"));
	        else if(args[0].equals("4"))
	            System.out.println(isPalindrome("abcd"));
	        else if(args[0].equals("5"))
	            System.out.println(isPalindrome("racecar"));
        }
    }
}
