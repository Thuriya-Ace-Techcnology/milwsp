package org.ace.insurance.common;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {
	public static BufferedImage generateQRCodeImage(String code) {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix= null;
		try {
			bitMatrix = barcodeWriter.encode(code, BarcodeFormat.QR_CODE, 200, 200);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
}
