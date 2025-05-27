/**
 * HackerRank - Java - Data Structures - Java Map
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/phone-book/problem
 *
 * Push data in a map, then query it
 */
package hackerrank.challenges.dataStructures.phone_book;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
	public static void main(String []argh)
	{
		Map<String, Integer> book = new HashMap<>();
		try(Scanner scanner = new Scanner(System.in)) {
			int n= scanner.nextInt();
			scanner.nextLine();
			for(int i=0;i<n;i++)
			{
				book.put(scanner.nextLine(), scanner.nextInt());
				scanner.nextLine();
			}

			while(scanner.hasNext())
			{
				String key = scanner.nextLine();
				Integer value = book.get(key);
				if(value == null) {
					System.out.println("Not found");
				} else {
					System.out.println(key + "=" + value);
				}
			}
		}
	}
}
