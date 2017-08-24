package formation.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import vol.metier.dao.*;
import vol.metier.model.*;



@Component
public class ClientBean {
	
			
		private Long clientId;
		private Client clientMoral = new ClientMoral();
		private Client clientPhysique = new ClientPhysique();
		@Autowired
		private ClientDao clientDao;
	
		
		public List<Client> getClientsMoral() {
			return clientDao.findAllClientMoral();
		}
		public List<Client> getClientsPhysique() {
			return clientDao.findAllClientPhysique();
		}

		public List<Client> getClients() {
			return clientDao.findAll();
		}

		public Long getClientId() {
			return clientId;
		}

		public void setClientId(Long clientId) {
			this.clientId = clientId;
		}

		public Client getClientMoral() {
			return clientMoral;
		}

		public void setClientMoral(Client clientMoral) {
			this.clientMoral = clientMoral;
		}
		public Client getClientPhysique() {
			return clientPhysique;
		}

		public void setClientPhysique(Client clientPhysique) {
			this.clientPhysique = clientPhysique;
		}
		
		public String clientMoaladd() {
			
			return "clientMoalEdit";
		}
	public String clientPhysiqueadd() {
			
			return "clientPhysiqueEdit";
		}

		public String clientMoralEdit(Long id) {
			List<Client> listClients = clientDao.findAllClientMoral();

			return "clientMoalEdit";
		}
		public String clientPhysiqueEdit(Long id) {
			List<Client> listClients = clientDao.findAllClientPhysique();

			return "clientPhysiqueEdit";
		}
		
		

		public String clientEdit(Long id) {

			Client client = clientDao.find(id);

			if (client instanceof ClientMoral) {
			
			return "clientMoralEdit";
			
			} else if (client instanceof ClientPhysique) {
				
				return "clientPhysiqueEdit";
				
			}
			return "client/";
		}
		
		

		public String saveClientPhysique(@ModelAttribute("clientPhysique") @Valid ClientPhysique clientPhysique, 
				 BindingResult errors) {
		if (errors.hasErrors()) {

			return "clientEdit";
			}

		if (clientPhysique.getId() != null) {
				clientDao.update(clientPhysique);
			} else {
				clientDao.create(clientPhysique);
		}
			return "forward:clientsPhysique.xhtml";
		}
		
		
		
		
		
		public String saveClientMoral(@ModelAttribute("clientMoral") @Valid ClientMoral clientMoral, 
				 BindingResult errors) {
		if (errors.hasErrors()) {

			return "clientEdit";
			}

		if (clientMoral.getId() != null) {
				clientDao.update(clientMoral);
			} else {
				clientDao.create(clientMoral);
		}
			return "forward:clientsMoral.xhtml";
		}
		
		
		
		
		
		

	
}
