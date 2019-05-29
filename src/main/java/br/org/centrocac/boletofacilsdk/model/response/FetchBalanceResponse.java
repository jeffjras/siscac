package br.org.centrocac.boletofacilsdk.model.response;

import javax.xml.bind.annotation.XmlRootElement;

import br.org.centrocac.boletofacilsdk.model.entities.PayeeBalance;

@XmlRootElement(name = "result")
public class FetchBalanceResponse extends BaseResponse {
	private PayeeBalance data;

	public PayeeBalance getData() {
		return data;
	}

	public void setData(PayeeBalance data) {
		this.data = data;
	}
}
