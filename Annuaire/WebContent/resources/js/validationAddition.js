	$().ready(function() {
		$("#addForm").validate({
			rules: {
				firstName: "required",
				lastName: "required",
				login: "required",
				password: {
					required: true,
					minlength: 8
				},
				mail: {
					required: true,
					email: true
				},
				dateOfBirth: {
					required: false,
					dateISO: true
				}
			},
			messages: {
				firstName: "Prénom obligatoire.",
				lastName: "Nom obligatoire.",
				login: "Login obligatoire.",
				password: {
					required: "Mot de passe obligatoire.",
					minlength: "Le mot de passe doit contenir au moins 8 caractères"
				},
				mail: {
					email: "Adresse mail non valide.",
					required: "Adresse mail obligatoire."
				},
				dateOfBirth: "La date doit être au format aaaa-mm-jj"
			}
		});
	});