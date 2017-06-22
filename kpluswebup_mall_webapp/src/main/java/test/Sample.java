package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.OSSObject;
import com.aliyun.openservices.oss.model.OSSObjectSummary;
import com.aliyun.openservices.oss.model.ObjectListing;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;

public class Sample {
	private static OSSClient client;
	private static String accessKeyId = "<key>";
	private static String accessKeySecret = "<secret>";
	private static String endpoint = "http://oss.aliyuncs.com";
	/**
	 * 变量 accessKeyId 与 accessKeySecret 是由系统分配给用户的，称为ID对，用于标识用户，为访问OSS做签名验证
	 */
	static {
		accessKeyId = "<key>";
		accessKeySecret = "<secret>";
		endpoint = "http://oss.aliyuncs.com";
		// 初始化一个OSSClient
		client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	// 变量 accessKeyId 与 accessKeySecret 是由系统分配给用户的，称为ID对，用于标识用户，为访问OSS做签名验证。
	public static void main(String[] args) {
		createBucket("testBucket");
		// 下面是一些调用代码...
	}

	/**
	 * 新建Bucket 由于Bucket的名字是全局唯一的，所以尽量保证你的 bucketName 不与别人重复。
	 * 
	 * @param bucketName
	 */
	public static void createBucket(String bucketName) {
		// 获取Bucket的存在信息
		boolean exists = client.doesBucketExist(bucketName);
		// 输出结果
		if (exists) {
			System.out.println("Bucket exists");
		} else {
			System.out.println("Bucket not exists");
		}
		// 新建一个Bucket
		client.createBucket(bucketName);
	}

	/**
	 * 上传Object Object通过InputStream的形式上传到OSS中。在上面的例子里我们可以看出，每上传一个Object，
	 * 都需要指定和Object关联的ObjectMetadata
	 * 。ObjectMetaData是用户对该object的描述，由一系列name-value对组成
	 * ；其中ContentLength是必须设置的，以便SDK可以正确识别上传Object的大小。 Put
	 * Object请求处理成功后，OSS会将收到文件的MD5值放在返回结果的ETag中。用户可以根据ETag检验上传的文件与本地的是否一致。
	 */

	public static void putObject(String bucketName, String key, String filePath)
			throws FileNotFoundException {
		// 获取指定文件的输入流
		File file = new File(filePath);
		InputStream content = new FileInputStream(file);

		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();

		// 必须设置ContentLength
		meta.setContentLength(file.length());

		// 上传Object.
		PutObjectResult result = client.putObject(bucketName, key, content,
				meta);

		// 打印ETag
		System.out.println(result.getETag());
	}

	/**
	 * 列出所有Object
	 * listObjects方法会返回ObjectListing对象，ObjectListing对象包含了此次listObject请求的返回结果
	 * 。其中我们可以通过ObjetListing中的getObjectSummaries方法获取所有Object的描述信息
	 * （List<OSSObjectSummary>）。
	 */
	public static void listObjects(String bucketName) {
		// 获取指定bucket下的所有Object信息
		ObjectListing listing = client.listObjects(bucketName);
		// 遍历所有Object
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			System.out.println(objectSummary.getKey());
		}
	}

	/**
	 * 获取指定Object 当调用OSSClient的getObject方法时，会返回一个OSSObject的对象，此对象包含了Object的各种信息。
	 * 通过OSSObject的getObjectContent方法，
	 * 还可以获取返回的Object的输入流，你可以读取这个输入流来对Object的内容进行操作；记得在用完之后关闭这个流。
	 */
	public static void getObject(String bucketName, String key)
			throws IOException {
		// 获取Object，返回结果为OSSObject对象
		OSSObject object = client.getObject(bucketName, key);
		// 获取Object的输入流
		InputStream objectContent = object.getObjectContent();
		// 处理Object
		// 关闭流
		objectContent.close();
	}
}
