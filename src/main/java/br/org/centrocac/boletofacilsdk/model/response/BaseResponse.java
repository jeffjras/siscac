package br.org.centrocac.boletofacilsdk.model.response;

import br.org.centrocac.boletofacilsdk.model.ModelBase;

public class BaseResponse extends ModelBase {
	private Boolean success;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success.booleanValue();
	}
}
