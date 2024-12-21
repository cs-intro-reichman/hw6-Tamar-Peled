import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
		System.out.println();
		System.out.println("flipped image");
		System.out.println();
		System.out.println();
		print(flippedHorizontally(tinypic));

		System.out.println();
		System.out.println("gray");
		System.out.println();
		print(grayScaled(tinypic));

		System.out.println();
		System.out.println("scaling");
		System.out.println();
		print(scaled(tinypic, 3, 5));

		System.out.println();
		System.out.println("null");
		System.out.println();
		
		
		
		
		
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i< numRows; i++){
			for(int j = 0; j < numCols; j++){
				int r = in.readInt();
				int g = in.readInt();
				int b = in.readInt();
				Color color = new Color (r, g, b);
				image[i][j]= color;
			}
		}

		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
		for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image [i].length; j++){
				print(image[i][j]);
			
			}
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		
		int numRows = image.length;
		int numCols = image[0].length;
		Color flippedHorizontally [][] = new Color[numRows][numCols];
			for (int i = 0; i < numRows; i++){
				for (int j = 0; j <numCols; j++ ){
					flippedHorizontally[i][numCols - 1 - j] = image[i][j]; 

				}

			}

		
		return flippedHorizontally;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		int numRows = image.length;
		int numCols = image[0].length;
		Color flippedVertically [][] = new Color[numRows][numCols];
			for (int i = 0; i < numRows; i++){
				for (int j = 0; j <numCols; j++ ){
					flippedVertically[numRows - 1 - i][j] = image[i][j]; 

				}

			}
		
		return flippedVertically;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();
		
		int lum = (int) ((0.299 * r) + (0.587 * g) + (0.114 * b));

	
		return new Color (lum, lum,lum) ;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		int numRows = image.length;
		int numCols = image[0].length;
		Color grayScaled [][] = new Color[numRows][numCols];

		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numCols; j++){
				grayScaled [i][j]= luminance(image[i][j]);
								
			}
		}
		return grayScaled;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		int numRows = image.length;
		int numCols = image[0].length;
		Color [][] scaled = new Color[height][width];

		// Use decimal numbers (double) for more accurate calculation
		double rowScale = (double) numRows/ height;
		double numScale = (double) numCols / width;

		// Calculate the exact position in the original image
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				int orgRow = (int) (i * rowScale);
				int orgCol = (int) (j * numScale);
				scaled[i][j] = image [orgRow][orgCol];
			}
		}
		return scaled;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		
		int r  = (int) (alpha * c1.getRed() + (1 - alpha) * c2.getRed());
		int g = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());
		int b = (int) (alpha * c1.getBlue() + (1 - alpha) * c2.getBlue());

		
		Color blend = new Color (r,g,b);
		//System.out.println("blending alp = " + alpha + "c1 = " + c1 + "c2 = " + c2 + "result "+ " " + r + " " + g + " " + b );
	

		
		return blend;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		int numRows = image1.length;
		int numCols = image1[0].length;

		Color [][] blendImage = new Color[numRows][numCols];
		
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numCols; j++){
				 Color c1 = image1 [i][j];
				 Color c2 = image2 [i][j];
				 blendImage[i][j] = blend(c1, c2, alpha);
				
			}
		}
	
		return blendImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		int numRowsSource = source.length;
		int numColsSorce = source[0].length;
		int numRowsTarget = target.length;
		int numColsTarget = target[0].length;

		if (numRowsSource != numRowsTarget || numColsSorce != numColsTarget){
			target = scaled(target, numRowsSource, numColsSorce);
		}
		
		Color[][] newImage = new Color[numRowsSource][numColsSorce];
		for (int i = 0; i <= n; i++){
			double alpha = (double) (n-i) / n;
			newImage = blend(source, target, alpha);
			display(newImage);
			StdDraw.pause(500);
		
	}
		
	
}
	
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

