package Beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "bHelp")
public class HelpBean {
	
	private boolean loginUser;
	private boolean loginSenha;
	
	public boolean isLoginUser() {
		return loginUser;
	}
	public void setLoginUser(boolean loginUser) {
		this.loginUser = loginUser;
	}
	public boolean isLoginSenha() {
		return loginSenha;
	}
	public void setLoginSenha(boolean loginSenha) {
		this.loginSenha = loginSenha;
	}
	public void setFalse(){
		loginUser = false;
		loginSenha = false;
	}
	public void UserScreen(){
		setFalse();
		loginUser = true;
	}
	public void SenhaScreen(){
		setFalse();
		loginSenha = true;
	}
	
}
