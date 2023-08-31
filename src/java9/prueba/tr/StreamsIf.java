package java9.prueba.tr;

import java.util.stream.IntStream;

public class StreamsIf
{

	public static void main(String[] args)
	{
		IntStream.range(0, 100).mapToObj(i->
		{
	
			if(i==0)
				return "0";
			else if(i%15==0)
			{
				return "foobar";
			}
			else if(i%3==0)
				return "foo";
			
			else if(i%5==0)
			{
				return "bar";
			}
			else
				return Integer.toString(i);
					
		}).forEach(System.out::println);
	}
}
