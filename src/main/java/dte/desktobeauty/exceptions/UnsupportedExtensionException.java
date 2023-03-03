package dte.desktobeauty.exceptions;

public class UnsupportedExtensionException extends RuntimeException
{
	private final String extension;
	
	private static final long serialVersionUID = 2941123534579523206L;
	
	public UnsupportedExtensionException(String extension) 
	{
		super(String.format("The provided extension(%s) is not supported!", extension));
		
		this.extension = extension;
	}
	
	public String getExtension() 
	{
		return this.extension;
	}
}
