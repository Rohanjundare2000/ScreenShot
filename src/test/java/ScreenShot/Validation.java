package ScreenShot;
import java.io.File;

public class Validation {
	
	

	    private static final String SCREENSHOTS_FOLDER = "screenshots";

	    public static void main(String[] args) {
	        // Validation steps
	        if (areScreenshotsCaptured()) {
	            System.out.println("Screenshots captured successfully.");
	        } else {
	            System.err.println("Error: No screenshots captured.");
	        }
	    }

	    private static boolean areScreenshotsCaptured() {
	        // Check if the screenshots folder is not empty
	        File screenshotsFolder = new File(SCREENSHOTS_FOLDER);
	        return screenshotsFolder.exists() && screenshotsFolder.list().length > 0;
	    }
	
}
