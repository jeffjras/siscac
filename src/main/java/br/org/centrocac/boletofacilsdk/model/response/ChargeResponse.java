package br.org.centrocac.boletofacilsdk.model.response;

import javax.xml.bind.annotation.XmlRootElement;

import br.org.centrocac.boletofacilsdk.model.entities.ChargeList;

@XmlRootElement(name = "result")
public class ChargeResponse extends BaseResponse {
	private ChargeList data;

	public ChargeList getData() {
		return data;
	}

	public void setData(ChargeList data) {
		this.data = data;
	}
}
