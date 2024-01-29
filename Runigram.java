// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		//print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] imageOut;

		//Tests the horizontal flipping of an image:
		// imageOut = flippedHorizontally(tinypic);
		// System.out.println();
		// print(imageOut);
		//Tests the vertical flipping of an image:
		//imageOut = flippedVertically(tinypic);
		// System.out.println();
		// print(imageOut);
		//Tests the luminance function:
		// int r = Integer.parseInt(args[0]);
		// int g = Integer.parseInt(args[1]);
		// int b = Integer.parseInt(args[2]); 
		// Color pixel  = new Color(r, g, b);
		//printLuminance(pixel);
		//Tests the gray scayling of an image:
		// imageOut = grayScaled(tinypic);
		// System.out.println();
		// print(imageOut);
		//Tests the scayling of an image:
		// imageOut = scaled(tinypic, r, g);
		// System.out.println();
		// print(imageOut);
		// double alpha = 0.25;
		// Color c1 = new Color (100, 40 ,100);
		// Color c2 = new Color (200, 20 ,40);
		// printBlend(c1, c2, alpha);




	// 	//// Write here whatever code you need in order to test your work.
	// 	//// You can reuse / overide the contents of the imageOut array.
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
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		Color[][] image = new Color[numRows][numCols];
		for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image[i].length ; j++){
				int red = in.readInt();
				int green = in.readInt();
				int blue = in.readInt();
				image[i][j] = new Color( red, green, blue);
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
		for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image[i].length; j++){
				print(image[i][j]);				
			}
			System.out.println();
		}
	}
		/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		int col = 0;
		Color[][] newImage = new Color[image.length][image[0].length];		
		for(int i = 0; i < image.length; i++){
			col = 0;
			for(int j = image[i].length - 1; j >= 0; j--){
				newImage[i][col] = image[i][j];
				col++; 
			}
		}
		return newImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		int row = 0;
		Color[][] newImage = new Color[image.length][image[0].length];	
		row = image.length;
		for(int i = 0; i < image.length; i++){
			System.out.println(i);
			row--;
			newImage[row] = image[i];
		}
		return newImage;
	}
	public static void printLuminance(Color pixel) {
		System.out.println(luminance(pixel));
	}

	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		int r = pixel.getRed();
		int b = pixel.getBlue();
		int g = pixel.getGreen();
		int lum;
		lum = (int)(0.299*r + 0.587*g + 0.114*b);
		Color newPixel;
		newPixel = new Color(lum, lum, lum);
		return newPixel;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		Color[][] grayimage = new Color[image.length][image[0].length];
		for(int i = 0; i < image.length; i++){
			for(int j = 0; j < image[i].length; j++){
				 grayimage[i][j] = luminance(image[i][j]);
			}
		}
		return grayimage;
	}	
	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		int h0 = image.length;
		int w0 = image[0].length;
		int h = height;
		int w = width;
		double scaleH = (double) h0 / h;
		double scaleW = (double) w0 / w;
		Color[][] scaleImage = new Color[h][w];
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				int x = (int) (i * scaleH);
				int y = (int) (j * scaleW);
				scaleImage[i][j] = image[x][y];
			}
		}	
		return scaleImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//Gets the rgb of c1
		int red1 = c1.getRed();
		int green1 = c1.getGreen();
		int blue1 = c1.getBlue();
		//Gets the rgb of c2
		int red2 = c2.getRed();
		int green2 = c2.getGreen();
		int blue2 = c2.getBlue();
		// The rgb of the new color
		int red3 = (int) (alpha * red1 + (1 - alpha) * red2);
		int green3 = (int) (alpha * green1 + (1 - alpha) * green2);
		int blue3 = (int) (alpha * blue1 + (1 - alpha) * blue2);
		Color blendColor = new Color (red3, green3, blue3 );
		return blendColor;
	}
	public static void printBlend(Color c1, Color c2, double alpha) {
		System.out.println(blend(c1, c2, alpha));
	}

	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		Color[][] blendImage = new Color[image1.length][image1[0].length];
		for(int i = 0; i < blendImage.length; i++){
			for(int j = 0; j < blendImage[i].length; j++){
				Color c1 = image1[i][j];
				Color c2 = image2[i][j];
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
		Color[][] scaledTarget = target;
		if (target.length != source.length || target[0].length != source[0].length){
			 scaledTarget = scaled(target, source[0].length, source.length);
		}
		
		for (int i = 0; i <= n; i++) {
			int alpha = (n - i) / n;
			Color[][] morph = blend(source, scaledTarget, alpha);
			 display(morph);
			 StdDraw.pause(500);
		}
	}
	// public static Color luminanceBlend(Color pixel, double alpha) {
	// 	int r = pixel.getRed();
	// 	int b = pixel.getBlue();
	// 	int g = pixel.getGreen();
	// 	int lum;
	// 	lum = (int)(0.299*r + 0.587*g + 0.114*b);
	// 	int lum1 = (int)((alpha * lum) + ((1 - alpha) * lum));
	// 	Color newPixel;
	// 	newPixel = new Color(lum1, lum1, lum1);
	// 	return newPixel;
	// }
	// public static Color[][] luminanceBlend(Color[][] image, double alpha) {
	// 	Color[][] grayimage = new Color[image.length][image[0].length];
	// 	for(int i = 0; i < image.length; i++){
	// 		for(int j = 0; j < image[i].length; j++){
	// 			 grayimage[i][j] = luminanceBlend(image[i][j], alpha);
	// 		}
	// 	}
	// 	return grayimage;
	// }
	// Morphs the source image into the gray image, gradually, in n steps.
	public static void morphGray(Color[][] source, int n) {
		Color[][] target = grayScaled(source);
		for (int i = 0; i <= n; i++) {
			int alpha = (n - i) / n;
			Color[][] morph = blend(source, target, alpha);
			 display(morph);
			 StdDraw.pause(500);
		}
	}

	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
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

