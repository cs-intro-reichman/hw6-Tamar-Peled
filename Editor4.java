import java.awt.Color;


 
public class Editor4 {
    public static void main (String[] args) {
		String source = args[0];
		int n = Integer.parseInt(args[1]);
        // Read the source image from the file.
		Color[][] sourceImage = Runigram.read(source);
        // The grayScaled method converts the colored image into grayscale.
        Color[][] grayScaled = Runigram.grayScaled(sourceImage);
        // Step 3: Set up the canvas.
		Runigram.setCanvas(sourceImage);
        // The morph method gradually transitions the source image into the grayscale version
        // over 'n' steps, displaying each intermediate image on the canvas.
		Runigram.morph(sourceImage, grayScaled, n);
	}
}

    

