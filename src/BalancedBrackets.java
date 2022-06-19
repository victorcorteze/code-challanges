import java.io.*;
import java.util.Stack;
import java.util.stream.IntStream;

public class BalancedBrackets {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();
                String result;
                if(BalancedBrackets.isBalanced(s))
                    result = "YES";
                else
                    result = "NO";

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static boolean isBalanced(String s) {
        if(s == null || s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            } else if(c == ')' || c == '}' || c == ']'){
                if(!stack.isEmpty()){
                    char latestOpenP = stack.pop();
                    if(latestOpenP == '(' && c != ')'){
                        return false;
                    } else if(latestOpenP == '{' && c != '}'){
                        return false;
                    } else if(latestOpenP == '[' && c != ']'){
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
