import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Tester {
	
    /**
     * Complete the function below.
     * DO NOT MODIFY anything outside this method. 
     */
    static boolean[] twins(String[] a, String[] b) {
        boolean[] result = new boolean[a.length];
        
        for(int i=0; i<a.length; i++){
            if(a[i].length() != b[i].length()){
                //impossible to be twins since the lengths are differrent
                result[i] = false;
                continue;
            }
            
            char[] a_chars = a[i].toCharArray();
            char[] b_chars = b[i].toCharArray();
             
            //swap even and odd of a_chars
            int last_even_index = -1;
            int last_odd_index = -1;
            for(int k=0; k < a_chars.length; k++){
                if(k % 2 == 0){
                    if(last_even_index != -1){
                        //swap even
                        char temp = a_chars[k];
                        a_chars[k] = a_chars[last_even_index];
                        a_chars[last_even_index] = temp;
                    }
                    last_even_index = k;
                }else{
                    if(last_odd_index != -1){
                        //swap odd
                        char temp = a_chars[k];
                        a_chars[k] = a_chars[last_odd_index];
                        a_chars[last_odd_index] = temp;
                    }
                    last_odd_index = k;
                }
                
            }
            
            //check if both are equal
            boolean is_equal = true;
            for(int k = 0; k < a_chars.length; k++){
                if(a_chars[k] != b_chars [k]){
                    is_equal = false;
                    break;
                }
            }
            result[i] = is_equal;
            
        }
        
        
        return result;
    }

    /**
     * DO NOT MODIFY THIS METHOD!
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        
        int n = Integer.parseInt(in.nextLine().trim());
        String[] a = new String[n];
        for(int i = 0; i < n; i++) {
            a[i] = in.nextLine();
        }
        
        int m = Integer.parseInt(in.nextLine().trim());
        String[] b = new String[m];
        for(int i = 0; i < m; i++) {
            b[i] = in.nextLine();
        }
        
        // call twins function
        boolean[] results = twins(a, b);
        
        for(int i = 0; i < results.length; i++) {
            System.out.println(results[i]? "Yes" : "No");
        }
    }
}