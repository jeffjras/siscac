
function validaCampos(form) {	
	var tipo_pessoa 	= form.tipoPessoa.value;		

	if (tipo_pessoa.length < 1) {
		alert("Informe se é uma pessoa Física ou Jurídica!");		
		return(false);	
	}
	
	if (form.cpfcmpj.length < 1 || form.cpfcnpj.value == null || form.cpfcnpj.value == '') {
		alert("Campo CPF/CNPJ é obrigatório!");
		form.login.focus();
		return(false);	
	}
	
	if (form.usuario.length < 1 || form.usuario.value == null || form.usuario.value == '') {
		alert("Campo login é obrigatório!");
		form.login.focus();
		return(false);	
	}

	if (form.senha.length < 1 || form.senha.value == null || form.senha.value == '') {
		alert("Preencha o campo senha!");
		form.senha.focus();
		return(false);	
	}	
	
	if (form.email.length < 1 || form.email.value == null || form.email.value == '') {
		alert("Preencha o campo e-mail!");
		form.email.focus();
		return(false);	
	}
				 
	if (form.nome.length < 1 || form.nome.value == null || form.nome.value == '') {
		alert("Preencha o campo nome!");
		form.nome.focus();
		return(false);	
	}

	if (endereco.length < 1 || form.endereco.value == null || form.endereco.value == '') {
		alert("Preencha o campo endereco!");
		form.endereco.focus();
		return(false);	
	}	
	
	if (form.telefone.length < 1 || form.telefone.value == null || form.telefone.value == '') {
		alert("Preencha o campo telefone!");
		form.telefone.focus();
		return(false);	
	}	
	return(true);
}

function deletaConfirma() {			
	var resp = confirm("Deseja realmente excluir sua conta? "); 
	
	if (resp == true) {		
		document.getElementById("formDeleta").submit();
		return(true);
	}	
	return(false);
}

function emailConfirma() {	
	var info = prompt("Informe o e-mail cadastrado para\nrecuperar os dados:");
	document.getElementById("formEmail").email.value = info;
	var resp = confirm("Deseja enviar seus dados para o e-mail: "+ info+" ?"); 
	
	if (resp == true) {		
		document.getElementById("formEmail").submit();
		return(true);
	}	
	return(false);
}

function verificaQuantidade() {
    //var form = document.getElementById("formCompra").name;
	var resp = false;	
	var qtde = document.getElementById("qtde");//form.qtde;
	var qtde_estoque = document.getElementById("qtde_estoque");//form.qtde_estoque;		
	
	//alert("qtde: " + qtde.value);
	//alert("qtde_est: " + qtde_estoque.value);
	
	if (parseInt(qtde_estoque.value) < parseInt(qtde.value)) {
		alert("A quantidade informada excede o saldo do produto em estoque.");
		qtde.focus();
		return(resp);	
	}
	
	if (qtde.value < 1 || qtde.value == null || qtde.value == '') {
		alert("Antes de comprar preencha a quantidade!");
		qtde.focus();
		return(resp);	
	} 	
	resp = true;
	if (resp == true) {	
        	
		document.getElementById("formCompra").submit();
		return(resp);
	}	
	var elem = document.getElementById("qtde");
	//alert(elem.value);
	elem.disabled = true;
}

var valor;

function verificaCheck(elem) {				    
	valor = elem.value;   	 
	return valor;						
}

function verificaVoto(id) {	
    var resp = false;
    id = valor;		
	if (id == null || id == '') {
		alert("Selecione uma opnião para votar!");		
		return(resp);	
	}	
	document.getElementById("id_opniao").value = id;			
	resp = true;
	if (resp == true) {	
		document.getElementById("formVoto").submit();
		return(resp);
	}	
}

var CheckMaximo = 1;

function verificar() {
	var Marcados = 1;
	var objCheck = document.forms['formVoto'].elements['cbvoto'];

	//Percorrendo os checks para ver quantos foram selecionados:
	for (var iLoop=0; iLoop<objCheck.length; iLoop++) {
	//Se o número máximo de checkboxes ainda não tiver sido atingido, continua a verificação:
		if (objCheck[iLoop].checked) {
			Marcados++;
		}
		
		if (Marcados <= CheckMaximo) {
		//Habilitando todos os checkboxes, pois o máximo ainda não foi alcançado.
		for (var i=0; i<objCheck.length; i++) {
			objCheck[i].disabled = false;
		}       
		//Caso contrário, desabilitar o checkbox;
		//Nesse caso, é necessário percorrer todas as opções novamente, desabilitando as não checadas;
		
		} else {
			for (var i=0; i<objCheck.length; i++) {
				if(objCheck[i].checked == false) {
					objCheck[i].disabled = true;
				}       
		  }
		}
	}
}

function desabilitaQtde() {
	var elem = document.getElementById("qtde");
	//alert(elem.value);
	elem.disabled = true;
}

function validaEnvioEmail(form) {		
	var nome 		= form.nome;	
	var endereco 	= form.endereco;	 	
	var bairro 		= form.bairro;
	var fone 		= form.fone;
	var assunto 	= form.assunto;
	var mensaegm 	= form.mensagem;
	
	if (nome.length < 1 || nome.value == null || nome.value == '') {
		alert("Preencha o campo nome!");
		form.nome.focus();
		return(false);	
	}
	if (endereco.length < 1 || endereco.value == null || endereco.value == '') {
		alert("Preencha o campo Endereco!");
		form.endereco.focus();
		return(false);	
	}		
	if (bairro.length < 1 || bairro.value == null || bairro.value == '') {
		alert("Preencha o campo Bairro!");
		form.bairro.focus();
		return(false);	
	}	
	if (fone.length < 1 || fone.value == null || fone.value == '') {
		alert("Preencha o campo Fone!");
		form.fone.focus();
		return(false);	
	}	
	if (assunto.length < 1 || assunto.value == null || assunto.value == '') {
		alert("Preencha o campo Assunto!");
		form.assunto.focus();
		return(false);	
	}	
	if (mensagem.length < 1 || mensagem.value == null || mensagem.value == '') {
		alert("Preencha o campo Mensagem!");
		form.mensagem.focus();
		return(false);	
	}	
	return(true);
}

function verificaTipoUsuario(form) {
    var opcao = prompt('Informe o número que define \ncomo deseja interagir no sistema: \n(1) Tenho Resíduo (Produtor) ou \n(2) Quero Resíduo (Consumidor)');	
	if (opcao == 1) {	
		form.opcao.value = 1;
	} 
	if (opcao == 2) {		
		form.opcao.value = 2;
	} 
	if (opcao.length < 1 || opcao == '' || opcao == null) {
		form.opcao.value = 0;
	}		
}

function verificaTipoPessoa(elem){
	document.getElementById("tipoPessoa").value = elem.value;
}
