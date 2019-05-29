package br.org.centrocac.boletofacilsdk.model.response;

import javax.xml.bind.annotation.XmlRootElement;

import br.org.centrocac.boletofacilsdk.model.entities.FeeSchema;

@XmlRootElement(name = "result")
public class FeeSchemaResponse extends BaseResponse {
	private FeeSchema data;

	public FeeSchema getData() {
		return data;
	}

	public void setData(FeeSchema data) {
		this.data = data;
	}
}
