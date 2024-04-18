package org.ko.fastDFS;

import org.csource.fastdfs.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 
 */
public class FastDFSTests {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// 1、把FastDFS提供的jar包添加到工程中
		// 2、初始化全局配置。加载一个配置文件。
		ClientGlobal.init("client.conf");
		// 3、创建一个TrackerClient对象。
		TrackerClient trackerClient = new TrackerClient();
		// 4、创建一个TrackerServer对象。
		TrackerServer trackerServer = trackerClient.getConnection();
		// 5、声明一个StorageServer对象，null。
		StorageServer storageServer = null;
		// 6、获得StorageClient对象。
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		
		String file = "E:\\123.txt";
		String type = "txt";
		
		//上传文件
//		upLoad(storageClient, file, type);
		
		//下载文件
		downLoad(storageClient, "E:\\outPut.txt", "wKgBkFoCpTGELcQOAAAAAIi3nNI082.txt");
		
		//续传
//		apperdUpLoad(storageClient, file, "M00/00/00/wKgBkFoCpTGELcQOAAAAAIi3nNI082.txt");
	}

	/**
	 * 
	 * 说明：上传
	 * 
	 * @param
	 * @return
	 * @throws @author
	 *             研发部：JZR
	 * @time 2017年11月8日下午2:12:30
	 */
	public static void upLoad(StorageClient storageClient, String file, String type) throws Exception {
		String[] upload_appender_file = storageClient.upload_appender_file(file, type, null);
//		String[] strings = storageClient.upload_file(file, type, null);
		for (String string : upload_appender_file) {
			System.out.println(string);
		}
	}

	/**
		 * 
		 * 说明：续传
		 * 
		 * @param 
		 * @return   
		 * @throws
		 * @author 研发部：JZR
		 * @time  2017年11月8日下午2:15:53
		 */
	public static void apperdUpLoad(StorageClient storageClient, String filename, String fileId) throws Exception {
		File file = new File(filename);
		FileInputStream inputStream = new FileInputStream(file);
		byte[] b = new byte[1024];
		int n;
		while ((n = inputStream.read(b)) != -1) {
			storageClient.append_file("group1", fileId, b);
		}
		inputStream.close();
		System.out.println("续传完成");
	}

	/**
	 * 
	 * 说明：下载
	 * 
	 * @param
	 * @return
	 * @throws @author
	 *             研发部：JZR
	 * @time 2017年11月8日下午2:10:33
	 */
	public static void downLoad(StorageClient storageClient, String file, String name) throws Exception {
		byte[] bytes = storageClient.download_file("group1", "M00/00/00/" + name);
		File outputFile = new File(file);
		FileOutputStream outputFileStream = new FileOutputStream(outputFile);
		outputFileStream.write(bytes);
		outputFileStream.close();
		System.out.println("下载完成。");
	}
}
