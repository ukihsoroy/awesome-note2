package com.panhai.sys.builder;

import com.panhai.sys.bo.FilePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class FilePathBuilder {

    private static final Logger log = LoggerFactory.getLogger(FilePathBuilder.class);

    @Value("${file.local.root}") private String fileLocalRoot;
    @Value("${file.http.root}") private String fileHttpRoot;

    /**
     *
     * @param fileName	不带路径的文件名
     * @return
     */
    public FilePath buildArchivePath(String fileName){
        String httpPath = fileHttpRoot + fileName;
        String localPath = fileLocalRoot  + fileName;

        FilePath fp = new FilePath();
        fp.setHttpPath(httpPath);
        fp.setLocalPath(localPath);
        return fp;
    }

    public boolean download(HttpServletRequest request, HttpServletResponse response, String downLoadPath,
                            String realName) {
        boolean downloadSuccessfulFlg = true;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileInputStream fis = null;

        long fileLength = new File(downLoadPath).length();

        try {
            String filename = processFileName(request, realName);
            if (!StringUtils.isEmpty(filename)) {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename=" + filename);
                response.setHeader("Content-Length", String.valueOf(fileLength));
                fis = new FileInputStream(downLoadPath);
                bis = new BufferedInputStream(fis);
                bos = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } else {
                downloadSuccessfulFlg = false;
            }
        } catch (FileNotFoundException e) {
            downloadSuccessfulFlg = false;
        } catch (UnsupportedEncodingException e) {
            downloadSuccessfulFlg = false;
        } catch (IOException e) {
            downloadSuccessfulFlg = false;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    downloadSuccessfulFlg = false;
                }
            }

            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    downloadSuccessfulFlg = false;
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    downloadSuccessfulFlg = false;
                }
            }
            if (!downloadSuccessfulFlg) {
                setDownloadFailedInfo(response);
            }
        }
        return downloadSuccessfulFlg;
    }

    public String processFileName(HttpServletRequest request, String realName) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie

                String name = java.net.URLEncoder.encode(realName, "UTF-8");

                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {

                codedfilename = new String(realName.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (UnsupportedEncodingException e) {
        }
        return codedfilename;
    }

    public void setDownloadFailedInfo(HttpServletResponse response) {
        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().println("文件下载失败！");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
        }
    }
}
