package org.ko.security.core.validate.code.image;

import javax.imageio.ImageIO;

import org.ko.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图片验证码处理器
 */
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	/**
	 * 发送图形验证码，将其写到响应中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}

}
