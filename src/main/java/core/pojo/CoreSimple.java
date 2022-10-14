package core.pojo;

import java.io.Serializable;

// coresimple 實現 序列化
public class CoreSimple implements Serializable {
	// 設定序列化   成功用true/false  失敗用字串回覆
	private static final long serialVersionUID = 1457755989409740329L;
	private boolean successful;
	private String message;
	
//  建立空的  給帶入
	public CoreSimple() {
	}

	// 建立可帶入的公式
	public CoreSimple(boolean successful, String message) {
		this.successful = successful;
		this.message = message;
	}

	// 判定成功 回傳到successful
	public boolean isSuccessful() {
		return successful;
	}

	// 將成功的值存入
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	// 判定失敗後   讀取失敗的訊息 回傳到message
	public String getMessage() {
		return message;
	}
	// 存入訊息  回傳
	public void setMessage(String message) {
		this.message = message;
	}
}
