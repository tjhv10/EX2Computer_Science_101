package Package;

public class Ex2 {

    public static int[][] rgb2gray(int[][][] img) {
        int[][] gimg=new int[img[0].length][img[0][0].length];
            for (int j=0; j< img[0].length; j++)
            {
                for (int k=0; k< img[0][j].length; k++)
                {
                    gimg[j][k]=(int)(0.3*img[0][j][k]+0.59*img[1][j][k]+0.11*img[2][j][k])*255;
                }
            }
        return gimg;
    }

    public static int[][][] crop(int[][][] img, int i_1, int j_1, int i_2, int j_2) {
        int[][][]nimg= new int[img.length][i_2-i_1][j_2-j_1];
        for (int i=0; i<nimg.length; i++)
        {
            for (int j=i_1; j<i_2-1; j++)
            {
                for (int k=j_1; k<j_2-1; k++)
                {
                    nimg[i][j][k]=img[i][j][k];
                }
            }
        }

        return nimg;
    }

    public static int[][][] rotate_alpha(int[][][] img, double alpha) {
        int[][][] nimg= new int[img.length][img[0].length][img[0][0].length];
        int i0=(img[0].length)/2,j0=(img[0][0].length)/2;

        for (int i=0; i<nimg.length; i++)
        {
            for (int j=0; j<img[0].length; j++)
            {
                for (int k=0; k<img[0][0].length; k++)
                {
                    if ((Math.cos(Math.toRadians(-alpha))*(j-i0)-Math.sin(Math.toRadians(-alpha))*(k-j0)+i0)>=0 &&
                            (Math.cos(Math.toRadians(-alpha))*(j-i0)-Math.sin(Math.toRadians(-alpha))*(k-j0)+i0) < img[0].length &&
                            (Math.sin(Math.toRadians(-alpha))*(j-i0)+(Math.cos(Math.toRadians(-alpha))*(k-j0))+j0)>=0 &&
                            (Math.sin(Math.toRadians(-alpha))*(j-i0)+(Math.cos(Math.toRadians(-alpha))*(k-j0))+j0)<img[0][0].length)
                    {
                    nimg[i][j][k]=img[i][(int)(Math.cos(Math.toRadians(-alpha))*(j-i0)-Math.sin(Math.toRadians(-alpha))*(k-j0)+i0)][(int)(Math.sin(Math.toRadians(-alpha))*(j-i0)+Math.cos(Math.toRadians(-alpha))*(k-j0)+j0)];
                    }
                }
            }
        }
        return nimg;
    }

    public static int[][][] Smooth(int[][][] img, int n)
    {
        int[][][] nimg= new int[img.length][img[0].length][img[0][0].length];
        int sum=0;
        for (int i=0; i< nimg.length; i++)
        {
            for (int j = 0; j < img[0].length; j++)
            {
                for (int k = 0; k < 2*n+1; k++)
                {
                    nimg[i][j][k]=img[i][j][k];
                }
            }
        }
        for (int i=0; i< nimg.length; i++)
        {
            for (int j = 0; j <2*n+1; j++)
            {
                for (int k = 0; k < nimg[0][0].length; k++)
                {
                    nimg[i][j][k]=img[i][j][k];
                }
            }
        }

        for (int i=0; i< nimg.length; i++)
        {
            for (int j=n; j< nimg[i].length-(2*n+1); j++)
            {
                for (int k=n; k< nimg[i][j].length-(2*n+1); k++)
                {
                    for (int m=0; m<2*n+1; m++)
                    {
                        for (int l=0; l<2*n+1; l++)
                        {
                            sum+=img[i][j+m][k+l];
                        }
                    }
                    nimg[i][j+n][k+n]=sum/((2*n+1)*(2*n+1));
                    sum=0;
                }
            }
        }
        for (int i=0; i< nimg.length; i++)
        {
            for (int j = nimg[i].length-(2*n+1); j <nimg[i].length; j++)
            {
                for (int k = 0; k < nimg[i][j].length; k++)
                {
                    nimg[i][j][k]=img[i][j][k];
                }
            }
        }
        for (int i=0; i< nimg.length; i++)
        {
            for (int j = 0; j <nimg[i].length; j++)
            {
                for (int k =nimg[i][j].length-(2*n+1) ; k < nimg[i][j].length; k++)
                {
                    nimg[i][j][k]=img[i][j][k];
                }
            }
        }
        return nimg;
    }

    public static int[][] scaleImg(double factor_h, double factor_w, int[][] im) {
        int[][] nimg= new int[(int)(im.length*factor_h)][(int)(im[0].length*factor_w)];
        for (int i=0; i< nimg.length; i++)
        {
            for (int j =0; j < nimg[0].length; j++)
            {
                 nimg[i][j]=im[(int)(i/factor_h)][(int)(j/factor_w)];
            }
        }
        return nimg;
    }

    public static void main(String[] args) {
        int[][][] image = MyImageIO.readImageFromFile("C:/Users/achiy/Desktop/butterfly.jpg");
        MyImageIO.writeImageToFile("C://Users/achiy/Desktop/pic/grey",rgb2gray(image));
        MyImageIO.writeImageToFile("C://Users/achiy/Desktop/pic/crop",crop(image,1,1,2000,2000));
        MyImageIO.writeImageToFile("C://Users/achiy/Desktop/pic/smooth",Smooth(image,10));
        MyImageIO.writeImageToFile("C://Users/achiy/Desktop/pic/rotate",rotate_alpha(image,60));
        MyImageIO.writeImageToFile("C://Users/achiy/Desktop/pic/scale",scaleImg(2,9,rgb2gray(image)));
    }

}
