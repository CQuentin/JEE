	$().ready(function() {
		$("#editForm").validate({
			rules: {
				firstName: "required",
				lastName: "required",
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
				password: {
					required: "Mot de passe obligatoire.",
					minlength: "Le mot de passe doit contenir au moins 8 caractères"
				},
				mail: {
					required: "Adresse mail obligatoire.",
					email: "Adresse mail non valide."
				},
				dateOfBirth: "La date doit être au format aaaa-mm-jj"
			}
		});
	});