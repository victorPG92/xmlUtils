package java9.prueba.tr.cor;

/**
 * Chain of responsability en que dado un numero, lo transforma a string

 * @author vpenit
 *
 */
public class CoRElMismo extends CoRInt2String
{
	
	
	
	

	

	@Override
	protected boolean debeEscribirOPasarAlSig(int i)
	{
		return true;
	}

	@Override
	protected String getMsg(int i)
	{
		return Integer.toString(i);
	}

}
