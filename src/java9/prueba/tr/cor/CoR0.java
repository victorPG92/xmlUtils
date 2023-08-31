package java9.prueba.tr.cor;

public class CoR0 extends CoRInt2String
{

	@Override
	protected boolean debeEscribirOPasarAlSig(int i)
	{
		return i==0;
	}

	@Override
	protected String getMsg(int i)
	{
		return "0";
	}

}
