package br.org.centrocac.boletofacilsdk.exceptions;

import br.org.centrocac.boletofacilsdk.model.ModelBase;

public class BoletoFacilInvalidEntityException extends BoletoFacilException {

	private static final long serialVersionUID = 1L;

	public BoletoFacilInvalidEntityException(ModelBase entity) {
		super(entity.getClass().getSimpleName() + " inválido.");
	}

	public BoletoFacilInvalidEntityException(br.org.centrocac.boletofacilsdk.model.ModelBase modelBase, Exception e) {
		super(modelBase.getClass().getSimpleName() + " inválido.", e);
	}
}
