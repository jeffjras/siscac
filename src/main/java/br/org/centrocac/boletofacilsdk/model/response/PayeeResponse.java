package br.org.centrocac.boletofacilsdk.model.response;

import javax.xml.bind.annotation.XmlRootElement;

import br.org.centrocac.boletofacilsdk.model.entities.Payee;

@XmlRootElement(name = "result")
public class PayeeResponse extends BaseResponse {
	private Payee data;

	public Payee getData() {
		return data;
	}

	public void setData(Payee data) {
		this.data = data;
	}
}
