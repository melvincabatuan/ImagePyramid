package ph.edu.dlsu;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/*
*
* Simple illustration of image scaling
* By M.K. Cabatuan aka Cobalt
*
*/

public class Main {

    private static Mat bigger;
    private static Mat smaller;

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        String filePath = "res/DLSU.png";
        Mat input = Imgcodecs.imread(filePath);

        if(input.dataAddr()==0){
            System.out.println("Couldn't open file " + filePath);
        }else{
            ImageViewer imageViewer = new ImageViewer();
            imageViewer.show(input, "Input image");
            pyramidUp(input);
            pyramidDown(input);
        }
        destroy();
    }

    private static void pyramidUp(Mat src){
        bigger = new Mat(src.rows()*2, src.cols()*2, src.type());
        Imgproc.pyrUp(src, bigger, bigger.size());
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.show(bigger, "Scale image x " + 2);
    }

    private static void pyramidDown(Mat src){
        smaller = new Mat(src.rows()/2, src.cols()/2, src.type());
        Imgproc.pyrDown(src, smaller, smaller.size());
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.show(smaller, "Scale image x " + 1/2);
    }

    private static void destroy(){
        bigger.release();
        smaller.release();
    }

}
