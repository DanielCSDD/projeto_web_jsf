/**
 * Confirmar a exclusao de um contato
 * 
 * @author Professor Jose de Assis
 * @param id
 */

function confirmar(id) {
	let resposta = confirm("Confirma a exclusão deste contato?")
	if (resposta === true) {
		window.location.href = "excluir?id=" + id;
	}
}