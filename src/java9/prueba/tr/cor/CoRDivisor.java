package java9.prueba.tr.cor;

/**
 * Chain of responsability en que dado un divisor
 * si el resto de la entrada con ese divisor, es 0, 
 * mostrara el mensaje
 * eoc, ira al siguiente cor
 * @author vpenit
 *
 */
public class CoRDivisor extends CoRInt2String
{
	
	int divisor;
	String msg;
	
	
	

	public CoRDivisor(int divisor, String msg)
	{
		super();
		this.divisor = divisor;
		this.msg = msg;
	}

	@Override
	protected boolean debeEscribirOPasarAlSig(int i)
	{
		return i%divisor==0;
	}

	@Override
	protected String getMsg(int i)
	{
		return msg;
	}

}
