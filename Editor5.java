import java.awt.Color;

public class Editor5 {
    public static void main (String[] args) {
		String source = args[0];
		String target = args[1];
		Color[][] sourceImage = Runigram.read(source);
		Color[][] targetImage = Runigram.read(target);
		Runigram.setCanvas(sourceImage);
		Runigram.scaled(targetImage, sourceImage[0].length, sourceImage.length);
	}
}
    

