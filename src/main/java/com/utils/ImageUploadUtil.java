package com.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUploadUtil {
	
	public static void changeImgeSize(String imageUrl,File oldFile){
		try {
			InputStream inputStream=new FileInputStream(oldFile);
			BufferedImage image2=ImageIO.read(inputStream);
			BufferedImage image=new BufferedImage(160, 100, BufferedImage.TYPE_INT_RGB);
			int imge2_width=image2.getWidth();
			int image2_height=image2.getHeight();
			 Graphics2D resizedG = image.createGraphics();
			 image=resizedG.getDeviceConfiguration().createCompatibleImage(160, 100,Transparency.TRANSLUCENT);
			 resizedG.dispose();
			 resizedG=image.createGraphics();
			if (imge2_width>=160) {
				if (image2_height>=imge2_width) {
					//宽度大于160的情况，且高度大于宽度
					int image2_width_old=(int)((double)imge2_width/image2_height*100);
					Image newImage=image2.getScaledInstance( image2_width_old,100, Image.SCALE_SMOOTH);
					resizedG.drawImage(newImage, (160-image2_width_old)/2,0, null);
					resizedG.dispose();
				}else{
					int image2_height_old=(int)(((double)image2_height)/imge2_width*160);
					Image newImage=image2.getScaledInstance(160, image2_height_old, Image.SCALE_SMOOTH);
					resizedG.drawImage(newImage, 0, (100-image2_height_old)/2, null);
					resizedG.dispose();
				}
			}else {
				if (image2_height<100) {
					int left=(160-imge2_width)/2;
					int top=(100-image2_height)/2;
					Image newImage=image2.getScaledInstance(imge2_width, image2_height, Image.SCALE_SMOOTH);
					resizedG.drawImage(newImage,left,top, null);
					resizedG.dispose();
				}else {
					int image2_width_old=(int)((double)imge2_width/image2_height*100);
					Image newImage=image2.getScaledInstance( image2_width_old,100, Image.SCALE_SMOOTH);
					resizedG.drawImage(newImage, (160-image2_width_old)/2,0, null);
					resizedG.dispose();
				}
			}
			ImageIO.write(image, "png", new File(imageUrl));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		File file=new File("d:\\Pictures\\Camera Roll/1.jpg");
		changeImgeSize("d:\\Pictures\\Camera Roll/11.jpg", file);
	}

}
