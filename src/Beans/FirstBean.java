package Beans;

import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;

import Funcoes.ADMFirst;

@ManagedBean(name = "bFirst")
public class FirstBean {
	ADMFirst adm = new ADMFirst();

	public void firstI() throws NoSuchAlgorithmException {
		adm.admFirst();
	}
	
	
}
