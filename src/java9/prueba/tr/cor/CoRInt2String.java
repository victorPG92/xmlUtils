package java9.prueba.tr.cor;

/**
 * chain osf responsability que dado un entero, muestra un mensaje o lo pasa al siguiente cor
 * @author vpenit
 *
 */
public abstract class CoRInt2String
{

	/**siguiente COR */
	CoRInt2String corSig;
	
	/**
	 * dado un 
	 * @param i
	 */
	public void escribe(int i)
	{
		if(debeEscribirOPasarAlSig(i))
		{
			System.out.println(getMsg(i));
		}
		else if(corSig!=null)
		{
			corSig.escribe(i);
		}
	}

	/**Indica si debe escribir el mensaje o lo debe pasar al siguiente */
	protected abstract boolean debeEscribirOPasarAlSig(int i);
	/**Indica el mensaje que debe escribir en el caso de que pueda */
	protected abstract String getMsg(int i);

	/**siguiente COR */
	public CoRInt2String getCorSig(){return corSig;}
	/**siguiente COR */
	public void setCorSig(CoRInt2String corSig){this.corSig = corSig;}
	
	public void encolaCor(CoRInt2String corSig2)
	{
		if(this.corSig==null)
		{
			this.corSig=corSig2;
		}
		
		else
		{
			this.corSig.encolaCor(corSig2);
		}
	}
	
}
