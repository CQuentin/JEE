	$().ready(function() {
		$("#editForm").validate({
			rules: {
				firstName: {
					required: true,
					maxlength: 20
				},
				lastName: {
					required: true,
					maxlength: 20
				},
				password: {
					required: true,
					minlength: 8,
					maxlength: 40
				},
				mail: {
					required: true,
					email: true,
					pattern: /.+@.+\.[a-z]+/,
					maxlength: 50
				},
				dateOfBirth: {
					required: false,
					dateISO: true,
					pattern: /\d\d\d\d-\d\d-\d\d/,
					maxlength: 50
				}
			},
			messages: {
				firstName: {
					required: "Prénom obligatoire.",
					maxlength: "Le prénom ne peut pas faire plus de 20 caractères"
				},
				lastName: {
					required: "Nom obligatoire.",
					maxlength: "Le nom ne peut pas faire plus de 20 caractères"
				},
				password: {
					required: "Mot de passe obligatoire.",
					minlength: "Le mot de passe doit contenir au moins 8 caractères",
					maxlength : "Le mot de passe ne peut pas contenir plus de 40 caractères"
				},
				mail: {
					email: "Adresse mail non valide.",
					pattern: "Adresse mail non valide.",
					required: "Adresse mail obligatoire.",
					maxlength: "L'adresse mail ne peut pas faire plus de 50 caractères"
				},
				dateOfBirth: "La date doit être au format aaaa-mm-jj"
			}
		});
	});