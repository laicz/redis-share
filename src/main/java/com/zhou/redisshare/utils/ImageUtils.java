package com.zhou.redisshare.utils;

import scala.Tuple3;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoumb on 2019/3/14
 */
public class ImageUtils {
    /**
     * @param oldFilePath:你想压缩的图片的地址
     * @param newFilePath：压缩后存放的地址
     * @param t：tuple类型（scala里面的，因为在学习spark所以就拿来用）,<Integer,Integer,Integer>-><weight,high,RGB>
     */
    public static void generatePhoto(String oldFilePath, String newFilePath, List<Tuple3<Integer, Integer, Integer>> t) {
        try {
            BufferedImage imgOld = ImageIO.read(new File(oldFilePath));
            int w = imgOld.getWidth();
            int h = imgOld.getHeight();

            File out = new File(newFilePath);
            if (!out.exists())
                out.createNewFile();
            OutputStream output = new FileOutputStream(out);

            BufferedImage imgOut = new BufferedImage(w, h,
                    BufferedImage.TYPE_3BYTE_BGR);
            for (Tuple3<Integer, Integer, Integer> tt : t) {
                imgOut.setRGB(tt._1(), tt._2(), tt._3());
            }

            ImageIO.write(imgOut, "png", output);

            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 作用是返回图片每一点的RGP值
     *
     * @param filePath :想要处理的图片的地址
     * @return String: with,high,R,G,P,这里我把RGP独立成三个方向的值
     */
    public static List<String> getImageGRBStr(String filePath) {
        File file = new File(filePath);

        List<String> list = new ArrayList<String>();

        if (!file.exists()) {
            return null;
        }
        try {
            BufferedImage bufImg = ImageIO.read(file);
            int height = bufImg.getHeight();
            int width = bufImg.getWidth();

            for (int i = bufImg.getMinX(); i < width; i++) {
                for (int j = bufImg.getMinY(); j < height; j++) {
                    String str = i + "," + j + "," + ((bufImg.getRGB(i, j) & 0xff0000) >> 16) + "," + ((bufImg.getRGB(i, j) & 0xff00) >> 8) + "," + ((bufImg.getRGB(i, j) & 0xff));
                    list.add(str);
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static void transformer(String srcImage, String tarImage, int maxPixel) {
        //源图片文件
        File srcImageFile = new File(srcImage);
        //目的图片文件
        File tarImageFile = new File(tarImage);
        // 生成图片转化对象
        AffineTransform transform = new AffineTransform();
        // 通过缓存读入缓存对象
        BufferedImage image = null;
        try {
            image = ImageIO.read(srcImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int imageWidth = image.getWidth();//原图片的高度
        int imageHeight = image.getHeight();//原图片的宽度
        int changeWidth = 0;//压缩后图片的高度
        int changeHeight = 0;//压缩后图片的宽度
        double scale = 0;// 定义小图片和原图片比例
        if (maxPixel != 0) {
            if (imageWidth > imageHeight) {
                changeWidth = maxPixel;
                scale = (double) changeWidth / (double) imageWidth;
                changeHeight = (int) (imageHeight * scale);
            } else {
                changeHeight = maxPixel;
                scale = (double) changeHeight / (double) imageHeight;
                changeWidth = (int) (imageWidth * scale);
            }
        }
        // 生成转换比例
        transform.setToScale(scale, scale);
        // 生成转换操作对象
        AffineTransformOp transOp = new AffineTransformOp(transform, null);
        //生成压缩图片缓冲对象
        BufferedImage basll = new BufferedImage(changeWidth, changeHeight,
                BufferedImage.TYPE_3BYTE_BGR);
        //生成缩小图片
        transOp.filter(image, basll);
        try {
            //输出缩小图片
            ImageIO.write(basll, "jpeg", tarImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean compressPic(String srcFilePath, String descFilePath) throws IOException {
        File file = null;
        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;

        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
                null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality((float) 1);
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        ColorModel colorModel = ImageIO.read(new File(srcFilePath)).getColorModel();// ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
//        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
//                colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
                colorModel, colorModel.createCompatibleSampleModel(16, 16)));

        try {
            if (isBlank(srcFilePath)) {
                return false;
            } else {
                file = new File(srcFilePath);
                System.out.println(file.length());
                src = ImageIO.read(file);
                out = new FileOutputStream(descFilePath);

                imgWrier.reset();
                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
                // OutputStream构造
                imgWrier.setOutput(ImageIO.createImageOutputStream(out));
                // 调用write方法，就可以向输入流写图片
                imgWrier.write(null, new IIOImage(src, null, null),
                        imgWriteParams);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isBlank(String string) {
        if (string == null || string.length() == 0 || string.trim().equals("")) {
            return true;
        }
        return false;
    }
}
