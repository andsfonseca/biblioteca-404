package Util;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import Beans.PessoasBean;

public class VeriListener implements PhaseListener {

	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext().getCurrentInstance();
		FacesContext con = FacesContext.getCurrentInstance();
		if ("/index.xhtml".equals(context.getViewRoot().getViewId())
				|| "/Registros.xhtml".equals(context.getViewRoot().getViewId())
				|| "/VisualizarLivros.xhtml".equals(context.getViewRoot()
						.getViewId())
				|| "/HelpScreenSenha.xhtml".equals(context.getViewRoot()
						.getViewId())
				|| "/VisualizarUsuarios.xhtml".equals(context.getViewRoot()
						.getViewId())) {

			return;
		}

		PessoasBean bean = (PessoasBean) context
				.getApplication()
				.evaluateExpressionGet(context, "#{bPessoa}", PessoasBean.class);

		if (!bean.logado()) {
			NavigationHandler nv = context.getApplication()
					.getNavigationHandler();
			nv.handleNavigation(context, null, "index?faces-redirect=true");
			con.addMessage(null, new FacesMessage("Erro!",
					"� nececess�rio estar logado para acessar essa p�gina"));
			System.out.println("Processamento Incorreto: Favor fa�a Login");
			context.renderResponse();

		} 
	}

	public void beforePhase(PhaseEvent event) {

	}

	public PhaseId getPhaseId() {

		return PhaseId.RESTORE_VIEW;
	}

}
