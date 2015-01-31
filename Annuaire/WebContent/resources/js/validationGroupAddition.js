	$().ready(function() {
		$("#addGroupForm").validate({
			rules: {
				groupname:  {
					required: true,
					minlength: 1,
					maxlength : 50
				}
			},
			messages: {
				groupname : "Le nom du groupe doit compter entre 1 et 50 caractères."
			}
		});
	});