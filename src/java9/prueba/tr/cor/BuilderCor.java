package java9.prueba.tr.cor;

public class BuilderCor
{

	public CoRInt2String creaCoR()
	{
		CoRInt2String cor0Inicial= new CoR0();
		cor0Inicial.encolaCor(new CoRDivisor(15, "foobar"));
		cor0Inicial.encolaCor(new CoRDivisor(3, "foo"));
		cor0Inicial.encolaCor(new CoRDivisor(5, "bar"));
		cor0Inicial.encolaCor(new CoRElMismo());

		
		return cor0Inicial;

	}
}
