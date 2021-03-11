package server;

public class ChatVO {
	private String id 		= null;
	private String nickname = null;
	private String password = null;

	public ChatVO() {

	}

//	public ChatVO() {
//
//	}
//
//	public ChatVO() {
//
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
