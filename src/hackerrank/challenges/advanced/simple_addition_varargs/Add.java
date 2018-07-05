/**
 * HackerRank - Java - Advanced - Java Varargs Simple Addition
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/simple-addition-varargs/problem
 *
 * Add up all the integers passed in
 * NOOP if no integers (HackerRank says nothing about this case)
 */
package hackerrank.challenges.advanced.simple_addition_varargs;

public class Add {
    public void add(int... args) {
        if(args.length == 0)
            return;

        int sum = args[0];

        System.out.print(args[0]);
        for (int i = 1; i < args.length; i++) {
            sum += args[i];
            System.out.print("+" + args[i]);
        }
        System.out.println("=" + sum);
    }
    
    public static void main(String[] args) {
        Add add = new Add();
        
        add.add(1, 2, 3);
        add.add();
    }
}
