

$(document).ready(function () {



    // FORM VALIDATION FEEDBACK ICONS
    // =================================================================
    var faIcon = {
        valid: 'fa fa-check-circle fa-lg text-success',
        invalid: 'fa fa-times-circle fa-lg',
        validating: 'fa fa-refresh'
    };

    // =================================================================
    $('#login').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {
            userId: {
                validators: {
                        notEmpty: {
                        message: 'Please Enter User Name'
                    }
                }
            },
            loginPassword: {
                //container: 'tooltip',
                validators: {
                        notEmpty: {
                        message: 'Please Enter Password'
                    }
                }
            }

        }
    });

});


