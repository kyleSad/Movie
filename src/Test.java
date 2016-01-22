import java.util.*;
import java.io.*;

public class Test {
	public void readFile(Scanner file) {
		Scanner file = null;
		 file = new Scanner(new File(args[0]));
		 while(file.hasNextLine()){
			 while(file.hasNext()){
			 System.out.print(file.next());
			 }
		 }
		 
	}
}
