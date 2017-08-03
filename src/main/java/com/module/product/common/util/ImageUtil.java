package com.module.product.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017/6/21.
 */
public class ImageUtil {
    private File file = null; // 文件对象
    private String inputDir; // 输入图路径
    private String outputDir; // 输出图路径
    private String inputFileName; // 输入图文件名
    private String outputFileName; // 输出图文件名
    private int outputWidth = 222; // 默认输出图片宽
    private int outputHeight = 295; // 默认输出图片高
    public static int defaultWidth = 900; // 默认输出图片宽
    public static int defaultHeight = 560; // 默认输出图片高
    public static String defaultHouZhui = "PNG"; // 默认输出图片高
    private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)
    public ImageUtil() { // 初始化变量
        inputDir = "";
        outputDir = "";
        inputFileName = "";
        outputFileName = "";
        outputWidth = 222;
        outputHeight = 295;
    }
    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    public void setOutputWidth(int outputWidth) {
        this.outputWidth = outputWidth;
    }
    public void setOutputHeight(int outputHeight) {
        this.outputHeight = outputHeight;
    }
    public void setWidthAndHeight(int width, int height) {
        this.outputWidth = width;
        this.outputHeight = height;
    }

    /*
     * 获得图片大小
     * 传入参数 String path ：图片路径
     */
    public long getPicSize(String path) {
        file = new File(path);
        return file.length();
    }

    // 图片处理
    public String compressPic() {
        try {
            //获得源文件
            file = new File(inputDir + inputFileName);
            if (!file.exists()) {
                return "";
            }
            Image img = ImageIO.read(file);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                System.out.println(" can't read,retry!" + "<BR>");
                return "no";
            } else {
                int newWidth; int newHeight;
                // 判断是否是等比缩放
                if (this.proportion == true) {
                    // 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) img.getWidth(null)) / (double) outputWidth;
                    double rate2 = ((double) img.getHeight(null)) / (double) outputHeight;
                    // 根据缩放比率大的进行缩放控制
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    newWidth = (int) (((double) img.getWidth(null)) / rate);
                    newHeight = (int) (((double) img.getHeight(null)) / rate);
                } else {
                    newWidth = outputWidth; // 输出的图片宽度
                    newHeight = outputHeight; // 输出的图片高度
                }
                BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);

			 	/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
				 * 优先级比速度高 生成的图片质量比较好 但速度慢
				 */
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
                File directory = new File(outputDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
                // JPEGImageEncoder可适用于其他图片类型的转换
                //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                ImageIO.write(tag, "png", out);
                //encoder.encode(tag);
                out.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "ok";
    }
    public String compressPic (String inputDir, String outputDir, String inputFileName, String outputFileName) {
        // 输入图路径
        this.inputDir = inputDir;
        // 输出图路径
        this.outputDir = outputDir;
        // 输入图文件名
        this.inputFileName = inputFileName;
        // 输出图文件名
        this.outputFileName = outputFileName;
        return compressPic();
    }
    public  String compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) {
        // 输入图路径
        this.inputDir = inputDir;
        // 输出图路径
        this.outputDir = outputDir;
        // 输入图文件名
        this.inputFileName = inputFileName;
        // 输出图文件名
        this.outputFileName = outputFileName;
        // 设置图片长宽
        setWidthAndHeight(width, height);
        // 是否是等比缩放 标记
        this.proportion = gp;
        return compressPic();
    }
    /**
     * 压缩图片
     * @param oldFile 输入文件
     * @param destFile	输出文件
     * @param minW	最小宽度
     * @param minH	最小高度
     * @throws IOException
     */
    public static void imageZip(File oldFile, String destFile,int minW ,int minH) throws IOException {
        FileOutputStream out = null;
        try {
            // 文件不存在时
            if (!oldFile.exists())
                return;
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(oldFile);
            // 获取图片的实际大小 宽度
            int w = (int) srcFile.getWidth(null);
            // 获取图片的实际大小 高度
            int h = (int) srcFile.getHeight(null);
            //计算图片压缩率
            double multiple = ((double)h/(double)minH<(double)w/(double)minW?(double)h/(double)minH:(double)w/(double)minW);
            // multiple = multiple>1?multiple:1;
            //新图片的宽高
            int new_w = ((Double)(w/multiple)).intValue();
            int new_h = ((Double)(h/multiple)).intValue();
            out = new FileOutputStream(destFile);
            //新图片对象
            BufferedImage tag= null ;
            if((new_w-minW)>(new_h-minH)){
                int cutW = (new_w-minW)/2 ;
                tag= new BufferedImage(new_w-(new_w-minW),new_h,BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(srcFile.getScaledInstance(new_w, new_h,Image.SCALE_SMOOTH),-cutW,0,null);
            }else{
                int cutH = (new_h-minH)/2 ;
                tag= new BufferedImage(new_w,new_h-(new_h-minH),BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(srcFile.getScaledInstance(new_w, new_h,Image.SCALE_SMOOTH),0,-cutH,null);
            }

//            tag= new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(srcFile.getScaledInstance(100,100,Image.SCALE_SMOOTH),100,100,null);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
//            jep.setQuality(0.72f, true);
//            encoder.encode(tag,jep);
            out.close();
            srcFile.flush();
        } finally{
            if(out != null)out.close();
        }
    }

    /**
     * 图片压缩大小
     * @author hejianming
     * @date 2013-11-05
     * @param imageFilePath
     * @param flag
     */
    /*public  static void picZoom(String imageFilePath,String flag){
        //压缩后的宽度
        int width=0;
        //压缩后的告诉
        int height=0;
        try {
            File intFile = new File(imageFilePath) ;
            // 文件不存在时
            if (!intFile.exists())
                return;
            // 获取图片的实际大小 宽度
            java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(intFile);
            int w = img.getWidth();// 原图的宽
            int h = img.getHeight();// 原图的高
            if(flag.equals("w")){
                width=defaultWidth;
                height =(int)(h*(double)defaultWidth)/w;
            }else{
                width =(int)(w*(double)defaultHeight)/h;
                height=defaultHeight;
            }
            Builder<File> outputFormat = Thumbnails.of(imageFilePath)
                    .size(width, height)
                    .keepAspectRatio(false) //默认是true,按比例缩小
                    .outputQuality(1f)//设置压缩比,决定压缩后图片的质量
                    .outputFormat(defaultHouZhui);
            outputFormat.toFile(imageFilePath);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
    /**
     * 图片压缩大小
     * @author hejianming
     * @date 2013-11-05
     * @param importImageFilePath 压缩前的图片地址
     * @param outImagePathDir 压缩后的图片根目录
     * @param outImageName 压缩后的图片名称（包含后缀）
     * @param flag
     */
   /* public  static String picZoom(String importImageFilePath,String outImagePathDir,String outImageName,Integer num){
        //压缩后的宽度
        int width=0;
        //压缩后的告诉
        int height=0;
        try {
            File directory = new File(outImagePathDir);
            if (!directory.exists()) {
                //如果目录不存在则创建目录
                directory.mkdirs();
            }
            File intFile = new File(importImageFilePath) ;
            // 文件不存在时
            if (!intFile.exists())
                return "";
            // 获取图片的实际大小 宽度
            java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(intFile);
            int w = img.getWidth();// 原图的宽
            int h = img.getHeight();// 原图的高
            width=num;
            height =num;
            Builder<File> outputFormat = Thumbnails.of(importImageFilePath)
                    .size(width, height)
                    .keepAspectRatio(false) //默认是true,按比例缩小
                    .outputQuality(1f)//设置压缩比,决定压缩后图片的质量
                    .outputFormat(defaultHouZhui);
            outputFormat.toFile(outImagePathDir+outImageName);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "ok";
    }*/
    /**
     * 获取本地图片宽高
     * @return
     * @throws IOException
     */
    public static Map<String, Integer> getImageWeiAndHei(InputStream stream){
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            BufferedImage image = ImageIO.read(stream);
            map.put("width", image.getWidth());
            map.put("height", image.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("width",0);
            map.put("height",0);
        }
        return map;
    }

    /**
     * 获取图片的宽和高
     *
     * @param path 图片路径
     * @return
     */
    public static String[] getImageWidthAndHeihgtByUrl(String path) {
        String[] str = new String[]{"0", "0"};
        InputStream is = null;
        try {
            int width = 0;
            int height = 0;
            if (path.startsWith(ParamUtil.imageServicePath)) {
                String filePath = path.replace(ParamUtil.imageServicePath, ParamUtil.imageServiceRealPath);
                BufferedImage img = ImageIO.read(new FileInputStream(new File(filePath)));
                width = img.getWidth();
                height = img.getHeight();
            } else {
                URL url = new URL(path);
                is = url.openConnection().getInputStream();
                BufferedImage img = ImageIO.read(is);
                width = img.getWidth();
                height = img.getHeight();
            }
            str[0] = String.valueOf(width);
            str[1] = String.valueOf(height);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    // main测试
    // compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
    public static void main(String[] arg) {
// 		ImageUtil mypic = new ImageUtil();
// 		System.out.println("输入的图片大小：" + mypic.getPicSize("f:/1.png")/1024 + "KB");
// 		mypic.compressPic("f://", "f://", "1.jpg", "img_tanonymous_mid.jpg", 60, 60, true);
// 		mypic.compressPic("f://", "f://", "1.jpg", "img_tanonymous_min.jpg", 30, 30, true);
// 		mypic.compressPic("f://", "f://", "1.jpg", "img_tanonymous.jpg", 100, 100, true);
// 		File intFile = new File("D:/Workspace/mars/WebRoot/resources/upload/livePpt/20131105120926247/image/1.JPG") ;
// 		File outFile = new File("f:/2.png") ;
// 		File outFile2 = new File("e:/175858120403532761test2.jpg") ;
        try {
            //picZoom("C:/Users/hp_sunland//Desktop/b/aa.jpg","C:/Users/hp_sunland/Desktop/b/","2.png",50);
            //Map<String, Integer> map =getImageWeiAndHei("C://Users//Public//Pictures//Sample Pictures//Desert.jpg");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
