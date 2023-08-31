package java9.bean;

public abstract class AbstractNodo implements INodoXMl
{

	protected INodoXMl padre;
	protected boolean isComentario;

	
	@Override
	public INodoXMl getPadre()
	{
		return this.padre;
	}

	@Override
	public void setPadre(INodoXMl padre)
	{
		if(padre instanceof NodoComentario)
		{
			System.err.println("Error ,no se puede poner como padre a un comentario");
		}
		this.padre = padre;
	}

	public boolean isComentario()
	{
		return isComentario;
	}

	public void setIsComentario(boolean isComentario)
	{
		this.isComentario = isComentario;
	}
	
	
}
