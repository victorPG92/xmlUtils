package java9.prueba.tr.cor;

public class PruebaCoR
{

	public static void main(String[] args)
	{
		
		BuilderCor b = new BuilderCor();
		CoRInt2String cor = b.creaCoR();
		for (int i = 0; i < 100; i++) {
			cor.escribe(i);
		}
		
	}
}
