import java.io.File;

public class ObjectData {
	private File javaFile;
	private String fname;
	private SpriteData spriteData;
	
	public ObjectData(File javaFile){
		this(javaFile, null);
	}
	
	public ObjectData(File javaFile, SpriteData spriteData){
		this.javaFile = javaFile;
		this.spriteData = spriteData;
		fname = "Null";
		
		if (javaFile != null){
			fname = javaFile.getName();
			fname = fname.substring(0, fname.lastIndexOf("."));
		}
	}
	
	public void setSpriteData(SpriteData spriteData){this.spriteData = spriteData;}
	
	public File getJavaFile(){return javaFile;}
	public SpriteData getSpriteData(){return spriteData;}
	public String toString(){
		return fname;
	}
}
