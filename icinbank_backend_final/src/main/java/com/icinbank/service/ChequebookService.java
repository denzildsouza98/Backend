package com.icinbank.service;

import java.util.List;
import com.icinbank.model.Chequebook;
import com.icinbank.response.ChequeResponse;

public interface ChequebookService {

	public ChequeResponse createrequest(Chequebook chequebook);
	public List<Chequebook> getRequests(int account);
}
