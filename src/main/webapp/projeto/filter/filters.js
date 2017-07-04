project.filter("traslateStatus",function(){
	return function(value){
		if(value == 10){
			return 'Aguardando aprovação';
		}else if(value == 20){
			return 'Aprovado';
		}else if(value == 30){
			return 'Reprovado';
		}else if(value == 40){
			return 'Produto a caminho';
		}else if(value == 50){
			return 'Produto entregue. Aguardando encerramento';
		}else if(value == 60){
			return 'Encerrado';
		}else{
			return '';
		}
	};
});